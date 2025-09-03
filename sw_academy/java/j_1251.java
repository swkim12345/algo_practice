package sw_academy.java;

/*
 * 4일차 - 하나로
 * 
 * 문제 요약
 * 모든 섬을 연결하는 해저터널 건설
 * x,y좌표로 제공되고, 길이는 유클리디안
 * 환경부담금 - E는 고정, 길이는 유동 -> 길이 최소한
 * 따라서, 최소신장트리를 구현하는 문제.
 * 답의 경우, 환경부담금을 구한 뒤, 소수점 첫째자리에서 반올림한 값을 담아 출력.
 * 
 * 문제 해결
 * 이번에는 크루스칼 알고리즘을 이용해 구현하자.
 * 점을 처음에 모두 저장하고, 순열을 이용해 점간의 거리를 edge 배열에 담자.
 * 담을 때, edge 개수는 v * (v - 1)개만큼 나오니 이만큼 double[] 에 담아두자.
 * 
 * 주의사항
 * 환경부담금의 경우 소수점 첫째자리에서 반올림한 값을 담아 출력하자.
 * double, long을 사용해 구현해야함. 아니면 범위 초과가 나게 된다.
 * 
 * 막히는 부분
 * 크루스칼을 사용하려면 엣지를 구해야하는 데, 엣지를 구할 때 점이 1000개라 총 100만개가 나오게 된다.
 * ... 괜찮나..?
 * - 테케가 20개이므로, 2000만개를 선택하고, 크루스칼의 시간복잡도는 O(ElogE) (정렬시)이므로 넉넉하다.
 * 애초에 자바 기준 20초임;
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class j_1251 {
    static Point[] points;
    static Edge[] edges;
    static int[] parents;
    static boolean[] visited;

    static class Edge implements Comparable<Edge>{
        int start, end;
        double weight;

        Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }
    }

    static void make(int N) {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    // 약간 변형된 union - 사이클 판별
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        parents[b] = a;
        return true;
    }

    static double euclidianDstPow(Point start, Point end) {
        double ret = Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2);
        return ret;
    }

    static long solution(int N) {
        long totalDst = 0;
        int cnt = 0;

        make(N);
        Arrays.sort(edges);

        for (Edge e :edges) {
            if (!union(e.start, e.end)) continue;
            totalDst += e.weight;
            cnt += 1;
            if (cnt + 1 == N) { // 기저조건 : 간선 선택 + 1이 N개가 되기 전까지
                break;
            }
        }

        return totalDst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int N, x, y;
        double E;
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());

            points = new Point[N];
            edges = new Edge[N * (N - 1) / 2];
            parents = new int[N];
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, 0);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y = Integer.parseInt(st.nextToken());
                points[i].y = y;
            }

            // edges 만들기 - 조합
            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    edges[idx++] = new Edge(i, j, euclidianDstPow(points[i], points[j]));
                }
            }

            E = Double.parseDouble(br.readLine());

            System.out.printf("#%d %d\n", testCase, Math.round(solution(N) * E));
        }
    }
}
