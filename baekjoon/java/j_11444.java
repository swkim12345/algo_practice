/*
 * 피보나치 수 6
 * 
 * 문제 요약
 * 피보나치 수를 구하는 것은 동일하지만, n의 범위가 100경이다.
 * 
 * 문제 풀이
 * 첫번째 아이디어.
 * 점화식의 경우는 행렬식으로 표현할 수 있다.
 * dp(x)        1 1 dp(x - 1)
 * dp(x - 1) =  1 0 dp(x - 2)
 * 이를 0부터 시작하는 점화식으로 바꿔보면..
 * 1 1 ** x - 1 dp(1)
 * 1 0          dp(0)
 * 
 * dp(0)은 0, dp(1)은 1이므로, 나온 결과 행렬식을 table이라고 하면
 * dp(x) = table[0][0]이 답이 된다.
 * 두번째 아이디어.
 * n의 범위가 100경이다보니, 이를 줄이기 위해 분할정복을 이용해 거듭제곱을 이용해 줄이자.
 */
import java.io.*;
import java.util.*;

public class j_11444 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long[][] arr = {
            {1, 1},
            {1, 0},
        };
        if (n <= 1) {
            System.out.println(n);
            return;
        }

        arr = dividePower(arr, n - 1);
        System.out.println(arr[0][0]);
    }

    static long[][] dividePower(long[][] arr, long n) {
        int N = arr.length;
        int M = arr[0].length;
        long[][] ans = new long[N][M];
        
        // 단위행렬로 초기화
        for (int i = 0; i < N; i++) {
            ans[i][i] = 1;
        }
        
        while(n > 0) {
            if (n % 2 == 1) {
                ans = power(ans, arr);
            }
            arr = power(arr, arr);
            n /= 2;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    ans[i][j] %= MOD;
                    arr[i][j] %= MOD;
                }
            }
        }
        return ans;
    }

    static long[][] power(long[][] left, long[][] upper) {
        int A = left.length;
        int B = upper.length;
        int C = upper[0].length;

        long[][] ans = new long[A][C];

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                for (int k = 0; k < C; k++) {
                    ans[i][k] += left[i][j] * upper[j][k];
                }
            }
        }
        return ans;
    }
}
