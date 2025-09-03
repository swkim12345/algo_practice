/*
 * 튜터-튜티 관계의 수
 * 문제 요약
 * 관계가 양방향 그래프이자 포레스트
 * 친분관계 끼리 튜터-튜티가 될 수 있음. 모든 교육생에게 교육자료를 전달해야 할 때, 최초로 교육자료를 받는 인원이 최소화가 되게
 * 하는  경우의 수를 1 000 000 007로 나눈 나머지 구하기
 * 
 * 문제 해결
 * union-find를 사용해 엣지가 추가되면 union이라 생각하고 합쳐주면 되는 문제임.
 * 다 풀고 나면, 교육생을 순회하며 (트리의 최상위 노드의 cnt) * (다른 최상위 노드의 cnt)..을 한다.
 * 이게 되는 이유는 트리는 노드간 간선이 하나밖에 없어 튜터를 하나만 고르면, 나머지 튜터-튜티 관계는 결정되기 때문이다.
 * (최초로 교육자료를 받는 인원 최소화)
 * 이를 나머지로 나눠가며 다 순회하면 출력하면 끝.
 * 
 * 주의사항
 * 나머지를 계속 나눠줘야 함. 교육생 번호는 1번부터임.
 * 이 그래프는 양방향 그래프임. 트리를 만드려면 먼저 단방향 조건을 설정해야 함.
 * 나의 경우 부모 노드는 무조건 나보다 작다고 가정함.
 */

import java.io.*;
import java.util.*;

public class j_24542 {
    static int[] graph;
    static long[] count;
    static Set<Integer> parents = new HashSet<>();
    
    static final int DIVISOR = 1000000007;

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        graph[b] = a;
        count[a] += count[b];
        if (parents.contains(b)) parents.remove(b);
    }

    static int find(int node) {
        if (node == graph[node]) return node; // 부모가 자기 자신일 때

        return graph[node] = find(graph[node]); //path compression
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int u, v;
        graph = new int[N + 1];
        count = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = i; // 자기 자신 초기화
            parents.add(i); // parent 추가
            count[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            union(u, v);
        }

        long ans = 1;
        for (Integer parent : parents) {
            ans = (ans * count[parent]) % DIVISOR;
        }
        System.out.println(ans);
    }
}
