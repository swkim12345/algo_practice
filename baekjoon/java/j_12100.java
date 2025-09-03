/*
 * 2048(Easy)
 * 
 * 문제 요약
 * 이번에는 2048 구현이다
 * 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 프로그램 작성하기
 * 
 * 문제 풀이
 * 최소한 감소하지 않는다 -> 마지막에 검사해도 무방하다.
 * 다만, 2, 4 등 블록이 합쳐질 때 방향에 따라서 어느 쪽을 검사하는 지가 달라진다.
 * 이동방향이 위라면 -> 가장 상단부터 검사하고 하나씩 내려온다. 블록끼리는 outside가 아니라면 블록의 수가 같다면 합쳐짐.
 * 다만 합쳐질 때 한번 합쳐지면 더이상 합쳐지만 안 됨
 * 각 보드는 독립적이므로, 이를 관리하기 위한 클래스가 필요함.
 * step, 보드, 충돌값, N을 저장하고, 이를 큐에 넣고 뺌으로써 각 스텝별로 처리해주면 됨.
 * 
 * 주의사항
 * int 범위는 넘지 않음 - 1024 * 400이 최대임.
 */

import java.io.*;
import java.util.*;

public class j_12100 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static class Board {
        int[][] board;
        int N, step;
        boolean[][] coalesce;

        Board(int N) {
            this.N = N;
            board = new int[N][N];
            coalesce = new boolean[N][N];
            step = 0;
        }

        // 복사생성자 사용
        Board(Board b) {
            this.N = b.N;
            board = new int[N][N];
            coalesce = new boolean[N][N];
            this.step = b.step;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    this.board[i][j] = b.board[i][j];
                }
            }

        }
    }

    static int ans;

    static boolean isInside(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int count(Board b) {
        int[][] board = b.board;
        int mx = Integer.MIN_VALUE;

        for (int i = 0; i < b.N; i++) {
            for (int j = 0; j < b.N; j++) {
                mx = Math.max(board[i][j], mx);
            }
        }
        return mx;
    }

    static void moveOrMerge(int x, int y, int nx, int ny, int[][] board) {
        if (ny == y && nx == x) return; // 같은 값이면 처리하지 않음.
        board[ny][nx] += board[y][x];
        board[y][x] = 0;
    }

    static void moveBlock(int x, int y, int dir, Board newBoard) {
        int nx = x, ny = y;
        int[][] board = newBoard.board;
        int N = newBoard.N;

        // inside && (충돌 났으면 이동 종료 || 충돌 안났으면 숫자 검증 검증 후 합침 or 이동종료)
        while (true) {
            if (!isInside(nx + dx[dir], ny + dy[dir], N)) {
                break;
            }

            nx += dx[dir]; ny += dy[dir];
            if (newBoard.coalesce[ny][nx] == true) {
                nx -= dx[dir]; ny -= dy[dir];
                break;
            } else if (board[ny][nx] != 0) {
                if (board[ny][nx] != board[y][x]) {
                    nx -= dx[dir]; ny -= dy[dir];
                } else {
                    newBoard.coalesce[ny][nx] = true; // 병합처리
                }
                break;
            }
        }
        moveOrMerge(x, y, nx, ny, board);
    }

    static Board step(int dir, Board b) {
        Board newBoard = new Board(b);
        int N = newBoard.N;

        newBoard.step += 1;

        switch(dir) {
            case 0: { //위로 이동
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (newBoard.board[y][x] == 0) continue;
                        moveBlock(x, y, dir, newBoard);
                    }
                }
                break;
            }
            case 1: { // 아래로 이동
                for (int y = N - 1; y >= 0; y--) {
                    for (int x = 0; x < N; x++) {
                        if (newBoard.board[y][x] == 0) continue;
                        moveBlock(x, y, dir, newBoard);
                    }
                }
                break;
            }
            case 2: { // 왼쪽으로 이동
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        if (newBoard.board[y][x] == 0) continue;
                        moveBlock(x, y, dir, newBoard);
                    }
                }
                break;
            }
            case 3: {
                // 오른쪽으로 이동
                for (int x = N - 1; x >= 0; x--) {
                    for (int y = 0; y < N; y++) {
                        if (newBoard.board[y][x] == 0) continue;
                        moveBlock(x, y, dir, newBoard);
                    }
                }
                break;
            }
        }
        return newBoard;
    }

    // queue를 이용한 bfs
    static void bfs(Board board) {
        Queue<Board> queue = new ArrayDeque<>();

        queue.add(board);

        while (!queue.isEmpty()) {
            // 기저조건 - step이 5일 경우 - 전체 카운팅해서 ans 에 추가
            board = queue.poll();
            if (board.step == 5) {
                ans = Math.max(ans, count(board));
                continue;
            }

            // 4방 탐색
            for (int i = 0; i < 4; i++)
                queue.add(step(i, board));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Board board = new Board(N);
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board.board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(board);
        System.out.println(ans);
    }
}
