package sw_academy.java;

/*
 * 2일차 - Ladder1
 * 
 * 2에 도달하는 x 좌표를 찾는 문제
 * 단순하게 구현하면 되는 문제임. 전의 값을 기억할 필요 없고, 이동만 처리하면 됨.
 * 2에 도달하면 끝!
 */

import java.io.*;
import java.util.*;

public class j_6808 {
    static int[][] board = new int[100][100];

    static boolean isInside(int x, int y) {
        return (x >= 0 && x < 100 && y >= 0 && y < 100);
    }

    // dirX - -1, 0, 1
    // dirY - 1, 0
    // dirY == 1 => x - 1에 있으면 -1, x + 1 -> +1
    // -1 / 1에 없거나 인덱스 초과이면 0, dirY - 0

    static boolean start(int start) {
        int y = 0, x = start, dirX = 0, dirY = 1, nx, ny;
        while (y < 99) {
            if (dirY == 1) {
                nx = x - 1;
                ny = y;
                if (isInside(nx, ny) && board[ny][nx] == 1) {
                    dirX = -1;
                    dirY = 0;
                }
                nx = x + 1;
                ny = y;
                if (isInside(nx, ny) && board[ny][nx] == 1) {
                    dirX = 1;
                    dirY = 0;
                }
            } else {
                nx = dirX + x;
                ny = dirY + y;

                if (!isInside(nx, ny) || board[ny][nx] != 1) {
                    dirY = 1;
                    dirX = 0;
                }
            }
            x += dirX;
            y += dirY;
        }
        if (board[y][x] == 2) return true;
        else return false;
    }

    static int solution() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            if (board[0][i] == 1 && start(i)) {
                ans = i; break;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("sw_academy/java/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 10;

        for (int i = 1; i <= T; i++) {
            br.readLine();
            for (int y = 0; y < 100; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < 100; x++) {
                    board[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.printf("#%d %d\n", i, solution());
        }
    }
}
