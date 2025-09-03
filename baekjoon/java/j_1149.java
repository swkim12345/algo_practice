/*
 * RGB 거리
 * 
 * 문제 요약
 * 집마다 빨, 파, 초로 칠하는 비용이 주어지고,
 * 앞 뒤로 같은 색은 불가능
 * 집 칠하는 비용을 최소화하는 값 구하기
 * 
 * 문제 풀이
 * dp식으로 풀어내면, 현재 색과 다른 색을 이전 값에서 가져오면 된다.
 * dp(x, 색) = min(dp(x -1, 다른 색 1), dp(x - 1, 다른 색 2)) + value(x, 색)
 * 또한, 바로 직전 값만 사용하기 때문에 슬라이딩윈도우로 풀었다.
 */
import java.io.*;
import java.util.*;

public class j_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }    
        }

        // dp
        int[] prev = new int[3], acc = new int[3];
        System.arraycopy(arr[0], 0, prev, 0, 3);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                acc[j] = Math.min(prev[(j + 1) % 3], prev[(j + 2) % 3]) + arr[i][j];
            }
            prev = acc;
            acc = new int[3];
        }
        int ans = Math.min(Math.min(prev[0], prev[1]), prev[2]);
        System.out.println(ans);
    }
}
