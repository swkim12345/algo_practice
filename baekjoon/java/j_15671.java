/*
 * 오델로
 * 
 * 문제 요약
 * 오델로를 시뮬레이션해서 푸는 문제
 * 입력값은 올바른 게임로그 / 턴을 넘기는 것 없음(중간에 다 참 or 이김 이 없음)
 * 
 * 문제 풀이
 * 승리조건이 3가지인데 돌이 차거나, 뒤집거나, 차례를 넘기는 경우 모두 게임로그 내에서는 불가능하다.
 * 다 끝나면 모두 가능하다는 것은 결국 게임 종료시 white, black의 카운팅만으로 승패를 가릴 수 있다는 뜻이다.
 * 
 * 주의 사항
 * 1부터 시작이다..
 */

import java.io.*;
import java.util.*;

public class j_15671 {
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static int empty = 0;
    static int black = 1;
    static int white = 2;
    static char[] arr = {'.', 'B', 'W'};

    static int[] cnt = {0, 2, 2}; //black, white cnt

    static int mx = 6;
    
    static int[][] board = new int[mx + 1][mx + 1];

    static boolean isInside(int x, int y) {
        return x >= 1 && x <= mx && y >= 1 && y <= mx;
    }

    static void solution(int R, int C, int stone) {
        int alter = stone == white ? black : white;
        int nx, ny;
        Deque<int[]> stack = new ArrayDeque<>();
        board[R][C] = stone;
        
        for (int i = 0; i < 8; i++) {
            nx = C + dx[i]; ny = R + dy[i];
            stack.clear();

            while (isInside(nx, ny)) {
                if (board[ny][nx] == empty || board[ny][nx] == stone)  
                    break;
                stack.addLast(new int[]{nx, ny});
                nx += dx[i]; ny += dy[i];
            }

            if (!isInside(nx, ny) || board[ny][nx] == empty || board[ny][nx] == alter) continue;

            int[] point;
            while (!stack.isEmpty()) {
                point = stack.pollLast();
                nx = point[0]; ny = point[1];
                board[ny][nx] = stone;
                cnt[stone] += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        board[3][3] = white;
        board[4][4] = white;
        board[3][4] = black;
        board[4][3] = black;

        int N = Integer.parseInt(br.readLine());

        int R,C, stone = black;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            solution(R, C, stone);
            if (stone == black) stone = white;
            else stone = black;
        }

        for (int y = 1; y <= mx; y++) {
            for (int x = 1; x <= mx; x++) {
                sb.append(arr[board[y][x]]);
            }
            sb.append("\n");
        }

        // 비기는 경우는 없으므로.
        if (cnt[white] > cnt[black]) sb.append("White\n");
        else sb.append("Black\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
