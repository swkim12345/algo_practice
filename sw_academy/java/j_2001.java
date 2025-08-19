package sw_academy.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 파리 퇴치
 * N값이 15라서 단순 브루트포스를 이용해 구하는 문제
 * 
 * 문제 요약
 * N * N 짜리 배열에서 M * M 파리채로 잡아 잡는 파리 수를 최대화하는 문제
 * 
 * 해결
 * 
 * 주의
 * 0 ~ N - M 미만까지 진행하면서 풀어야 함.
 * 
 */

import java.io.*;
import java.util.*;

public class j_2001 {
    static int[][] arr = new int[15][15];

    static int getTotalValue(int x, int y, int M) {
        int ans = 0;
    
        for (int i = y; i < y + M; i++) {
            for (int j = x; j < x + M; j++) {
                ans += arr[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T, N, M, ans;

        T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase ++ ) {
            ans = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    ans = Math.max(ans, getTotalValue(i, j, M));
                }
            }

            System.out.printf("#%d %d\n", testCase, ans);
        }
    }
}