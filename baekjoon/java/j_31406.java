/*
 * 트리 탐색기 (Easy)
 * 
 * 문제 요약
 * 트리 형태를 따르는 폴더 구조, 폴더는 토글이 가능
 * 토글이 더이상 하위 폴더 접근 불가능.
 * move : 폴더구조가 리니어하게 보인다고 가정(일반 컴퓨터 폴더구조), 이 상태에서 리니어하게 이동(- / +)
 * toggle : 폴더 토글을 반전시킴 (열림 -> 닫힘, 닫힘 -> 열림)
 * 
 * 접근
 * dfs를 이용해 푸는 문제임.
 * 
 * 주의사항
 * 폴더의 끝에 도달하는 경우가 있을 수 있음
 */

import java.io.*;
import java.util.*;

public class j_31406 {
    /*
     * dfs를 위해 사용하는 스택
     * 이 스택은 특이하게 트리의 구조대로 따라가는 것이 아니라, linear한 구조로 따라가게 됨.
     * idx 0 - list 내 자식 폴더의 인덱스 번호 (dfs를 원할하게 돌리기 위해 사용.)
     * idx 1 - 자식 폴더 번호
     */
    static Deque<int[]> stack = new ArrayDeque<>();
    static List<Node> graph = new ArrayList<>();
    static int cursor; // 현재 가리키는 폴더 번호

    final static int OPEN = 0;
    final static int CLOSE = 1;

    static class Node {
        public int parent, toggle;
        public int[] childs;

        Node(int size) {
            this.parent = 0;
            this.toggle = CLOSE;
            this.childs = new int[size];
        }

        Node(int size, int parent) {
            this.parent = parent;
            this.toggle = CLOSE;
            this.childs = new int[size];
        }
    }

    // stack을 복제한 다음, 탐색
    // pop은 복제한 스택만, push는 두 개의 stack 모두에서 있어야 함.
    static void weiredDfs(int k) {
        Deque<int[]> copiedStack = new ArrayDeque<>();
        int mv = 0, childIdx, parent, parentIdx;
        int[] childs;
        int[] newElement;

        for (int[] tmp : stack) {
            copiedStack.addLast(tmp);
        }

        /*
         * 1. 먼저 현재 노드의 child부터 탐색
         * 1-1. toggle에 닫혀있거나, child를 이미 모두 탐색했다면(child가 0일 수 있음.) - coppied stack만 pollLast
         * 1-3. 아니라면 - child 탐색, 두 스택 모두 addLast
         */
        parentIdx = copiedStack.peekLast()[0];
        parent = copiedStack.peekLast()[1];
        childs = graph.get(parent).childs;
        childIdx = -1; // 현재 노드는 child를 탐색하지 않은 상태임.

        while (mv < k && !copiedStack.isEmpty()) {
            // System.out.printf("now node : %d, child index : %d, toggle : %d, size : %d, stack size : %d, copied stack size : %d\n", parent, childIdx, graph.get(parent).toggle, graph.get(parent).childs.length, stack.size(), copiedStack.size());
            if (graph.get(parent).toggle == CLOSE || childIdx + 1 >= childs.length) {
                if (parent == 1 && childIdx + 1 >= childs.length) { //종료조건 - 더이상 탐색할 노드가 없을 때
                    break;
                }
                parent = graph.get(parent).parent;
                copiedStack.pollLast();
    
                childIdx = parentIdx;
            } else {
                /*
                 * child 탐색(childIdx + 1), 두스택 모두 addLast
                 * mv ++;
                 */
                cursor = childs[childIdx + 1];
                newElement = new int[]{childIdx + 1, cursor};
                copiedStack.addLast(newElement);
                stack.addLast(newElement);
                mv ++;

                // childIdx 초기화
                childIdx = -1;
                graph.get(cursor).parent = parent;
                parent = cursor;
            }
            parentIdx = copiedStack.peekLast()[0];
            childs = graph.get(parent).childs;
        }
    }
    
    // dfs를 이용해 좌표 이동, 이동 후 cursor 값 리턴
    static int move(int k) {
        int mv = 0;

        /*
         * k가 양수일 때
         * toggle이
         * 열려있는 경우 - child 탐색 할 수 있음. stack 삽입, mv ++
         * 닫혀있는 경우 - child 탐색할 수 없음 다음 child 탐색, 탐색 불가능 시 부모 노드 부르고, child 이후 탐색
         * 종료조건 - 더이상 탐색할 child가 없을 때 / move가 끝난 경우
         */

        if (k > 0) {
            weiredDfs(k);
        }

        /*
         * k가 음수일 때
         * stack을 pop함.
         * 종료조건 - stack의 사이즈가 2일 때(1번노드의 첫번째 child 도달시) / move 끝날 경우
         */
        else {
            k = Math.abs(k);
            while (mv++ < k && stack.size() > 2) {
                stack.pollLast();
                cursor = stack.peekLast()[1];
                // System.out.printf("back mv : %d\n", cursor);
            }
        }

        return cursor;
    }

    // 현재 가리키는 폴더 번호 토글을 반대로 바꿈
    static void toggle() {
        int state = graph.get(cursor).toggle;
        
        if (state == OPEN) {
            graph.get(cursor).toggle = CLOSE;
        } else {
            graph.get(cursor).toggle = OPEN;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N, Q, childCnt, child;

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        // 0번에서 인덱스를 시작할 수 있도록 추가
        graph.add(new Node(1));
        graph.get(0).childs[0] = 1;
        // 자식 폴더 추가
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            childCnt = Integer.parseInt(st.nextToken());

            Node node = new Node(childCnt);
            for (int j = 0; j < childCnt; j++) {
                child = Integer.parseInt(st.nextToken());
                node.childs[j] = child;
            }

            graph.add(node);
        }

        cursor = graph.get(1).childs[0];
        graph.get(cursor).parent = 1;
        graph.get(1).toggle = OPEN;
        stack.addLast(new int[]{0, 1}); //최상위 노드 출력
        stack.addLast(new int[]{0, cursor}); // 1번 노드의 첫번째 child 추가
        

        // 쿼리 진행
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("toggle")) {
                toggle();
            } else {
                move(Integer.parseInt(st.nextToken()));
                // System.out.println(cursor);
                sb.append(cursor).append("\n");
            }
        }

        // 정답 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
