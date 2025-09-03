package sw_academy.java;

/*
 * 현주가 좋아하는 제곱근 놀이
 * 
 * 문제 요약
 * 2 이상의 정수 N
 * N + 1로 만들기
 * 루트 N이 정수일 때, N을 루트 N으로 바꿀 수 있음.
 * 
 * 문제 설명
 * 그리디? 최솟값을 구하는 것.
 * 루트값을 구하는 것 만이 최솟값을 구하는 거임.
 * 그럼 처음 접근만 그리디로 하고, 이후에는 
 * 
 * f(x ** 2) = min(f(x) + 1, f(x**2 - 1) + 1, f(x ** 2))
 * 
 * 주의
 *  long임 주의
 */

import java.io.*;
import java.util.*;

public class j_6782 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        long N, rootN, step;

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Long.parseLong(br.readLine());
            step = 0;

            while (N != 2) {
                rootN = (long)Math.sqrt(N);
                if (rootN * rootN == N) {
                    N = rootN;
                    step += 1;
                } else {
                    step += (rootN + 1) * (rootN + 1) - N + 1; // rootN + 1로 나누기까지 포함
                    N = rootN + 1;
                }
            }
            sb.append(String.format("#%d %d\n", testCase, step));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
