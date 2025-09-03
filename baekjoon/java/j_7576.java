/*
 * 토마토
 * 
 * 문제 요약
 * 하루가 지나면 익은 토마토 인접 익지않은 토마토 => 익은 토마토
 * 며칠 지나면 다 익게 되는 지 최소 일자 구하기
 * 
 * 문제 해결
 * bfs를 이용해 모든 곳에서 시작하는 bfs를 돌리면 되는 문제임.
 * 0일차 -> 1일차 넘어갈 때, 인접한 모든 익지 않은 토마토를 bfs에 삽입하면 되는 거임.
 * 1. 동일한 x, y좌표가 들어가는 경우? - 익지 않은 토마토가 같은 날짜에 익는 거임. 상관 없음.
 * 
 * 주의
 * 단순히 다 삽입하니깐 메모리 초과가 뜬다. day + 1일차에도 업데이트를 진행해주자.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class j_7576 {
    static final int EMPTY = -1;
    static final int NOTGOOD = 0;
    static final int GOOD = 1;
    static Deque<int[]> deque = new ArrayDeque<>(); // 0 - y, 1 - x, 2 - 날짜
    static int N, M;
    static final int[] dx = {0, 1, -1, 0};
    static final int[] dy = {1, 0, 0, -1};

    static boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
    
    static int bfs(int[][] arr) {
        int nx, ny, x, y, days = 0;
        int[] tmp;

        while (!deque.isEmpty()) {
            tmp = deque.pollFirst();
            y = tmp[0]; x = tmp[1]; days = tmp[2];
            // System.out.printf("%d %d %d\n", x, y, days);

            for (int i = 0; i < 4; i++) {
                nx = dx[i] + x;
                ny = dy[i] + y;

                if (!isInside(nx, ny)) continue;
                if (arr[ny][nx] == NOTGOOD)
                {
                    arr[ny][nx] = GOOD;
                    deque.addLast(new int[]{ny, nx, days + 1});
                }
            }
        }
        return days;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int ans;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ans = 0;

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 익은 토마토이면 바로 deque에 삽입
                if (arr[i][j] == GOOD) {
                    deque.addLast(new int[]{i, j, 0});
                }
            }
        }

        ans = bfs(arr);
        // 익지 않은 토마토 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == NOTGOOD) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(ans);
    }
}
