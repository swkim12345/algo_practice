/*
 * 가장 가까운 공통 조상
 * 
 * 문제 조건 해설
 * 1. 루트가 있는 트리 - 부모가 존재 / 트리이므로 사이클이 존재하지 않음.
 * 2. A B -> A 가 부모인 B 노드
 * 
 * 해결방법
 * 특정 노드 2개 제공 -> 2개에서 시작하고, 부모를 향해 이어지는 dfs를 하자. 
 * 가장 가까운 공통 조상을 구하기 위해 dfs 한 사이클마다 0, 1...씩 늘어나고, 백트래킹시 -1을 하자.
 * 하나의 부모만 존재하므로 dfs 말고도 다른 해결방법 - 루트에서 거리를 바탕으로 푸는 방법도 존재함.
 * 하지만, 여기선 dfs로 풀자.
 * 
 * 
 * 주의사항
 * 노드 인덱스는 1-N 사이이다.
 * 루트노드의 부모는 0이다.
 * 
 * 오답노트
 * 1. testcase의 제한이 없음. 따라서, 시간복잡도가 낮아야 함.
 * 이게 모르면 전형적인 쳐맞아야하는 문제인거 같은데... 모르겠다.
 * dfs로 풀이를 바꿀까?
 */

import java.io.*;
import java.util.*;

public class j_3584 {
    static int[] edge = new int[10001];
    static boolean[] isAncester = new boolean[10001];

    final static int INF = Integer.MAX_VALUE;

    // A, B의 최소 공통 조상 노드를 찾은 다음, 이를 리턴하는 함수.
    static int solution(int A, int B, int N) {
        int ans, next;
        
        Arrays.fill(isAncester, false);
        next = A;
        isAncester[next] = true;
        // A 찾아서 aAncester 업데이트
        while (edge[next] != 0) {
            next = edge[next];
            isAncester[next] = true;
        }

        // B의 경우 조상으로 거슬러 올라가면서, isAncester와 만날 경우(INF가 아닐 경우) 이를 담고, 리턴.
        next = B;
        while (!isAncester[next]) {
            next = edge[next];
        }
        ans = next;
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int N, A, B;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            Arrays.fill(edge, 0);

            for (int i = 0; i < N - 1; i++) { //트리이므로, N - 1개만큼 간선이 존재한다.
                st = new StringTokenizer(br.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                
                edge[B] = A; //index - 자식, 값 - 부모
            }
            // 최소 공통 조상을 찾을 두 노드
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append(solution(A, B, N)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
