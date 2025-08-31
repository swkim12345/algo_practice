/*
 * 이분 그래프
 * 
 * 문제 요약
 * 그래프의 정점의 집합을 둘로 분할해 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 
 * 특별히 이분 그래프라고 부른다.
 * 그래프가 주어졌을 때, 이 그래프가 이분 그래프인지 아닌 지 판별하라.
 * 
 * 문제 풀이
 * dfs, bfs 풀이 모두 있을 거 같은데, 간단하게 풀 수 있는 dfs를 이용해 풀자.
 *
 * 사용 자료구조 및 알고리즘
 * List<List<Integer>> graph - 인접 리스트
 * int[] steps - V + 1 / 0은 미저장, 1 이상은 홀 / 짝 나눔
 * boolean dfs(v, step) - v는 방문 정점, true면 이분, false면 아님.
 */

import java.io.*;
import java.util.*;

public class j_1707 {
    static List<List<Integer>> graph;
    static int[] steps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            steps = new int[V + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int idx = 1;
            for (; idx <= V; idx++) {
                if (steps[idx] == 0) {
                    if (!dfs(idx, 1)) break;
                }
            }
            
            if (idx == V + 1) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean dfs(int idx, int step) {
        if (steps[idx] != 0) {
            if (steps[idx] % 2 == step % 2) return true; //기저조건 1 - 그냥 이분 그래프이자 방문한 노드
            else return false; // 기저조건 2 - 방문한 노드이자 이분그래프 아님
        }
        
        steps[idx] = step;
        List<Integer> edges = graph.get(idx);
        boolean isBipartiteGraph = true;
        for (Integer edge: edges) {
            if (!dfs(edge, step + 1)) {
                isBipartiteGraph = false;
                break;
            }
        }
        return isBipartiteGraph;
    }
}
