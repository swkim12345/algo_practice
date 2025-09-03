/*
 * 객실 배치
 * 
 * 문제 요약
 * 한층에 2개 객실, 층은 N층짜리 호텔
 * 총 3가지 선택지 - 1, 2, 없음 선택
 * 다만, 1을 선택시 다음 층에서는 2, 없음 선택
 * 반대로 2 선택시 다음 층에서는 1, 없음 선택
 * 
 * 문제 풀이
 * a가 미선택, b가 선택
 * ax = ax-1 + 2bx-1
 * bx = ax-1 + bx-1
 * -> ax + bx를 dp(x)라고 하면
 * dp(x) = 2dp(x - 1) + dp(x - 2) 으로 만들 수 있다.
 * 이 식을 바탕으로 행렬식을 세우면
 * dp(x)    =   2 1 제곱 n - 2 dp(2)  
 * dp(x-1)      1 0           dp(1)
 * n - 1은 분할 정복을 이용한 거듭제곱을 이용해 분해할 수 있다.
 * 
 * arrPower는 행렬의 거듭제곱을 쉽게하기 위한 것인데, 이를 위해서는 결국
 * 플로이드워설과 같이 반복문을 3개 사용한다.
 * 
 * int 범위는 어떻게 처리하지? 나눗셈의 성질 - 분배법칙 이용
 */

import java.io.*;
import java.util.*;

public class j_19587 {
    static final long MOD = 1_000_000_007;

    static long[][] arrPower(long[][] target, long[][] input) {
        int a = target.length;
        int b = input.length;
        int c = input[0].length;

        if (b != target[0].length) return null;

        long[][] ret = new long[a][c];

        for (int y = 0; y < a; y++) {
            for (int x = 0; x < c; x++) {
                for (int i = 0; i < b; i++) {
                    ret[y][x] += target[y][i] * input[i][x];
                }
            }
        }
        return ret;
    }

    static long[][] dividePower(long[][] arr, long n) {
        int a = arr.length;
        int b = arr[0].length;
        long[][] ret = new long[a][b];

        n -= 1;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                ret[i][j] = arr[i][j];
            }
        }

        while (n > 0) {
            if (n % 2 == 1) {
                ret = arrPower(ret, arr);
                n -= 1;
            }
            arr = arrPower(arr, arr);
            n /= 2;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    ret[i][j] %= MOD;
                    arr[i][j] %= MOD;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        long[] dpTable = new long[]{0, 3, 7};
        long[][] table = new long[][]{{2, 1}, {1, 0}};

        if (N < 3) {
            System.out.println(dpTable[(int)N]);
            return;
        }
        
        table = dividePower(table, N - 2);
        long ans = ((table[0][0] * dpTable[2]) % MOD + (table[0][1] * dpTable[1]) % MOD) % MOD;
        // 이후 실제로 곱하는 값은 첫번째 행의 0, 1번째 값
        System.out.println(ans);
    }
}