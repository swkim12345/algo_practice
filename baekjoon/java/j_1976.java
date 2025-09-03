/*
 * 여행 가자
 * 
 * 여행이 "가능한 지" 확인하는 프로그램
 * 여행경로, 최단 경로는 상관이 없음.
 * 따라서, 그래프가 연결된 것을 확인하기만 하면 됨 -> bfs, dfs, 유니온 파인드
 * 
 * 이 문제는 첫번째는 유니온파인드로 해결하자.
 * 
 * 오답노트
 * 종료조건일 때에는 반드시 return 넣어두자;;;;.
 */

import java.io.*;
import java.util.*;

public class j_1976 {
    static int[] node;
    static int find(int a) {
        // 자기 자신이 부모노드라면 리턴
        if (node[a] == a) return a;
        else {
            // 재귀를 통해 부모 노드로 compression
            return node[a] = find(node[a]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        node[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M, parent;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        node = new int[201];

        for (int i = 1; i <= N; i++) {
            node[i] = i;
        }

        // 연결 노드 표시 - union
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        parent = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            if (parent != find(Integer.parseInt(st.nextToken()))) {
                System.out.println("NO");
                return ;
            }
        }
        System.out.println("YES");
    }
}
