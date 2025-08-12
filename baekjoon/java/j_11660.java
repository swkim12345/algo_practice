/*
 * 구간 합 구하기 5
 * 
 * 문제 요약
 * N * N 배열, 작은 사각형 내부 값을 더해서 구하는 문제
 * 
 * 문제 해결
 * 누적합을 두번 진행
 * 
 * 주의
 * 0번 인덱스는 버리는 인덱스임.
 */

import java.io.*;
import java.util.*;

public class j_11660 {
    static int[][] prefixSum; // (0,0) - (x, y) 사각형 내부의 누적합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 겹치는 사각형을 빼줘야 함.
                prefixSum[i][j] = Integer.parseInt(st.nextToken()) + prefixSum[i][j - 1] + prefixSum[i - 1][j] - prefixSum[i - 1][j - 1];
            }
        }

        int startX, startY, endX, endY, ans;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            

            ans = prefixSum[endX][endY] - prefixSum[endX][startY - 1] - prefixSum[startX - 1][endY] + prefixSum[startX - 1][startY - 1];
            sb.append(ans).append("\n");            
        }

        System.out.print(sb.toString());
    }
}
