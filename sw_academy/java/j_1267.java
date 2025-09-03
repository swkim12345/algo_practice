package sw_academy.java;

/*
 * 10일차 - 작업순서
 * 
 * 문제 해설
 * 위상정렬을 통해 가능한 작업순서를 제시하는 문제
 * 
 * 문제 풀이
 * bfs를 이용해 풀자. dfs도 가능한데, bfs쪽이 더 낫게 생각할 수 있을 듯 하다.
 * node에 cnt(자신에 들어오는 간선 개수), 다음 간선 - arrayList 번호 삽입
 * 이를 배열로 관리하자. 정점 개수는 정해져있으므로, V(V는 만큼 초기화하는 방식으로 문제해결하자.
 * 
 * 주의사항
 * 테케 개수는 정해져있음. 정점은 1000개 이하. 정점은 1부터 카운팅됨.
 */

import java.io.*;
import java.util.*;

public class j_1267 {
    static int V, E;
    static List<Node> graph;
    static boolean[] visited = new boolean[1001];

    static class Node {
        int cnt;
        List<Integer> edges;
        Node(int cnt) {
            this.cnt = cnt;
            edges = new ArrayList<>();
        }
    }

    static void solution(int testCase) {
        StringBuilder sb = new StringBuilder();
        Deque<Node> queue = new ArrayDeque<>();

        sb.append("#").append(testCase).append(" ");

        for (int i = 1; i <= V; i++) {
            if (graph.get(i).cnt == 0 && visited[i] == false) {
                queue.addLast(graph.get(i));
                visited[i] = true;
                sb.append(i).append(" ");
                break;
            }
        }

        Node node, dst;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                node = queue.pollFirst();

                for (Integer edge : node.edges) {
                    dst = graph.get(edge);
                    dst.cnt--;
                    if (dst.cnt == 0) {
                        queue.addLast(dst);
                        visited[edge] = true;
                        sb.append(edge).append(" ");
                    }
                }
            }

            for (int i = 1; i <= V; i++) {
                if (graph.get(i).cnt == 0 && visited[i] == false) {
                    queue.addLast(graph.get(i));
                    visited[i] = true;
                    sb.append(i).append(" ");
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int testCase = 1; testCase <= 10; testCase++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            Arrays.fill(visited, false);

            graph = new ArrayList<>(V + 1);
            // node 초기화
            for (int i = 0; i <= V; i++) {
                graph.add(new Node(0));
            }

            st = new StringTokenizer(br.readLine());
            int u, v;
            for (int i = 0; i < E; i++) {
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                graph.get(u).edges.add(v);
                graph.get(v).cnt++;
            }

            solution(testCase);
        }
    }
}
