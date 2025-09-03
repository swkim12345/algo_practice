package sw_academy.java;

/*
 * 보물상자 비밀번호
 * 
 * 문제 요약
 * 총 개수는 4의 배수, 숫자가 16진수로 제시됨
 * 사이클을 돌리며 숫자를 완성할 때, K번째에 있는 수를 출력(10진수로)
 * 돌리는 횟수는 (총 개수) / 4 - 1번이 됨. (한 사이클 완성하기 전)
 * 
 * 문제 풀이
 * 16진수를 저장하는 str 배열 하나, 시작 포인터 하나만 가지고 있고, 이 포인터를 -1하면서
 * 사이클을 돌린다
 * 각 문자를 순회하면서 이전 수 * 16을 곱해 저장하자. 전체 한 숫자의 길이는 (총 개수) / 4이다.
 * 길이만큼 도달하면 int[4]짜리 배열에 하나씩 저장하고, 모두 순회가 완료하면 treeset에 저장하자.
 * 정답은 treeset에서 pop하면서 카운팅해 K번째가 정답임.
 */

import java.io.*;
import java.util.*;

public class j_5658 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            String str = br.readLine().trim();
            int pointer = 0;
            int size = N / 4;

            TreeSet<Integer> tst = new TreeSet<>();

            for (int i = 0; i < size; i++) {
                int newP = pointer - i;
                int tmp = 0;
                
                for (int j = 1; j <= N; j++) {
                    char c = str.charAt((newP + j + N) % N);
                    int num;
                    if (c >= '0' && c <= '9') {
                        num = c - '0';
                    } else {
                        num = c - 'A' + 10;
                    }
                    tmp *= 16;
                    tmp += num;

                    if (j % size == 0) {
                        tst.add(tmp);
                        tmp = 0;
                    }
                }
            }

            for (int i = 0; i < K - 1; i++) {
                tst.pollLast();
            }
            System.out.printf("#%d %d\n", t, tst.pollLast());
        }
    }
}
