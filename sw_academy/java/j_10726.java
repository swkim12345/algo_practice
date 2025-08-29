package sw_academy.java;
/*
 * 이진수 표현
 * 
 * 문제 요약
 * 정수 N, M, M의 이진수 표현의 마지막 N비트가 모두 1로 켜져있는 지 판별해서 출력
 * 
 * 문제 풀이
 * M의 비트를 첫번째 자리와 and해 맞으면 다음 비트로 쉬프트, cnt ++(처음은 0) 아니면 바로 break 후
 * cnt가 N과 동일하면 on, 아니면 off
 */
import java.io.*;
import java.util.*;

public class j_10726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int cnt = 0;
            for (; cnt < N; cnt++) {
                int tmp = M & 1;
                if (tmp == 0) break;
                M = (M >> 1);
            }
            if (cnt == N) {
                System.out.printf("#%d ON\n", t);
            } else {
                System.out.printf("#%d OFF\n", t);
            }
        }
    }
}
