package sw_academy.java;

/*
 * 장훈이의 높은 선반
 * 
 * 문제 요약
 * 높이가 B 이상인 탑 중에 높이가 가장 낮은 탑의 높이
 * 점원을 1명 이상 선택해 탑을 쌓을 수 있음.
 * 
 * 문제 풀이
 * dfs를 이용해 풀되, 높이가 B이상이면 차이를 이용해 최소값 갱신 시도
 */

import java.io.*;
import java.util.*;

public class j_1486 {
    static int[] height = new int[21];
    static int ans;

    static void dfs(int N, int B, int idx, int total) {
        // 종료조건 : 끝까지 도달 - 검증
        if (idx == N) {
            if (total >= B) {
                ans = Math.min(ans, total - B);
            }
            return ;
        }

        // 중간 확인 - B 이상 시 중간 리턴
        if (total >= B) {
            ans = Math.min(ans, total - B);
            return;
        }

        // 현재 idx 선택 / 비선택
        dfs(N, B, idx + 1, total + height[idx]);
        dfs(N, B, idx + 1, total);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int N, B;

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
            dfs(N, B, 0, 0);
            System.out.printf("#%d %d\n", testCase, ans);
        }
    }
}
