/*
 * 나머지 합
 * 
 * 문제 요약
 * 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수 구하기
 * 
 * 문제 풀이
 * 누적합으로 풀면 됨, 다만, 누적합으로 풀 때 int 범위 초과할 수 있으므로, 합하기 전에 M으로 나누고 나머지를 이용해 누적합
 * 왜 괜찮냐? -> 나눗셈에 대해 우분배법칙이 허용되므로!
 * 쌍의 개수만 출력하면 되는 문제이다. 
 * 
 * 
 * 주의사항
 * Ai가 10**9 이다. 3개 합쳐지면 초과될 위험이 있다.
 * 누적합을 구할 때, 이미 M을 이용해 나눴으므로 - 값이 나올 수 있다. -이면 M을 더해 보정해주자.
 * 
 * 틀린 부분
 * 1. 
 */


import java.io.*;
import java.util.*;

public class j_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long tmp;
        int[] cnt = new int[M]; // 나머지 합의 개수를 저장
        int prefixSum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prefixSum = (prefixSum + Integer.parseInt(st.nextToken())) % M;
            cnt[prefixSum]++; // 음수는 고려할 필요가 없음.
        }

        // 0의 경우, 0,i 라는 것도 M으로 나누어 떨어짐. 따라서 보정해주어야 함.
        long ans = cnt[0];

        // 0이 아닌 경우, i, j를 선택해야 함, 순서는 중요하지 않음.
        for (int i = 0; i < M; i++) {
            tmp = cnt[i];
            if (tmp >= 2) {
                ans += tmp * (tmp - 1) / 2;
            }
            // System.out.printf("%d %d\n", i, tmp);
        }
        System.out.println(ans);
    }
}
