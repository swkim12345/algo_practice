/*
 * 미로 탐색
 * 
 * 문제 요약
 * 출발점에서 시작하는 bfs
 * 
 * 주의사항
 * 1,1 -> N, M이므로, -1을 해서 처리하자.
 * 수는 붙여서 입력으로 줌.
 */

import java.io.*;
import java.util.*;

public class j_2178 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static final int wall = 0;
    static final int good = 1;

    static int[][] arr;

    static class Point {
        int x, y, step;

        Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(solution(N, M));
    }

    static boolean isInside(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static int solution(int N, int M) {
        Queue<Point> queue = new ArrayDeque<>();

        Point p = new Point(0, 0, 1);
        queue.add(p);
        arr[0][0] = wall;

        while (!queue.isEmpty()) {
            p = queue.poll();
            // 종료 조건 - N -1, M - 1 도착
            if (p.x == M - 1 && p.y == N - 1) break;

            Point newP;
            int nx, ny;
            for (int i = 0; i < 4; i++ ){
                nx = p.x + dx[i]; ny = p.y + dy[i];
                if (isInside(nx, ny, N, M) && arr[ny][nx] == good) {
                    newP = new Point(nx, ny, p.step + 1);
                    queue.add(newP);
                    arr[ny][nx] = wall;
                }
            }
        }
        return p.step;
    }
}
