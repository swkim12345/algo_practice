/*
 * 격자판의 숫자 이어 붙이기
 * 
 * dfs를 이용해 4방향 순회하고, 만약 스텝이 7에 도달하면 전체 hashset에 저장
 * 모든 수에 대해 순회가 완료되면, 이후 set의 사이즈 리턴
 * set은 매번 초기화가 필요함.
 */

import java.io.*;
import java.util.*;

public class j_2819 {
    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { -1, 1, 0, 0 };

    static String[][] arr;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            arr = new String[4][4];
            set = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    arr[i][j] = st.nextToken().trim();
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(j, i, arr[i][j], 0);
                }
            }

            System.out.printf("#%d %d\n", t, set.size());
        }
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    static void dfs(int x, int y, String str, int step) {
        if (step == 6) {
            set.add(str);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isInside(nx, ny))
                continue;

            dfs(nx, ny, str + arr[ny][nx], step + 1);
        }
    }
}