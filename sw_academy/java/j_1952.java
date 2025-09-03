package sw_academy.java;

/*
 * 수영장
 * 
 * 문제 요약
 * 1일, 1달, 3달, 1년 이용권을 이용해 비용을 최소화해 수영장을 다닐 수 있게 만듦.
 * 제한조건이 따로 없음 (1일이 1달보다 비쌀 수도..), 3달 이용권은 11월, 12월에도 사용 가능함.
 * 
 * 문제 풀이
 * dp - 현재 달에 어떤 것으로 다닐 것인가?
 * 첫번째 항 - 1부터 시작하는 월
 * 0 - 1일, 1 - 1달, 2 - 3달
 * value는 현재 달에 이용 계획수
 * dp[x][0] = min(dp[x - 1][0], dp[x - 1][1], dp[x - 3][2]) + value(x);
 * dp[x][1] = min(dp[x - 1][0], dp[x - 1][1], dp[x - 3][2]) + 1;
 * dp[x][2] = min(dp[x - 1][0], dp[x - 1][1], dp[x - 3][2]) + 1;
 * 1년 이용은 따로 계산 - 한번만 비교해주면 됨.
 * 답의 경우 min(1년 이용, dp[x][0, 1, 2]) 를 해주면 됨.
 * 
 * 위와 같이 풀지 않아도 됨.
 * 각각 일, 월만 고려하고, 이후 3달, 1년을 고려해 푼다면, 일차원 배열을 두개 사용하므로써 문제를 풀 수 있었음.
 * 
 * 
 * 주의사항
 * 1. index out of range 조심
 * 2. 최소값이 0이 되는 케이스가 있음. 이를 처리해줘야 함.
 */

import java.io.*;
import java.util.*;

public class j_1952 {
    static final int DAY = 0;
    static final int MONTH = 1;
    static final int THREE_MONTH = 2;
    static final int YEAR = 3;
    static final int TOTAL_MONTH = 12;
    static final int PAY = 4;

    static int[] plan;
    static int[] amount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        plan = new int[TOTAL_MONTH + 1];
        amount = new int[PAY];

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < PAY; i++) {
                amount[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            boolean isEmpty = true;
            for (int i = 1; i <= TOTAL_MONTH; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
                if (plan[i] > 0) isEmpty = false;
            }
            
            if (isEmpty) {
                System.out.printf("#%d %d\n", testCase, 0);
                continue;
            }

            int ans = solution();

            System.out.printf("#%d %d\n", testCase, ans);
        }
    }

    static int solution() {
       int[] monthly = new int[TOTAL_MONTH + 1];
        for (int i = 1; i <= TOTAL_MONTH; i++) {
            int dayCost = plan[i] * amount[DAY];
            int monthCost = (plan[i] == 0) ? 0 : amount[MONTH];
            monthly[i] = Math.min(dayCost, monthCost);
        }

        int N = TOTAL_MONTH;
        int[] f = new int[N + 4];

        // 뒤에서 앞으로
        for (int i = N; i >= 1; i--) {
            int useMonthly = monthly[i] + f[i + 1];          // i월을 일/월권으로 처리
            int use3Month  = amount[THREE_MONTH] + f[i + 3]; // i~i+2를 3달권 하나로 처리
            f[i] = Math.min(useMonthly, use3Month);
        }

        // 연간권과 비교
        return Math.min(f[1], amount[YEAR]);
    }
}
