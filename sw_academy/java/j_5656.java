package sw_academy.java;
/*
 * 벽돌 깨기
 * 
 * 문제 요약
 * 구슬은 맨 위에 벽만 깸. 벽돌은 1 - 9까지고, 한번에 9짜리까지 깰 수 있음.
 * 깰 때, 상하좌우로 모두 깨지고, 그 사이에 벽돌이 있다면 그 벽돌에서 시작하는 1-9짜리 십자가로도 같이 터짐
 * 터질 때는 한번에 없어지고, 남은 벽돌은 위에서 아래로 내려옴.
 * 
 * 문제 풀이
 * 벽돌을 저장하는 어레이 하나
 * 벽돌 방문했는 지 확인하는 visited[][] 하나
 * 삭제할 벽돌을 저장하는 큐 하나
 * 
 * 1. 위에서 첫번째 벽돌을 찾음
 * 2. 첫번째 벽돌에서 시작하는 십자가 시작 - 격자그래프 탐색과 동일하게 처리
 * 2-1. visited[][]가 false + 벽돌 있으면 true 처리, 삭제할 벽돌 저장에 넣고, true면 스킵
 * 2-2. 큐 pop해서 2부터 다시 시작
 * 3. queue에 아무것도 없으면 해당되는 칸 모두 0으로 바꿈
 * 4. 새로운 어래이를 만들고, y 마지막 칸에서 첫번째 칸으로 올라가면서 벽돌이 있으면 어래이에 적재
 * 5. 새로운 어레이를 벽돌을 저장하는 어레이에 갱신
 * 
 * 이 짓을 W개에 대해 각각 진행하고, 진행할 때마다 배열을 복사해 넘겨줌.
 * 
 * 이 위의 짓을 N 개수만큼 진행하고, 마지막에 도달했을 경우 총 삭제 개수를 전역변수에 업데이트
 * 
 * 틀리는 이유
 * 단어가 혼용됨, 디버깅용 요소가 없음. 템플릿으로 외우는 것이 없다.
 */

import java.io.*;
import java.util.*;
import java.util.Queue;
import java.awt.*;

public class j_5656 {
    static final int EMPTY = 0;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int ans;
    static int N, W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            int[][] arr = new int[H][W];

            for (int i = H - 1; i >= 0; i--) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution(0, arr);
            System.out.printf("#%d %d\n", testCase, ans);
        }
    }

    static int[][] copy(int H, int W, int[][] arr) {
        int[][] newArr = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
    static boolean isInside(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    static void solution(int step, int[][] arr) {
        if (ans == 0) return; // 중단조건

        if (step == N) {
            int sm = 0;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (arr[i][j] != EMPTY) sm++;
                }
            }

            ans = Math.min(ans, sm);
            return;
        }

        for (int i = 0; i < W; i++) {
            int x, y;
            // 가장 첫번째 찾기
            for (y = H - 1; y >= 0; y--) {
                if (arr[y][i] != EMPTY) break;
            }
            if (y == -1) y = 0; // 스킵
            x = i;

            Queue<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[H][W];
            queue.add(new Point(x, y));
            visited[y][x] = true;

            while(!queue.isEmpty()) {
                Point p = queue.poll();
                int range = arr[p.y][p.x];

                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x;
                    int ny = p.y;
                    for (int stepLen = 1; stepLen < range; stepLen++) {
                        nx += dx[dir];
                        ny += dy[dir];
                        if (!isInside(nx, ny)) break;

                        if (!visited[ny][nx]) {
                            visited[ny][nx] = true;
                            if (arr[ny][nx] != EMPTY) {
                                // 벽돌이면 연쇄 전파를 위해 큐에 추가
                                queue.add(new Point(nx, ny));
                            }
                            // 빈칸이어도 직진은 계속해야 하므로 여기서 멈추지 않음
                        }
                    }
                }
            }

            int[][] newArr = copy(H, W, arr);

            for (int a = 0; a < H; a++) {
                for (int b = 0; b < W; b++) {
                    if (visited[a][b] == true) {
                        newArr[a][b] = EMPTY;                        
                    }
                }
            }

            for (int b = 0; b < W; b++) {
                int h = 0;
                for (int a = 0; a < H; a++) {
                    if (newArr[a][b] != EMPTY && h < a) {
                        newArr[h++][b] = newArr[a][b];
                        newArr[a][b] = EMPTY;
                    }
                    
                    while(isInside(b, h) && newArr[h][b] != EMPTY) {
                        h++;
                    }
                }
            }

            solution(step + 1, newArr);
        }
    }
}
