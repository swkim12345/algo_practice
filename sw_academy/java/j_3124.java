package sw_academy.java;

/*
 * 최소 스패닝 트리
 * 
 * 문제 요약
 * 최소 신장 트리를 구하는 문제. 인접 리스트 형태로 주어짐.
 * 
 * 문제 풀이
 * 사실 e가 커서 이건 프림 알고리즘이 낫지만, 크루스칼로 풀라고 했으니 크루스칼로 풀어야지 뭐..
 * 
 * 주의사항
 * 정점 번호는 1번부터 시작임.
 * 100만 * 20만 -> 2000억이라 int 범위 초과됨 (weight)
 */

import java.io.*;
import java.util.*;

public class j_3124 {
    static Edge[] edges;
    static int[] parents;
    static boolean[] visited;

    static class Edge implements Comparable<Edge> {
        int start, end;
        long weight;
        Edge(int start, int end, long weight) {
            this.start= start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.weight, e.weight);
        }
    }

    static void make(int N) {
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        parents[b] = a;
        return true;
    }

    static long solution(int E) {
        int cnt = 0; long sumWeight = 0;

        for (Edge e : edges) {
            if (!union(e.start, e.end)) continue;
            sumWeight += e.weight;
            cnt += 1;

            if (cnt + 1 == E) break;
        }

        return sumWeight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int V, E;

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edges = new Edge[E];
            parents = new int[V + 1];

            int a, b, c;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                edges[i] = new Edge(a, b, c);
            }

            // 초기화
            Arrays.sort(edges);
            make(V);

            System.out.printf("#%d %d\n", testCase, solution(E));
        }
        
    }
}
