/*
 * ABCDE
 * 
 * 문제 요약
 * A-B-C-D-E인 친구관계를 구하라.
 * 
 * 문제 풀이
 * 한점에서 시작하는 bfs를 만듦.
 * visited를 관리해 방문시 업데이트 아니면 그 정에서 다시 탐색을 한다.
 * 양방향 그래프이므로 방향은 고려할필요가 없다.
 */

import java.io.*;
import java.util.*;

public class j_13023 {
    static boolean[] visited;
    static List<List<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        System.out.println(solution(N));
    }

    // 백트래킹을 섞은 dfs
    static boolean dfs(int start, int step) {
        if (step == 5) return true;
        visited[start] = true;

        for (Integer e : edges.get(start)) {
            if (visited[e] == true) continue;
            if (dfs(e, step + 1)) return true;
        }
        visited[start] = false;
        return false;
    }

    static int solution(int N) {
        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (dfs(i, 1)) {
                ans = 1;
                break;
            }
        }
        return ans;
    }
}
