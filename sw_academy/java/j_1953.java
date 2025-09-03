/*
 * 탈주범 검거
 * 
 * 문제 요약
 * 탈주범은 시간당 1거리만큼 움직일 수 있음.
 * 터널 구조물이 7가지가 있고, 그 종류마다 연결하는 방향이 모두 다름
 * 지도, 맨홀뚜껑 위치, 시간이 주어질 때 위치할 수 있는 장소의 개수를 계산하라.
 * 
 * 문제 풀이
 * dx, dy를 두고, 1 ~ 7번까지 arr에 0, 1, 2, 3 등 갈 수 있는 방향을 모두 작성하는
 * 느낌으로 만들자.
 * boolean[][] visited를 사용해 어디 방문했는 지도 작성하고, 이를 통해 이미 간 곳은 가지 말자.
 * 마지막에는 visited 배열을 전체 순회하며 true인 곳을 카운팅하자.
 * 
 */
import java.io.*;
import java.util.*;

public class j_1953 {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    static final int[][] move = {
        {},
        {0, 1, 2, 3},
        {0, 2},
        {1, 3},
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 0},
    };
    static final int EMPTY = 0;

    static int N, M, R, C, L;
    static int[][] arr;
    static boolean[][] visited;

    static class Step {
        int x, y, step;

        Step(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) ans++;
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static void bfs() {
        Queue<Step> q = new ArrayDeque<>();

        q.add(new Step(C, R, 1));
        visited[R][C] = true;

        while (!q.isEmpty()) {
            Step step = q.poll();

            if (step.step == L) continue; // step이 같으면 스킵
            int[] dir = move[arr[step.y][step.x]];

            for (int d : dir) {
                int nx = step.x + dx[d];
                int ny = step.y + dy[d];
                
                if (!isInside(nx, ny) || arr[ny][nx] == EMPTY || visited[ny][nx]) continue;

                int target = arr[ny][nx];
                // 상우하좌
                switch (d) {
                    case 0:
                        if (target == 3 || target == 4 || target == 7) continue;
                        break;
                    case 1:
                        if (target == 2 || target == 4 || target == 5) continue;
                        break;
                    case 2:
                        if (target == 3 || target == 5 || target == 6) continue;
                        break;
                    case 3:
                        if (target == 2 || target == 6 || target == 7) continue;
                        break;
                }

                visited[ny][nx] = true;
                q.add(new Step(nx, ny, step.step + 1));
            }
        }
    }
}
