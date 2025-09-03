package sw_academy.java;

/*
 * 새로운 불면증 치료법
 * 
 * 문제 요약
 * N이 주어질 때, N, 2N, ... KN까지 세었을 때
 * 모든 수의 각 자리수에서 0 ~ 9까지의 모든 수를 보는 것은 최소 몇번 양을 센 시점일까?
 * 
 * 문제 풀이
 * 각 스텝별로 더하고, 이를 나눗셈해서 set에 넣어줌.
 * set size가 10이 될 때 이때의 k값이 답임.
 * 스텝의 경우는 나눗셈이 있으므로, 임시 변수 하나, set(각 스텝마다 초기화)되는 거 하나
 */
import java.io.*;
import java.util.*;

public class j_1288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            Set<Integer> set = new HashSet<>();

            for (int i = 1;; i++) {
                int KN = i * N;
                while (KN >= 1) {
                    set.add(KN % 10);
                    KN /= 10;
                }

                if (set.size() == 10) {
                    System.out.printf("#%d %d\n", t, i * N);
                    break;
                }
            }
        }
    }
}
