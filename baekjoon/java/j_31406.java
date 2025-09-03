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
    static int cursor; // 현재 가리키는 폴더 번호

    static final boolean OPEN = true;
    static final boolean CLOSE = false;

    static List<Node> graph = new ArrayList<>();
    static List<Element> elements = new ArrayList<>();

    static class Node {
        int parent;
        int[] childs;

        Node(int size) {
            this.parent = 0;
            this.childs = new int[size];
        }
    }

    /*
     * 전위순회로 나온 결과를 저장하기 위함
     * parent : 부모 노드의 인덱스
     * me : 자기 자신의 번호
     * child : 자식 노드의 인덱스
     * toggle : 토글링 확인
     */
    
    static class Element {
        int parent;
        int me;
        List<Integer> child;
        boolean toggle;

        Element(int parent, int me) {
            this.parent = parent;
            this.me = me;
            this.child = new ArrayList<>();
            this.toggle = false;
        }
    }

    // 전위순회, 리턴되는 값은 child의 idx 값
    static int preorder(int idx, int parent) {
        Node node = graph.get(idx);
        Element element = new Element(parent, idx);
        int childIdx = elements.size();

        elements.add(element);
        parent = node.parent;
        
        // 이진트리가 아니므로 순회
        for (int child : node.childs) {
            element.child.add(preorder(child, parent));
        }
        return childIdx;
    }

    static void toggle() {
        elements.get(cursor).toggle = !elements.get(cursor).toggle;
    }

    static void plusMove(int mv) {
        Element element, parent;

        while (mv > 0) {
            element = elements.get(cursor);

            // toggle on && 자식 노드 존재 - 이동 후 다음 노드 탐색
            if (element.toggle == OPEN && !element.child.isEmpty()) {
                cursor++;
                mv--;
                continue;
            }

            // 이외의 경우
            // parent 
        }
    }

    static int move(int mv) {
        

        return cursor;
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

        // 전위 순회 확인 완료
        preorder(1, 0);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("toggle")) toggle();
            else {
                move(Integer.parseInt(st.nextToken()));
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
