/*
 * 숨바꼭질
 * 
 * 문제 요약
 * 수빈이 - X - 1 / x + 1 / X * 2 (1초 뒤)가 가능
 * 동생 찾기 - 가장 빠른 시간.
 * 
 * 문제 해결
 * 그리디로는 해결이 안 되는 이유는 x - 1이라는 값이 있어 단순히 두배만 해서 찾는 것이
 * 최적의 해가 보장되지 않음.
 * dp dp로 접근할 때, +1, -1, *2로 확인하고 만들면 됨.
 * 왜 +2, -2를 하지 않는냐? -> y = 2 * x;일 때, y - 2 = 2 (x - 1);이므로 동일한 값으로 만들 수 있다.
 * y + 2도 동일하게 만들 수 있음. 따라서, +2, -2은 국지적으론 할 필요가 없다.
 * 모든 원소에 대해 수행해주면 끝.
 * 
 * 위에가 처음 접근 방식
 * 아래는 topdown방식으로 dp 구성
 * dp[x] = max - dp[x - 1] + 1, dp[x // 2] + x % 2 + 1, dp[(x + 1) // 2] + 2
 * 중 하나임.
 * 
 * 추가적으로 bfs로도 접근이 가능함.
 * 액션이 3가지밖에 되고, 같은 step을 밟기 때문에 visited 배열만 둔 다음, 방문 했으면
 * 더이상 방문하지 않고 중단처리한다.
 * 
 * 주의사항
 * 0을 조심해서 풀자.
 * bottomup 방식 build로 풀면 최적해가 보장되지 않는다. 
 * 따라서, topdown으로 원소를 나눠가며 확인이 필요하다.
 */

import java.io.*;
import java.util.*;

public class j_1697 {
    static final int MX = 100000;
    static int[] dp = new int[MX + 1];

    static int solution(int N, int K) {
        // N >= K 일 경우 - 감소밖에 없음 바로 리턴
        if (N >= K)
            return N - K;

        int target;
        // N < K인 경우 - 1부터 빌드
        for (int i = 1; i <= MX; i++) {
            target = i - 1;
            dp[i] = Math.min(dp[target] + 1, dp[i]);
            
            target = i / 2;
            dp[i] = Math.min(dp[target] + i % 2 + 1, dp[i]);

            target = (i + 1) / 2;
            dp[i] = Math.min(dp[target] + 2, dp[i]);
        }
        return dp[K];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 빌드
        for (int i = 0; i <= MX; i++) {
            dp[i] = Math.abs(i - N);
        }

        // solution
        System.out.println(solution(N, K));
    }
}
