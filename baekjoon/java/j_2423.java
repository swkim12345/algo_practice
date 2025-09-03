/*
 * 전구를 켜라
 * 
 * 경로 하나만 만들면 되는 문제.
 * 모든 타일은 두개의 마주보는 꼭짓점이 전선으로 연결되어 있음
 * 그럼 2차원 좌표에서 현재 연결되어 있는 두 꼭짓점은 0, 아니면 1이라고 가중치를 잡고, 
 * 이를 다익스트라로 돌려버리면 되는 거 아님?
 * 
 * 현재 전원 - (0, N)
 * 현재 전구 - (M, 0)
 * 전원부터 시작해 전구까지 가는 다익스트라를 만들면 되는 문제임.
 * 좀 더 단순화 -> 2차원 배열을 결국 1차원 배열에 할당할 수 있음.
 * idx -> y = idx / (M + 1), x = idx % (M + 1). 
 * 결국 이를 통해 우리가 아는 다익스트라 문제로 단순화해 풀수가 있게 됨.
 * 전원 -> y = idx / (M + 1), y -> N => idx = (M + 1) * N
 * 전구 -> y = idx / (M + 1) y -> 0, x = idx % (M + 1) => idx = M
 * idx 증가시 x좌표 기준으로 +1과 동일, M+1의 배수가 될 때 마다 y + 1임.
 * 
 * 생각하기 편하게 하기 위해 String을 담는 배열을 사용해 역으로 계산하자.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class j_2423 {
    static class Edge {
        int to, weight;

        Edge (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M;
    final static int INF = Integer.MAX_VALUE;

    static int[] dijkstra(List<List<Edge>> edges, int start) {
        int[] costs = new int[(N + 1) * (M + 1)];
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
                    pq.add(new Edge(next, costs[next]));
                }
            }
        }

        return costs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Edge>> edges = new ArrayList<>();
        Deque<String> inputs = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int start = (M + 1) * N, end = M;

        for (int i = 0; i < (N + 1) * (M + 1); i++) {
            edges.add(new ArrayList<>());
        }

        // 초기화 : stack에 삽입, 나중에 pop하면서 0,0부터 채워나갈 예정임.
        for (int i = 0; i < N; i++) {
            inputs.addLast(br.readLine());
        }

        int lowerIdx = 0, upperIdx = M + 1;

        for (int i = 0; i < N; i++) {
            String input = inputs.pollLast();

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == '/' ) {
                    // 바로 연결
                    edges.get(lowerIdx).add(new Edge(upperIdx + 1, 0));
                    edges.get(upperIdx + 1).add(new Edge(lowerIdx, 0));

                    // 한번 상태를 바꿔야 함.
                    edges.get(lowerIdx + 1).add(new Edge(upperIdx, 1));
                    edges.get(upperIdx).add(new Edge(lowerIdx + 1, 1));
                } else {
                    // 한번 상태를 바꿔야 함.
                    edges.get(lowerIdx).add(new Edge(upperIdx + 1, 1));
                    edges.get(upperIdx + 1).add(new Edge(lowerIdx, 1));

                    // 바로 연결
                    edges.get(lowerIdx + 1).add(new Edge(upperIdx, 0));
                    edges.get(upperIdx).add(new Edge(lowerIdx + 1, 0));
                }
                lowerIdx ++;
                upperIdx ++;
            }
            lowerIdx ++;
            upperIdx ++;
        }

        // 우리가 익히 아는 다익스트라, start는 lowerIdx와 동일한 상태가 됨. 하지만 따로 구해줌.
        int[] costs = dijkstra(edges, start);

        if (costs[end] == INF) {
            System.out.println("NO SOLUTION");
        } else {
            System.out.println(costs[end]);
        }
    }
}
