package sw_academy.java;
import java.io.*;
import java.util.*;

/*
 * 치즈도둑
 * 
 * 문제 요약
 * 치즈 덩어리 개수 카운팅
 * 
 * 문제 풀이
 * 100일(0일 포함) 치즈의 덩어리 개수를 구하는 문제
 * visited 배열을 두개 사용해 요정이 먹은 치즈와 방문한 배열을 따로 사용
 * 한 덩어리를 판별할 때 bfs를 이용함. 이후 전체 스캔하며 방문하지 않았다면 다시 bfs 시작
 * 
 * 주의점
 * 0일째에도 카운팅이 필요함.
 */

public class j_7733 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] arr;
    static boolean[][] visited;
    static boolean[][] ate;
    static int N;
    static Deque<int[]> queue = new ArrayDeque<>();

    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y>= 0 && y < N;
    }

    static boolean isGoodToGo(int x, int y) {
        return !visited[y][x] && !ate[y][x];
    }

    static void bfs(int x, int y) {
        int nx, ny;
        int[] now;

        queue.push(new int[]{x, y});
        visited[y][x] = true;
        while (!queue.isEmpty()) {
            now = queue.pollFirst();
            x = now[0]; y = now[1];

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i]; ny = y + dy[i];
                if (isInside(nx, ny) && isGoodToGo(nx, ny)) {
                    visited[ny][nx] = true;
                    queue.addLast(new int[]{nx, ny});
                }
            }
        }
    }

    static int solution() {
        int ans = Integer.MIN_VALUE;
        ate = new boolean[N][N];
        int group;

        for (int step = 1; step <= 100; step++) {
            group = 0;
            visited = new boolean[N][N];

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (arr[y][x] == step) ate[y][x] = true;
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (isGoodToGo(x, y)) {
                        bfs(x, y);
                        group++;
                    }
                }
            }
            ans = Math.max(ans, group);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.printf("#%d %d\n", i, solution());
        }
    }
}