package sw_academy.java;
/*
 * 상호의 배틀필드
 * 
 * 문제 요약
 * 평지면 이동 가능, 포탄발사시 벽돌벽이면 파괴
 * 입력 모두 처리시 맵 상태 업데이트
 * 
 * 문제 풀이
 * 전형적인 시뮬레이션 문제
 * 
 */

import java.io.*;
import java.util.*;

public class j_1873 {
    static char ground = '.';
    static char breakWall = '*';
    static char notBreakWall = '#';
    static char water = '-';
    static char[] tank = {'^', 'v', '<', '>'};

    static int U = 0;
    static int D = 1;
    static int L = 2;
    static int R = 3;
    static int S = 4;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int x, y, dir;
    static int H, W;

    static char[][] board;

    static boolean isInside(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    static boolean isGoodToGo(int x, int y) {
        return board[y][x] == ground;
    }

    static void shoot() {
        int nx = x, ny = y;

        while (isInside(nx, ny) && board[ny][nx] != notBreakWall) {
            if (board[ny][nx] == breakWall) {
                board[ny][nx] = ground;
                break;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    static void move(char cmd) {
        int nx = x, ny = y;
        switch (cmd) {
            case ('U') :
                dir = U;
                break;
            case ('D') :
                dir = D;
                break;
            case ('L'):
                dir = L;
                break;
            case ('R'):
                dir = R;
                break;
        }
        nx += dx[dir]; 
        ny += dy[dir];
        
        if (isInside(nx, ny) && isGoodToGo(nx, ny)) {
            x = nx; y = ny;
        }
    }
    
    static void solution(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String cmds = br.readLine().trim();
        char cmd;

        for (int i = 0; i < N; i++) {
            cmd = cmds.charAt(i);
            if (cmd == 'S') {
                shoot();
            } else {
                move(cmd);
            }
        }

        // 마지막에 탱크로 업데이트
        board[y][x] = tank[dir];
    }

    public static void main(String[] args) throws IOException {
        // // 파일 입출력을 이용해 검증이 필요할 경우
        // System.setIn(new java.io.FileInputStream("res/input.txt"));
        
        // //출력의 경우 파일 output stream을 만들고, 이를 PrintStream으로 변환해주는 과정이 필요하다.
        // FileOutputStream f = new java.io.FileOutputStream("res/userOutput.txt");
        // System.setOut(new PrintStream(f));


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        
        String str;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            sb = new StringBuilder().append(String.format("#%d ", testCase));

            board = new char[H][W];
            for (int i = 0; i < H; i++) {
                str = br.readLine().trim();

                for (int j = 0; j < W; j++) {
                    board[i][j] = str.charAt(j);
                    for (int a = 0; a < 4; a++) {
                        if (tank[a] == board[i][j]) {
                            x = j; y = i; dir = a;
                            board[i][j] = ground; // 땅으로 초기화
                            break;
                        }
                    }
                }
            }

            solution(br);

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }
}
