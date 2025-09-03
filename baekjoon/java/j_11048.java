/*
 * 이동하기
 * 
 * 문제 요약
 * N*M짜리 미로, 1,1에서 시작, N,M에 도착
 * 사이에 사탕 있고, 사탕 주어서 최대 개수로 나가고 싶음.
 * 갈수있는 방향은 아래, 오른쪽, 오른쪽아래 3가지임.
 * 
 * 문제 풀이
 * 거꾸로 도착점에서 dp를 만들면 어떨까?
 * dp(x, y) = max(dp(x - 1, y), dp(x - 1, y - 1), dp(x, y - 1)) + value(x, y);
 * 
 * 주의사항
 * 1,1 에서 시작
 */
import java.io.*;
import java.util.*;

public class j_11048 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++){ 
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Math.max(arr[i][j], arr[i - 1][j]);
                arr[i][j] = Math.max(arr[i][j], arr[i - 1][j - 1]);
                arr[i][j] = Math.max(arr[i][j], arr[i][j - 1]);
                arr[i][j] += Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(arr[N][M]);
    }
}
