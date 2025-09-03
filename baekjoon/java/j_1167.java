/*
 * 트리의 지름
 * 
 * 문제 요약
 * 트리에서 두 점끼리의 거리가 가장 긴 값을 출력하시요
 * 
 * 문제 풀이
 * 1. 한 점을 정점으로 가지는 트리를 먼저 만들어야 함.
 * 1-1 만들 때, 엣지는 두번 제시(양방향)이므로, 단방향 정보(작은 점 -> 큰점)만 저장한다.
 * 1-2. 저장할 때, 간선 list, 노드 list를 따로 가지자. 여기서는 간선만 업데이트
 * 
 * 2-1. 1 시작하는 dfs를 굴림. 만약 리프노드에 도달했다면, 백트래킹
 * 2-2. 백트래킹 되면서 리턴된 값 + dfs의 간선 정보를 childWeight에 업데이트
 * 2-3. 한 노드에서 모두 dfs가 되었다면, maxchildweight를 childwegith 값 중 최대값으로 결정하고, maxchildweigth
 * 를 리턴. 리턴하면서 전역변수 ans값과 비교해 갱신
 * 2-4. 모두 dfs가 끝났다면 종료 후 ans 출력
 * 
 * 풀면서 수정한 점
 * 단뱡항 그래프로 만드는 경우가 잘못됨. 처음엔 양방향으로 만들고, 부모 노드로 돌아가지 않게 막아야 함.
 * visited를 쓸수도 잇고 dfs를 이용해 넘겨주는 방식도 있겠지만 나의 경우는 dfs로 부모 노드를 넘겨줌.
 * 
 * 주의
 * 1부터 시작함.
 */

import java.io.*;
import java.util.*;

public class j_1167 {
    static Node[] nodes;
    static List<List<Edge>> graph;
    static int ans;

    static class Node {
        int maxWeight;
        List<Integer> childWeight;

        Node () {
            maxWeight = 0;
            childWeight = new ArrayList<>();
        }
    }

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ans = Integer.MIN_VALUE;
        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList<>(V + 1);
        nodes = new Node[V + 1];
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
            nodes[i] = new Node();
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());

                graph.get(node).add(new Edge(to, weight));
            }
        }

        // 이거 순서가 중요함. ans
        ans = Math.max(dfs(1, 0), ans);

        System.out.println(ans);
    }

    static int dfs(int to, int parent) {
        List<Edge> edges = graph.get(to);
        Node node = nodes[to];

        for (Edge edge: edges) {
            if (parent == edge.to) continue; // 부모 노드 돌아가는 것 제외.
            node.childWeight.add(edge.weight + dfs(edge.to, to));
        }

        if (node.childWeight.isEmpty()) return 0;

        // 1개만 있을 경우 - 바로 리턴
        if (node.childWeight.size() == 1) return node.childWeight.get(0);

        // 2개 이상 있을 경우 - 정렬 후 이 노드를 경유해 최대가 되는 간선으로 업데이트
        node.childWeight.sort(Collections.reverseOrder());
        ans = Math.max(ans, node.childWeight.get(0) + node.childWeight.get(1));

        // 이 노드를 경유해 자손으로 갈 때 최대값
        node.maxWeight = node.childWeight.get(0);
        return node.maxWeight;
    }
}
