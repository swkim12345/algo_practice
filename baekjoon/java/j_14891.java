/*
 * 톱니바퀴 - 이자 swea에서는 특이한 자석 문제.
 * 
 * 문제 요약
 * 시뮬레이션 문제
 * 붙어있는 자석은 서로 붙어있는 날의 자성과 다를 경우에만 인력에 의해 반대방향으로 1칸 회전함
 * 1번 자석 - S - 2 ** (1 - 1)
 * 2번 자석 - S - 2 ** (2 - 1)
 * 3번 자석 - S - 2 ** (3 - 1)
 * 4번 자석 - S - 2 ** (4 - 1)
 * N은 점수 없음.
 * 
 * 문제 풀이
 * 빨간 화살표를 포인터로 가짐.
 * -2 점이 이전과 맞닿여 있는 자석 날, +2점이 이후와 맞닿여 있는 자석 날
 * 회전 방향에 맞게 회전할 때, 첫번째는 무조건 회전. 두번째로 넘어갈 때, 첫번째와 비교해
 * 첫번째 + 2 와 두번째 - 2의 합이 1이라면 반대방향으로 회전 (* -1로 회전방향 이동)
 * 
 * 한 스텝이 모두 끝나면, 회전방향 저장 배열만큼 각 날의 해당 포인터값을 이동처리하고
 * 다음 번째로 넘어간다.
 * 
 * 주의
 * 자석번호 1부터 시작;;;;;
 */

import java.io.*;
import java.util.*;

public class j_14891 {
    static final int N = 0;
    static final int S = 1;
    static final int[] grade = {1, 2, 4, 8};

    static int ans;
    static int[] pointer;
    static int[] rotate;
    static int[][] blades;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ans = 0;
        pointer = new int[]{0, 0, 0, 0};
        blades = new int[4][8];
        rotate = new int[4];
        
        for (int i = 0; i < 4; i++) {
            String str = br.readLine().trim();

            for (int j = 0; j < 8; j++) {
                blades[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            Arrays.fill(rotate, 0);
            solution(num, dir);
            for (int j = 0; j < 4; j++) {
                pointer[j] = (pointer[j] - rotate[j] + 8) % 8;
            }
        }

        for (int j = 0; j < 4; j++) {
            if (blades[j][pointer[j]] == S) {
                ans += grade[j];
            }
        }

        System.out.println(ans);
    }

    public static void solution(int num, int dir) {
        if (rotate[num] != 0) return;
        rotate[num] = dir;

        dir *= -1;
        if (num > 0 && rotate[num - 1] == 0) {
            int befNum = num - 1;
            int befPointer = (pointer[befNum] + 2) % 8;
            int nowPointer = (pointer[num] - 2 + 8) % 8;
            
            if (blades[befNum][befPointer] + blades[num][nowPointer] == 1) {
                solution(befNum, dir);
            }
        }

        if (num < 3 && rotate[num + 1] == 0) {
            int aftNum = num + 1;
            int aftPointer = (pointer[aftNum] - 2 + 8) % 8;
            int nowPointer = (pointer[num] + 2) % 8;

            if (blades[aftNum][aftPointer] + blades[num][nowPointer] == 1) {
                solution(aftNum, dir);
            }
        }
    }
}