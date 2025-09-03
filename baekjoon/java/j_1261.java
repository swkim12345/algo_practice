/*
 * 알고스팟
 * 
 * 문제요약
 * 1, 1에서 N,M까지 이동할 때 벽을 최소한으로 부술 때 몇개를 부셔야 하는 지 구하는 프로그램 만들기
 * 
 * 문제 풀이
 * 0 - 1 다익스트라. 이동 가능 위치는 격자그래프이므로 4방향 모두 가능.
 * visited를 이용해 이전에 이미 방문한 노드의 경우 스킵
 * 간선비용을 Step class에 추가하면서 이동처리
 * 만약 N, M에 도달하면 간선비용을 리턴하는 다익스트라 구현
 * dijkstra() - 리턴 간선비용
 * 
 * N, M, 미로 전역변수, step - x, y, cost
 * 주의
 * 1,1 시작 - N, M 도착, 그냥 arr 사이즈를 하나 더 크게 만들자.
 */
import java.io.*;
import java.util.*;

public class j_1261 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int[][] arr;
    static boolean[][] visited;
    static int N, M;

    static class Step {
        int x, y, cost;

        Step(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine().trim();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        int ans = 0;
        PriorityQueue<Step> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        q.add(new Step(1, 1, 0));
        visited[1][1] = true;

        while (!q.isEmpty()) {
            Step step = q.poll();

            // 기저조건 - N, M 도달
            if (step.x == M && step.y == N) {
                ans = step.cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = step.x + dx[i];
                int ny = step.y + dy[i];

                if (!isInside(nx, ny) || visited[ny][nx]) continue;
                q.add(new Step(nx, ny, step.cost + arr[ny][nx]));
                visited[ny][nx] = true;
            }
        }
        return ans;
    }

    static boolean isInside(int x, int y) {
        return x >= 1 && x <= M && y >= 1 && y <= N;
    }
}
