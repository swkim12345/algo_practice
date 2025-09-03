package sw_academy.java;

/*
 * 활주로 건설
 * 
 * 문제 요약
 * 
 * 문제 풀이
 * 
 */

import java.io.*;
import java.util.*;

public class j_4014 {
    static final int DOWN = 0;
    static final int UP = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    static int N, X;
    static int[][] arr;
    static int[][][] diff;

    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            diff = new int[4][N][N];

            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine()); 
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int next = j + X;
                    
                    if (isInside(next, i))
                        diff[0][i][j] = arr[i][j] - arr[i][next];
                
                    next = j - X;

                    if (isInside(next, i))
                        diff[1][i][j] = arr[i][j] - arr[i][next];

                    next = i + X;

                    if (isInside(j, next))
                        diff[2][i][j] = arr[i][j] - arr[next][j];

                    next = i - X;
                    if (isInside(j, next))
                        diff[3][i][j] = arr[i][j] - arr[next][j];
                }
            }

            /*
             * 3가지 검증
             * 1. 차이가 1 이상으로 나면 안 됨.
             * 2. 만약 1이나, -1로 변경되었을 때 개수가 x개여야 함.
             * - 같은 수로 x개가 되면, 0으로 초기화 후 다시 카운팅 필요
             * - 미만인 데, 다른 수 (0 혹은 - + 1)로 바뀐다면 문제.
             * 3. 두개의 합이 2 이상이면 안 됨.
             */
            int ans = 2 * N;
            for (int i = 0; i < N; i++) {
                int[] before = {0, 0};
                int[] cnt = {0, 0};
                for (int j = 0; j < N; j++) {
                    // 1번 조건
                    if (Math.abs(diff[0][i][j]) > 1 || Math.abs(diff[1][i][j]) > 1) {
                        ans --;
                        break;
                    }

                    // 2번 조건
                    if (before[0] != diff[0][i][j]) {
                        if ((diff[0][i][j] == 0 || before[0] != diff[0][i][j]) && cnt[0] != X) {
                            ans--;
                            break;
                        }
                    }
                    before[0] = diff[0][i][j];
                    cnt[0] = (cnt[0] + 1) % X;

                    if (before[0] != diff[0][i][j]) {
                        if ((diff[0][i][j] == 0 || before[0] != diff[0][i][j]) && cnt[0] != X) {
                            ans--;
                            break;
                        }
                    }
                    before[0] = diff[0][i][j];
                    cnt[0] = (cnt[0] + 1) % X;
                    

                    // 3번 조건
                    if (diff[0][i][j] + diff[1][i][j] >= 2) {
                        ans --;
                        break;
                    }
                }
                // 마지막에도 2번 조건은 검사가 필수임.
                // 2번 조건
            }

            for (int j = 0; j < N; j++) {
                int[] before = {0, 0};
                int[] cnt = {0, 0};
                for (int i = 0; i < N; i++) {
                    // 1번 조건
                    if (Math.abs(diff[0][i][j]) > 1 || Math.abs(diff[1][i][j]) > 1) {
                        ans --;
                        break;
                    }

                    // 2번 조건
                    for (int k = 0; k < 2; k++) {
                        if (before[k] != diff[k][i][j]) {
                            if ((diff[k][i][j] == 0 || before[k] != diff[k][i][j]) && cnt[k] != X) {
                                ans--;
                                break;
                            }
                        }
                        before[k] = diff[k][i][j];
                        cnt[k] = (cnt[k] + 1) % X;
                    }

                    // 3번 조건
                    if (diff[0][i][j] + diff[1][i][j] >= 2) {
                        ans --;
                        break;
                    }
                }
                // 마지막에도 2번 조건은 검사가 필수임.
                // 2번 조건
                for (int k = 0; k < 2; k++) {
                    if (before[k] != diff[k][N - 1][j]) {
                        if ((diff[k][N - 1][j] == 0 || before[k] != diff[k][N - 1][j]) && cnt[k] != X) {
                            ans--;
                            break;
                        }
                    }
                    before[k] = diff[k][N - 1][j];
                    cnt[k] = (cnt[k] + 1) % X;
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
