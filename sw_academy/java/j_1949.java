package sw_academy.java;

/**
 * 등산로 조성
 * 
 * 문제 요약
 * 내려가는 등산로 만들기
 * 가장 높은 봉우리부터 시작
 * 최장 길이가 정답
 * 등산로를 만들 때 특정 점 한 점을 낮출 수 있음.
 * 
 * 문제 풀이
 * 첫번째 입력에서 최대값을 저장하고, 다음 순회때 이 곳에서 시작하는 백트래킹 있는 
 * dfs를 돌린다. 매 사이클마다 ans 체크해서 업데이트
 * 전역변수
 * ans
 * arr
 * 
 */
import java.io.*;
import java.util.*;

public class j_1949 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static boolean[][] visited;
    static int[][] arr;
    static int ans, N, K;

    static class Step {
        int x, y, len, diff;
        boolean isUsed;

        Step(int x, int y, int diff, int len, boolean isUsed) {
            this.x = x;
            this.y = y;
            this.diff = diff;
            this.len = len;
            this.isUsed = isUsed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;

            arr = new int[N][N];
            visited = new boolean[N][N];

            int mx = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    mx = Math.max(mx, arr[i][j]);
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == mx) {
                        visited[i][j] = true;
                        dfs(j, i, 0, 1, false);
                        visited[i][j] = false;
                    }
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }

    static void dfs(int x, int y, int diff, int len, boolean isUsed) {
        ans = Math.max(len, ans);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (!isInside(nx, ny) || visited[ny][nx]) continue;
            visited[ny][nx] = true;
            
            int now = arr[y][x], next = arr[ny][nx];
            if (now - diff > next) {
                dfs(nx, ny, 0, len + 1, isUsed);
            }
            else if (!isUsed && now > next - K) {
                dfs(nx, ny, next - now + 1, len + 1, true);
            }
            visited[ny][nx] = false;
        }
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}