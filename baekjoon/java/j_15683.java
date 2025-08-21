/*
 * 감시
 * 
 * 문제 요약
 * cctv가 8대 설치, 감시 유형이 총 5가지
 * 감시하지 못하는 영역을 최소화하는 cctv의 배치 "방향"을 결정
 * 답은 영역이 최소화될 때 값을 출력
 * 벽은 cctv가 통과하지 못함.
 * 
 * 문제 해결
 * cctv에 대해 전체 액션을 검사하는 dfs 문제.
 * 액션은 총 4방향, 2방향, 1방향임. 이를 잘 관리하는 게 주된 문제
 * 
 * 주의 사항
 * 
 */
import java.io.*;
import java.util.*;

public class j_15683 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static final int empty = 0;
    static final int wall = 6;
    static final int fill = 7;

    static int[][] cctvs = {
        {},
        {0},
        {0, 2},
        {0, 1},
        {0, 1, 2},
        {0, 1, 2, 3}
    };

    static int[] rotate = {0, 4, 2, 4, 4, 1};
    static int cur, N, M;

    static class Node {
        int x, y, dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int[][] board;
    static List<Node> cctvArray = new ArrayList<>();

    static boolean isInside(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static int fill(int x, int y, int dir) {
        int nx = x, ny = y;
        int cnt = 0;

        while (true) {
            nx += dx[dir]; ny += dy[dir];
            if (!isInside(nx, ny)) break;
            if (board[ny][nx] == wall) break;
            if (board[ny][nx] == fill) continue;
            board[ny][nx] = fill;
            cnt++;
        }
        return cnt;
    }

    static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    /**
     * cctv idx를 바탕으로 접근해 dfs를 하는 재귀함수
     * @param idx - cctv idx
     * @param remain - 남은 빈 배열 개수
     */
    static void dfs(int idx, int remain) {
        if (idx == cctvArray.size()) { // 기저조건 cctv를 모두 찾았을 때
            cur = Math.min(cur, remain);
            return;
        }

        int nxRemain;
        int[][] newBoard;
        Node node = cctvArray.get(idx);
        int[] cctv = cctvs[node.dir];

        for (int i = 0; i < rotate[node.dir]; i++) {
            nxRemain = remain;
            newBoard = copyBoard(board);
            if (board[node.y][node.x] != fill)
            {
                board[node.y][node.x] = fill;
                nxRemain--;
            }
            for (int j = 0; j < cctv.length; j++) {
                nxRemain -= fill(node.x, node.y, cctv[j]);
            }
            dfs(idx + 1, nxRemain);

            board = copyBoard(newBoard);
            for (int j = 0; j < cctv.length; j++) {
                cctv[j] = (cctv[j] + 1) % 4;
            }
        }
    }

    static int solution() {
        dfs(0, cur);
        return cur;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        cur = N * M;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M ;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > empty && board[i][j] < wall) {
                    cctvArray.add(new Node(j, i, board[i][j]));
                }
                if (board[i][j] == wall)
                    cur --;
            }
        }

        System.out.println(solution());
    }
}
