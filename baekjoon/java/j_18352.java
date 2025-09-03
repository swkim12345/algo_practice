/**
 * 특정 거리의 도시 찾기
 * 
 * 문제 요약
 * 단방향 그래프, X에서 출발해 도달할 수 있는 모든 도시에서 최단거리가 K인 모든 도시들의 번호를 출력함.
 * 오름차순으로 출력해야 해요.
 * 
 * 문제 풀이
 * bfs를 이용한 풀이, 사실 다익스트라도 되요.
 * 
 * 주의사항
 * 1부터 시작하는 도시 넘버
 */

import java.io.*;
import java.util.*;

public class j_18352 {
    static final int EMPTY = -1;
    static int N, M, K, X;
    static int[] steps;
    static List<List<Integer>> graph;

    static class Step {
        int idx, step;

        Step(int idx, int step) {
            this.idx = idx;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        steps = new int[N + 1];
        Arrays.fill(steps, EMPTY);
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
        }

        bfs(X);

        for (int i = 1; i <= N; i++) {
            if (steps[i] == K) {
                sb.append(i).append("\n");
            }
        }
        if (sb.length() == 0) sb.append(-1).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int X) {
        Queue<Step> queue = new ArrayDeque<>();

        queue.add(new Step(X, 0));
        steps[X] = 0;

        while(!queue.isEmpty()) {
            Step step = queue.poll();
            List<Integer> edges = graph.get(step.idx);

            if (step.step > K) break;
            for (Integer edge : edges) {
                if (steps[edge] != EMPTY) continue;
                steps[edge] = step.step + 1;
                queue.add(new Step(edge, step.step + 1));
            }
        }
    }
}