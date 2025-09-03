/*
 * 지름길 - 다익스트라 풀이
 * 
 * 기존 풀이 방식은 dp임.
 * 다익스트라를 이용해 풀되, 푸는 방식은 외운 방식 그대로 풀기.
 * 다른 분 풀이 - 다익스트라
 * https://www.acmicpc.net/source/97075363
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class j_1446_dijkstra {
    static class Edge {
        public int to, weight;

        Edge (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    final static int INF = Integer.MAX_VALUE;

    static int[] dijkstra(List<List<Edge>> edges, int start, int D) {
        int[] costs = new int[D + 1];
        boolean[] visited = new boolean[D + 1]; //각 노드를 길이 0 ~ D 의 정수값으로 고려함

        Arrays.fill(costs, INF);
        costs[0] = 0;
        visited[0] = true; // 처음 노드 방문했다고 가정함
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            visited[cur.to] = true;
            if (costs[cur.to] < cur.weight) continue;
            for (Edge next: edges.get(cur.to)) {
                if (costs[next.to] > costs[cur.to] + next.weight) {
                    costs[next.to] = costs[cur.to] + next.weight;
                    pq.add(next);
                }
            }
        }
        return costs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, D, start, end, length;

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<List<Edge>> edges = new ArrayList<>();

        for (int i = 0; i <= D; i++) {
            edges.add(new ArrayList<Edge>());
            if (i < D) { // 이건 다음 노드와의 연결을 위해 추가적으로 삽입
                edges.get(i).add(new Edge(i + 1, 1));
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            length = Integer.parseInt(st.nextToken());
            
            // 도착지가 더 클 경우
            if (end > D) continue;
            else {
                edges.get(start).add(new Edge(end, length));
            }
        }
        int[] costs = dijkstra(edges, 0, D);
        System.out.println(costs[D]);
    }
}
