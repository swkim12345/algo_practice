package sw_academy.java;

/*
 * 디저트 카페
 * 
 * 문제 해설
 * 디저트카페 투어는 직사각형, 대각선 이동만 가능
 * 같은 숫자의 디저트 불가능, 출발한 카페로 돌아오는 것만 가능
 * 디저트 먹을 수 없으면 -1, 최대 디저트 개수 출력
 * 
 * 문제 풀이
 * dfs, 중복검사는 set 이용
 * 왼쪽 위 이동과 오른쪽 아래 이동은 두번째 뒤의 이동과 동일하다.
 * 이를 전역변수로 관리하면 됨
 * 점의 시작점은 왼쪽 꼭지점.
 * 끝까지 도달하면 업데이트
 * 
 * 주의사항
 * N이 20, 
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class j_2105 {
    static final int[] dx = {1, 1, -1, -1};
    static final int[] dy = {-1, 1, 1, -1};
    static int[] mv = {0, 0};
    static int[][] board;
    static int ans, N;

    static Set<Integer> dupSet = new HashSet<>(); // 원소 중복 체크용

    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void reset(Deque<Integer> stack) {
        while (!stack.isEmpty()) {
            dupSet.remove(stack.pollLast());
        }
    }

    static void dfs(int x, int y, int dir) {
        if (dir == 4) {
            ans = Math.max(ans, dupSet.size());
            return;
        }

        // dir에 맞춰 시작
        // 0, 1의 경우 사이즈를 증감시켜가며 확인
        int nx = x + dx[dir], ny = y + dy[dir];
        Deque<Integer> stack = new ArrayDeque<>();

        if (dir == 0 || dir == 1) {
            while (isInside(nx, ny) && dupSet.contains(board[ny][nx]) == false) {
                dupSet.add(board[ny][nx]);
                stack.addLast(board[ny][nx]);
                mv[dir]++;
                dfs(nx, ny, dir + 1);
                nx += dx[dir]; ny += dy[dir];
            }
            reset(stack);
            mv[dir] = 0;
        }
        // 2, 3의 경우 사이즈만큼 이동
        else {
            for (int i = 0; i < mv[dir % 2]; i++) {
                if (!isInside(nx, ny) || dupSet.contains(board[ny][nx]) == true) {
                    reset(stack);
                    return;
                }
                stack.addLast(board[ny][nx]);
                dupSet.add(board[ny][nx]);
                nx += dx[dir]; ny += dy[dir];
            }
            dfs(nx - dx[dir], ny - dy[dir], dir + 1);
            reset(stack);
        }
    }

    static void solution() {
        // 시작 지점의 경우는 유저의 약간 보정이 필요함.
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                dfs(j, i, 0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new java.io.FileInputStream("sw_academy/java/res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            ans = -1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            sb.append(String.format("#%d %d\n", testCase, ans));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
