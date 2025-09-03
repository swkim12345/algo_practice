/*
 * 구간 합 구하기 4
 * 
 * 문제 설명
 * 수 N개 - i ~ j번째 수까지의 합 구하기
 * 
 * 문제 해설
 * N이 10만, 업데이트 필요 없음 -> 누적합.
 * 
 * 주의
 * 쿼리 start - 1부터 시작
 */

import java.io.*;
import java.util.*;

public class j_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start, end;
    
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1]; // 누적 합 구하기
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            sb.append(arr[end] - arr[start - 1]).append("\n");
        }

        System.out.print(sb.toString());
    }
}
