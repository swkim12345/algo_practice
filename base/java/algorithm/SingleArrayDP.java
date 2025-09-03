package base.java.algorithm;

/*
 * 가장 단순한 형태의 DP
 * 일차원 배열에 저장하고 푸는 문제
 * DP는 점화식을 세우는 것이 가장 중요함.
 * 
 * 여기 예시의 경우 백준 11053 가장 긴 증가하는 부분 수열 문제임.
 * 
 * 가장 증가하는 부분 수열 문제
 * DP, 제약조건이 (부분 element)마다 (부분 element)간의 관계임.
 * 따라서, DP 점화식을 만들기 편함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SingleArrayDP {
    static int[] arr;
    static int[] dpArray;

    public static void dp(int N) {
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) { // 제약 조건 : arr[j] < arr[i]
                    dpArray[i] = Math.max(dpArray[j] + 1, dpArray[i]); // 점화식
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, ans = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dpArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dpArray[i] = 1; //1로 초기화
        }
        dp(N);

        // 완성되었으면 전체를 스캔해 dp 답 출력
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dpArray[i]);
        }
        System.out.println(ans);
    }
}
