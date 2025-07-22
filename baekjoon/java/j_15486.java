/*
 * 퇴사 2
 * 
 * 누가 봐도 dp
 * 정답 : 금액 최적화
 * 고려해야할 부분 : 금액, 상담을 완료하는 데 걸리는 시간(제약사항)
 * 실제 dp - 상담 선택 비선택 나눔
 * dp를 진행할 때 처음부터 상담 선택, 비선택이 중요하지 않음! (이전의 일정과 현재 일정은 동일 선상(x)이라면 상관 없음)
 * 약간 특이한 dp임. 상담을 완료하는 데 걸리는 시간을 "고려해" dp(x + (일정))에 추가해줘야함(max를 이용)
 * dp(x) = Math.max(dp(x - 1), dp(x - 상담 걸리는 시간))
 * 바텀업으로 1일차부터 진행
 * 
 * 초기화
 * 1. 1부터 시작하는 T, P 이차원 배열 N + 1만큼 선언 후 0번에 T, 1번에 P 삽입
 * 2. dp 또한 1부터 시작하는 N + 1짜리 일차원 배열
 * 
 * 솔루션
 * 1. 바텀업으로 1부터 시작. 상담을 했을 때 x + (상담에 걸리는 날짜)에 총 얻은 금액 추가. (index out of range 조심)
 * 
 * 오답노트
 * N + 1까지로 처리해야함. (마지막 날을 포함해서 상담 처리가 가능하기 때문.)
 * 
 * 참조 답
 * https://www.acmicpc.net/source/96585080
 * 입력과 동시에 dp를 처리함. 내가 작성한 알고리즘도 이렇게 변경이 가능
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class j_15486 {
    static int[][] array;
    static int[] dp;

    static void solution(int N) {
        int T, P;

        for (int i = 1; i <= N; i++) {
            // 1. dp, 바로 직전 값(x - 1)과 현재 값 max 비교
            dp[i] = Math.max(dp[i - 1], dp[i]);

            // 2. 다음 값 계산, 계산 시 만약 index out of range라면 스킵
            T = array[i][0];
            P = array[i][1];
            if (i + T > N + 1) continue;
            dp[i + T] = Math.max(dp[i + T], dp[i] + P);
        }

        System.out.println(Math.max(dp[N + 1], dp[N]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, T, P;

        N = Integer.parseInt(br.readLine());
        array = new int[N + 1][2];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            array[i][0] = T;
            array[i][1] = P;
        }

        solution(N);
    }
}
