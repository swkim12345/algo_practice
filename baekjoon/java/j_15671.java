/*
 * 오델로
 * 
 * 문제 요약
 * 
 * 
 * 문제 풀이
 * 
 * 주의 사항
 */

import java.io.*;
import java.util.*;

public class j_15671 {
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static int empty = 0;
    static int white = 1;
    static int black = 2;

    static int mx = 6;
    
    static int[][] board = new int[mx + 1][mx + 1];

    static boolean isInside(int R, int C) {
        return R >= 0 && R <= mx && C >= 0 && C <= mx;
    }

    static void solution(int R, int C, int stone) {
        int alter = stone == white ? black : white;
        int nx = C, ny = R;
        
        for (int i = 0; i < 8; i++) {
            
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
                sb.append(board[y][x]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
