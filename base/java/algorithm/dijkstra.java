package base.java.algorithm;
/*
 * 다익스트라 구현
 * 
 * 1번 노드에서 시작해 다른 노드로 가는 최소값을 구하는 알고리즘
 * 여기 구현의 경우 음수 간선 허용, 우선순위 큐를 활용해 문제를 푸는 문제임.
 * 다만, 음수간선이면서, 사이클이 발생해 사이클 발생때마다 값이 계속 줄어든다면
 * 성립하지 않음.
 * 이럴 경우, 벨만 포드 알고리즘을 사용하는 것을 추천함.
 */

import java.util.*;

public class dijkstra {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    static int[] dijkstra(List<List<Edge>> edges, int start) {
        int[] costs = new int[edges.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight); //최소힙

        Arrays.fill(costs, INF);

        costs[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();

            int now = tmp.to;
            int nowWeight = tmp.weight;

            // 현재 도착 값이 costs보다 크다면 continue
            if (nowWeight > costs[now]) continue;
            for (Edge edge : edges.get(now)) {
                int next = edge.to;
                int nextWeight = edge.weight;

                if (costs[now] + nextWeight < costs[next]) {
                    costs[next] = costs[now] + nextWeight;
                    pq.add(new Edge(next, costs[next])); // 다음 노드까지 가는 값을 가짐.
                }
            }
        }

        return costs;
    }
}
