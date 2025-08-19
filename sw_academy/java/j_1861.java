package sw_academy.java;
/*
 * 정사각형 방
 * 
 * 문제 요약
 * 2차원 배열에 정수 값이 있음 4방향으로 이동 가능할 때(이동하려는 방이 현재보다 정확히 1 커야함),
 *  어느 정수에 있어야 최대한 많은 정수를 이동할 수 있냐?
 * 
 * 문제 해결
 * 1, 2, 3...을 인덱스로 삼고, 여기에 이차원 배열로 (y, x) 좌표를 삽입한다.
 * 순회하면서(마이너스로 이동) y, x 좌표가 1 차이가 나면 tmp ++, 최대값 갱신 시도, 아니면 tmp = 1로 초기화
 * 모두 순회가 완료가 되면, 최대값 출력
 */

import java.io.*;
import java.util.*;

public class j_1861 {

    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T= Integer.parseInt(br.readLine());
        int N, tmp, mx, diff, base;

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N  * N + 1][2]; // 1번째 인덱스에는 정수값 삽입, 두번째는 0 - y, 1 - x

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    tmp = Integer.parseInt(st.nextToken());
                    arr[tmp][0] = i;
                    arr[tmp][1] = j; 
                }
            }

            tmp = 1; base = 1; mx = 1;
            // 처음 값과 비교해 x, y 좌표 차이가 1만 난다면 tmp ++, 아니라면 1로 정상화
            // 시작값을 편하게 확인하기 위해 감소하게 설정함.
            for (int i = N * N - 1; i >= 1; i--) {
                diff = Math.abs(arr[i][0] - arr[i + 1][0]) + Math.abs(arr[i][1] - arr[i + 1][1]);
                if (diff == 1) {
                    tmp++;
                    if (mx <= tmp) {
                        mx = tmp;
                        base = i;
                    }
                }
                else {
                    tmp = 1;
                }
            }
            System.out.printf("#%d %d %d\n", testCase, base, mx);
        }
    }
}
