package sw_academy.java;
/*
 * 보호 필름
 * 
 * 문제 요약
 * 보호필름의 두께가 있고, 각 레이어마다 A, B가 있음.
 * 품질 기준 통과하려면, 각 관통 층마다 A, B가 연속해서 K개 이상 있어야 하고, 이것이 모든 관통 층마다 있어야
 * 통과 됨.
 * 또한, 약품을 투입해 한 층을 A 혹은 B로 바꿀 수도 있음.
 * 이렇게 했을 때, 최소로 약품을 투여해서 통과하는 횟수를 구하시오.
 * 
 * 문제 풀이
 * powerset을 해도 13의 경우 만도 안 됨. 따라서 부분 집합으로 풀어도 괜찮음.
 * 부분 집합 문제. dfs를 이용해 len까지 이동한 다음, 바꾸기로 한 레이어를 반영해 성능 검사에 통과하는 지 체크
 * 성능 검사에 통과하는 것을 체크할 때에는 전역변수를 쓴 다음에 통과하면 바뀌는 레이어의 size만큼 해줌.
 */
import java.io.*;
import java.util.*;

public class j_2112 {
    static final int NOTHING = -1;
    static final int A = 0;
    static final int B = 1;

    static int[][] arr;
    static int[] select;
    static int D, W, K, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[D][W];
            select = new int[D];
            ans = Integer.MAX_VALUE;
            Arrays.fill(select, NOTHING);

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            powerset(0, 0);
            System.out.printf("#%d %d\n", t, ans);
        }
    }

    static void powerset(int len, int change) {
        if (len == D) {
            if (isValid()) ans = Math.min(ans, change);
            return;
        }
        powerset(len + 1, change);
        select[len] = A;
        powerset(len + 1, change + 1);
        select[len] = B;
        powerset(len + 1, change + 1);
        select[len] = NOTHING;
    }

    // A와 B의 누적합을 이용해서 K개 이상이면 valid, 하나라도 아니면 not valid
    static boolean isValid() {
        boolean isOk = true;
        for (int j = 0; j < W; j++) {
            boolean isLocalOk = false;
            int[] thick = {0, 0};

            for (int i = 0; i < D; i++) {
                int target = select[i] != NOTHING ? select[i] : arr[i][j];
                if (target == A) {
                    thick[A] ++; thick[B] = 0;
                    if (thick[A] >= K) {
                        isLocalOk = true;
                        break;
                    }
                } else {
                    thick[A] = 0; thick[B] ++;
                    if (thick[B] >= K) {
                        isLocalOk = true;
                        break;
                    }
                }
            }
            if (isLocalOk == false) {
                isOk = false;
                break;
            }
        }
        return isOk;
    }
}
