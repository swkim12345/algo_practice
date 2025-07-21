/*
 * 텀 프로젝트
 * https://www.acmicpc.net/problem/9466
 * 
 * 사이클이 완성되면, 사이클 내에 있는 값만 그룹에 속한 것으로 취급함.
 * 사이클이 완성되었을 때, 사이클 내에 있지 않으면 그룹 내에 있지 않은 것으로 취급 - union-find를 사용..?
 * union-find를 사용 시 최적화를 하면 가장 부모의 값을 가지고 있음.
 * 
 * dfs를 사용해 사이클을 확인하고, 자기 자신으로 돌아올 경우 백트래킹을 통해 하나의 팀으로 만든다.
 * 팀이 결정된 이후, 다른 dfs를 시도할 때 팀에 접촉(팀 내 노드)라면 이 dfs는 팀이 될 수 없다.
 * dfs를 이용해 사이클을 만들려면, stack에 삽입하고나서 이미 방문한 노드라는 것을 확인해야함.
 * 방문한 노드라면, stack에서 pop을 이용해 하나의 그룹임을 표시하고, 사이클이 시작된 곳까지 백트래킹해 그룹 표시함.
 * 이후는 -1와 같은 그룹내에 속하지 않지만, 방문한 노드라는 것을 표시하는 것이다.
 * 
 * 초기화
 * 입력값을 받아서 arry배열에 저장(idx가 시작 노드, 1부터 시작)
 * visited는 0이 방문하지 않음. -1이 방문했으나 사이클 아님, 1부터는 사이클 완성
 * 
 * 솔루션
 * dfs를 이용해 서치 - stack 자료구조 사용
 * array 배열에 있는 값을 방문(visited에 0인 노드) 
 * 1. -1을 방문할 경우 -> stack에서 pop 하면서 없을 때 까지 -1로 업데이트
 * 2. 0을 방문할 경우 -> 자신이 가지고 있는 idx로 visited배열 업데이트, stack에 추가
 * 3. 1이상을 방문할 경우
 * 3-1. 자기 자신과 같을 경우 - cycle 완성, stack에서 pop, 이후 만난 노드 이후의 pop은 -1로 업데이트
 * 3-2. 자기 자신과 다를 경우 - 다른 cycle, stack에서 pop하면서 -1로 업데이트
 * 
 * 4. 모든 visited 노드를 돌면서 -1인 노드를 answer에 추가함. answer 출력.
 * 
 */
import java.util.*;
import java.io.*;

public class j_9466 {
    static int[] visited = new int[100001]; // 방문 노드, -1 : 사이클 아님, 0 : 방문하지 않음, 1 ~ : 사이클
    static int[] array = new int[100001]; // 다음 노드가 저장되어 있음.

    static void rewind(Deque<Integer> deq, int updateNum) {
        int tmp;

        // -1이 아니라 사이클이 완성된 경우
        if (updateNum != -1) {
            while (deq.size() > 0) { // 종료조건 1
                tmp = deq.poll();
                
                if (visited[tmp] == updateNum) { // 사이클 완성시 - 종료조건 2
                    break;
                } else {
                    visited[tmp] = updateNum;
                }
            }
        } else { // -1이라 남은 값을 모두 사이클이 아닌 경우
            while(deq.size() > 0) {
                tmp = deq.poll();

                visited[tmp] =  updateNum;
            }
        }
    }

    static void dfs(int idx, int groupNumber, int N) {
        Deque<Integer> deq = new ArrayDeque<>();
        int tmp, next;
        
        deq.add(idx);
        visited[idx] = groupNumber;
        // dfs를 돌림.
        while (true) {
            tmp = deq.peek();
            next = array[tmp];
            // 0일 경우
            if (visited[next] == 0) {
                deq.add(next);
                visited[next] = groupNumber;
                System.out.printf("before : %d, after : %d\n", tmp, next);
            } else if (visited[next] == -1) { // -1일 경우
                rewind(deq, -1);
                break;
            } else if (visited[next] == groupNumber) { // 사이클 완성시
                rewind(deq, groupNumber);
                rewind(deq, -1);
                break;
            } else { //다른 사이클 - 사이클 완성되지 않음.
                rewind(deq, -1);
                break;
            }
        }
    }

    static void solution(int N) {
        int groupNumber = 0, answer = 0;

        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0) dfs(i, ++groupNumber, N);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i] == -1) answer += 1; // 사이클이 아닌 경우
            System.out.printf("%d ", visited[i]);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T, N;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited, 0);
            for (int j = 1; j <= N; j++) {
                array[j] = Integer.parseInt(st.nextToken());
            }
            solution(N);
        }
    }
}
