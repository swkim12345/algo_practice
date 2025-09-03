/*
 * 신나는 함수 실행
 * 문제 요약
 * a,b,c가 있는 재귀함수.
 * a,b,c 중 하나라도 0보다 작거나 같으면 0 리턴
 * a,b,c 중 하나라도 20보다 크면 20, 20, 20 리턴
 * a < b < c라면 (a b c - 1) + (a b - 1 c - 1) - (a b -1 c) 리턴
 * 이중 하나라도 아니면... 아주 긴 무언가 리턴
 * 
 * 문제 풀이
 * 재귀함수 만들고, 메모이제이션
 * 
 */
import java.io.*;
import java.util.*;

public class j_9184 {
    static int[][][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        arr = new int[21][21][21];

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) break;
            int ans = w(a, b, c);
            String.format();
            sb.append("w(").append(a).append(", ")
                .append(b).append(", ")
                .append(c).append(") = ")
                .append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;

        if (a > 20 || b > 20 || c > 20) return w(20, 20, 20);

        if (arr[a][b][c] != 0) return arr[a][b][c];

        if (a < b && b < c) {
            arr[a][b][c] =  w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
            return arr[a][b][c];
        }

        arr[a][b][c] = w(a - 1, b, c) + w(a -1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        return arr[a][b][c];
    }
}
