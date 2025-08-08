/*
 * 트리의 부모 찾기
 * 
 * 문제 요약
 * 루트 - 1, 트리, 노드의 부모 구하는 문제
 * 풀이
 * dfs 약간 변형해서 풀이, 기본 알고리즘인 dfs를 적고, 이를 바탕으로 풀이를 변형해서 푼다.
 * 주의점
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class j_11725 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] parents;

    static void dfs(int start, int parent) {
        visited[start] = true;
        parents[start] = parent;
        parent = start; // parent 갱신

        for (Integer val : graph.get(start)) {
            if (visited[val]) continue;
            dfs(val, parent);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int a, b;

        parents = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1, 0);

        // 답 출력
        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }
}
