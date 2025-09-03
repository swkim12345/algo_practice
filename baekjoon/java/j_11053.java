/*
 * 가장 긴 증가하는 부분 수열
 * 
 * 문제 해설
 * 수열이 주어질 때, 가장 긴 증가하는 부분 수열의 길이를 구하는 문제
 * 
 * 문제 풀이
 * dp를 이용해 푸는 문제.
 * 가장 긴 증가 수열의 경우, 가장 마지막 값만 현재 값과 비교해 증가하면 더해주면 되기 때문에 점화식을 만들 수 있다.
 * dp(x) = max(dp(x - 1) ....)
 * dp(x)는 길이가 된다.
 * 이 문제는 입력값을 조작하지 않고 푸는 문제이다.
 * 
 * 주의 사항
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class j_11053 {
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
