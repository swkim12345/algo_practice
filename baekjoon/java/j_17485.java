/*
 * 진우의 달 여행(large)
 * 문제요약
 * 지구의 어느포인트에서든 출발해 달의 어느위치든 착륙하는 것임.
 * 3방향으로 갈 수 있음. 이 때, 전에 갔던 방향으로 갈 순 없음.
 * 최대한 돈을 아껴서 달에 도착하자.
 * 
 * 문제 풀이
 * 방향마다 하나의 배열로 관리할 수 있다.
 * 0 - 왼쪽 아래 1 - 아래, 2 - 오른쪽 아래
 * dp(x, y, 0) = min(dp(x, y - 1, 1), dp(x - 1, y - 1, 2));
 * dp(x, y, 1) = min(dp(x + 1, y - 1, 0), dp(x - 1, y - 1, 2))
 * dp(x, y, 2) = min(dp(x + 1, y - 1, 0), dp(x, y - 1, 1));
 * 음... 합치는 건 힘들듯..?
 * 주의사항
 * 0,0,0부터 시작해 달까지 연결할 수 있게 좀 더 넉넉하게 처리하자.
 */
import java.io.*;
import java.util.*;

public class j_17485 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 2][M + 2];
        int[][][] dp = new int[N + 2][M + 2][3];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 1; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = 1000000000;
                }
            }
        }

        for (int i = 1; i < N + 2; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j][0] = Math.min(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + arr[i][j];
                dp[i][j][1] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j - 1][2]) + arr[i][j];
                dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j][1]) + arr[i][j];
            }
        }

        int min = 1000000000;
        for (int j = 1; j < M + 1; j++) {
            for (int k = 0; k < 3; k++) {
                min = Math.min(min, dp[N + 1][j][k]);
            }
        }
        System.out.println(min);
    }    
}
