/*
 * 총깡 총깡
 * 1. A, B, AB 둘 다 아닌 집이 존재
 * 2. A, B 둘 중 하나에 총깡총깡 동물을 맡기고 싶음. (한마리씩)
 * 2-1. 한마리씩 못 맡기는 경우가 발생할 수 있음(도로로 연결되지 않을 경우)
 * 2-2. B가 안된다 -> A, a가 안됨 -> B , 둘다 안됨 -> -1 출력
 * 2-3. 모두 연결될 경우, A가 가까움 -> A 출력, B가 가까움 -> B 출력, 최소 거리를 나타내면 됨.
 * 
 * 해결 과정
 * 1. 다익스트라
 * 2. 다익스트라 이후 visited 확인 - 모두 방문시 상관 없음. A, B 둘 다 문제 발생시 - -1 출력, 얼리 리턴
 * 3. 노드 중 최소값 && a, b 둘 다 아닌 집을 확인해 최소값인 인덱스를 저장하고, 출력시 값과 인덱스를 출력한다.
 *
 * 오답노트
 * 거리가 동일할 경우 A, B 중 A에 살아야 함. 정렬이 필요할 듯.
 * 도달하지 못하는 경우를 고려하지 못했음.
 */

import java.io.*;
import java.util.*;

public class j_14618 {
    static class Edge {
        private int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    final static int INF = Integer.MAX_VALUE;
    final static int A = 1;
    final static int B = 2;
    final static int notAB = 0;
    static int N, M, K, start, tmp, x, y, z;

    static int[] dijkstra(List<List<Edge>> edges) {
        int[] costs = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        Arrays.fill(costs, INF);
        costs[start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            int weight = edge.weight;

            visited[to] = true;
            if (costs[to] < weight) continue;
            for (Edge e : edges.get(to)) {
                if (weight + e.weight < costs[e.to]) {
                    costs[e.to] = weight + e.weight;
                    pq.add(new Edge(e.to, costs[e.to]));
                }
            }
        }

        return costs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] houses, costs;
        List<List<Edge>> edges = new ArrayList<>();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        houses = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>()); 
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            tmp = Integer.parseInt(st.nextToken());
            houses[tmp] = A;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            tmp = Integer.parseInt(st.nextToken());
            houses[tmp] = B;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            edges.get(x).add(new Edge(y, z));
            edges.get(y).add(new Edge(x, z));
        }

        costs = dijkstra(edges);

        int valA = INF, valB = INF;

        for (int i = 1; i <= N; i++) {
            if (houses[i] == A) {
                valA = Math.min(valA, costs[i]);
            }
            if (houses[i] == B) {
                valB = Math.min(valB, costs[i]);
            }
        }

        if (valA == INF && valB == INF) {
            System.out.println("-1");
        } else {
            System.out.println(valA > valB ? "B" : "A");
            System.out.println(valA > valB ? valB : valA);
        }
    }
}
