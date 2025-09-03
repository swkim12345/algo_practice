package sw_academy.java;

/*
 * contact
 * 
 * 문제 요약
 * 방향 그래프. 연락이 닿이는 사람중에 가장 높은 사람 구하기
 * 
 * 문제 해결
 * bfs로 전체 스캔하면서, 가장 높은 숫자가 나오면 갱신하는 방식
 */

import java.io.*;
import java.util.*;

public class j_1238 {
    static Deque<int[]> queue = new ArrayDeque<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited = new boolean[101];

    static int bfs(int start) {
        int ans = Integer.MIN_VALUE;
        int step = 0;

        visited[start] = true;
        queue.addLast(new int[]{start, step});

        int dst, now;
        int[] target;
        List<Integer> edges;

        while (!queue.isEmpty()) {
            target = queue.pollFirst();
            dst = target[0]; now = target[1];

            if (now != step) { // 동일 단계에서 최대값을 찾기 위해
                step = now ;
                ans = Integer.MIN_VALUE;
            }
            ans = Math.max(ans, dst);
            // System.out.printf("%d %d %d %d \n", ans, dst, now, step);
            
            edges = graph.get(dst);
            for (Integer edge: edges) {
                if (visited[edge] == true) continue;
                visited[edge] = true;
                queue.addLast(new int[]{ edge, now + 1});
            }
        }
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        //System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= 100; i++) {
            graph.add(new ArrayList<>());
        }

        int len, start, u, v;
        for (int testCase = 1; testCase <= 10; testCase++) {
            Arrays.fill(visited, false);
            for (int i = 0; i <= 100; i++) {
                graph.get(i).clear();
            }

            st = new StringTokenizer(br.readLine());
            len = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len / 2; i++) {
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
            }

            sb.append(String.format("#%d %d\n", testCase, bfs(start)));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
