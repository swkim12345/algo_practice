package sw_academy.java;

/*
 * 규영이와 인영이의 카드게임
 * 국지적으로 이기는 경우와, 전체적으로 이기는 경우가 다름 (우리는 전체적으로 이기는 경우)
 * (규영이가 전체적으로 이기는 경우의 수)를 구하는 문제임.
 * 
 * 9!가 너무 큰 숫자라고 생각함. 대략 3000만정도밖에 되지 않음.
 * 알고리즘 분류 : 브루트포스
 */

import java.io.*;
import java.util.*;

public class j_1210 {
    // 재귀를 이용한 탐색 구현
    static int[] gCards = new int[9];
    static boolean[] cards = new boolean[19]; //카드 사용 여부를 나타내는 배열

    static int[] add(int[] a, int[] b) {
        return new int[]{a[0] + b[0], a[1] + b[1]};
    }

    // idx에 맞춰 gCards를 순회하는 함수
    // 입력값 : 지금까지 얻은 점수
    // 종료조건 - idx == 9
    // 리턴값 - 0 : 이긴 경우, 1 : 진 경우
    static int[] selection(int idx, int winValue, int loseValue) {
        int[] ans = new int[]{0, 0};
        if (idx == 9) { // 종료조건
            if (winValue > loseValue) ans[0] ++;
            else ans[1] ++;
            return ans;
        }

        for (int i = 1; i <= 18; i++) {
            if (!cards[i]) {
                cards[i] = true; //카드 사용 체크
                if (gCards[idx] > i) { // 다음인덱스, 이겼으니 점수 추가
                    ans = add(ans, selection(idx + 1, winValue + gCards[idx] + i, loseValue)); 
                } else { // 졌으니 다른쪽에 총점 추가
                    ans = add(ans, selection(idx + 1, winValue , loseValue + gCards[idx] + i));
                }
                cards[i] = false; //카드 원복
            }
        }
        return ans;
    }

    static int[] solution() {
        int[] ans = selection(0, 0, 0);
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                gCards[j] = Integer.parseInt(st.nextToken());
                cards[gCards[j]] = true;
            }

            int[] ans = solution();
            System.out.printf("#%d %d %d\n", i, ans[0], ans[1]);
        }
    }
}
