/*
 * 사이클 없는 그래프 만들기
 * 
 * 문제 설명
 * 모든 정점이 연결된 그래프
 * 지워진 정점을 이용해 사이클이 없어지는 C 시간 구하기.
 * 
 * 문제 해설
 * 1. 처음에는 지울 정점을 바탕으로 bfs를 돌린다.
 * 1-1. 정점이 지워진 순서대로 엣지를 삽입한다.
 * 2. 
 * 
 * 주의 사항
 * 정점은 1부터 시작임.
 * K가 2 이상일 때 제대로 작동되지 않음
 * 
 * 틀린 부분
 * 처음 접근때에는 노드를 기반으로 stack에 넣었다. 이로 인해 상호간의 엣지가 있는 지 판단하는 것에서 문제가
 * 발생했었다. 엣지를 stack에 push하는 방식으로 변경해 문제를 해결할 수 있었다.
 * 
 * 막히는 부분
 * bfs로 처음에 엣지를 접근하는 데, 접근할 때 visited를 검사하지 않아야지 사이클이 발생되는 엣지가 들어간다.
 * 양방향이다보니 두 방향에서 접근하는 같은 엣지 그래프가 있을 수 있다.
 */

import java.io.*;
import java.util.*;

public class j_30870 {
    static Deque<Edge> queue = new ArrayDeque<>();
    static Deque<Edge> stack = new ArrayDeque<>();
    static List<Set<Integer>> graph = new ArrayList<>();
    static int[] parents; // 부모 저장
    static boolean[] visited;

    static int N, M, K;

    static class Edge {
        int src, dst, step;

        Edge(int src, int dst, int step) {
            this.src = src;
            this.dst = dst;
            this.step = step;
        }
    }

    static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    // 사이클 판별용 변형된 union
    static boolean union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) {
            return true;
        }

        parents[b] = a;
        return false;
    }

    static int solution() {
        // 1단계 - 삭제하는 정점을 queue로 순회하고, pop된 것을 바탕으로
        // 엣지를 stack에 삽입한다. 시간은 T 시간 삽입
        Iterator<Integer> edges;
        int nextStep, src, dst;
        Edge edge;
        int ans = 1;

        while(!queue.isEmpty()) {
            edge = queue.pollFirst();
            nextStep = edge.step + 1;
            src = edge.dst;

            edges = graph.get(src).iterator();
            while(edges.hasNext()) {
                dst = edges.next();
                
                stack.addLast(new Edge(src, dst, nextStep));
                graph.get(dst).remove(src); //돌아오는 엣지 삭제
                if (visited[dst] == false)
                    queue.addLast(new Edge(src, dst, nextStep));
                visited[dst] = true;
                System.out.printf("visit edge : %d %d %d\n", src, dst, nextStep);
            }
        }

        // 2단계 stack에서 pop하면서 union한다.
        // union 시 사이클이 완성되는 시점이 없어지는 시간과 동일함.
        // 가중치가 높은 - time을 기준으로 parent를 가져야함
        while (!stack.isEmpty()) {
            edge = stack.pollLast();
            
            System.out.printf("pop edge: %d %d %d,%d\n", edge.src, edge.dst, edge.step, parents[edge.src]);
            if (union(edge.src, edge.dst)) {
                ans = edge.step;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new HashSet<>());
            parents[i] = i;
        }

        int u, v;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // init
        int start;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            start = Integer.parseInt(st.nextToken());
            queue.addLast(new Edge(0, start, 0));
            visited[start] = true;
        }

        System.out.println(solution());
    }
}