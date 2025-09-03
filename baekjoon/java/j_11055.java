/*
 * 가장 큰 증가하는 부분 수열
 * 
 * 문제 요약
 * 증가하는 수열 중에 합이 가장 큰 것을 구하시오
 * 
 * 문제 풀이
 * top down 방식 dp
 * f(x) = max(if target < x -> f(target) + value(x))
 */

import java.io.*;
import java.util.*;

public class j_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }

        int mx = 0;
        for (int i = 0; i < N; i++) {
            mx = Math.max(mx, dp[i]);
        }

        System.out.println(mx);
    }
}
