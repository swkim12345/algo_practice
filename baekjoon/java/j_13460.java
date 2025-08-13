/*
 * 구슬 탈출
 * 
 * 문제 요약
 * 왼쪽, 오른쪽, 위쪽, 아래쪽으로 기울여서 빨간구슬 만! 뽑아내는 문제
 * 
 * 문제 풀이
 * 각각의 액션(4개)에 대해 bfs를 수행하고, 10번 수행해서 만약 그 전에 끝나면 몇번 출력, 아니라면 -1 출력
 * 이동 시 두 구슬의 위치에 따라 간섭이 발생할 수 있음.
 * 나의 경우는 이를 좌표의 대소를 바탕으로 먼저 움직이는 것을 결정하고, 이동처리함.
 * 각각 공 중 어떤 것을 먼저 움직일 지 / 어느 방향으로 움직일 지를 결정해야함.
 * 공 움직일 때는 단위 벡터 개념을 활용해 절대값을 나눠주고, 만약 red공보다 blue가 앞에 있다면 blue부터 이동처리.
 * 
 * 정답 : 10번 이하 수행 해서 빨간구슬만 탈출 - 수행 횟수 출력, 10번 초과시 -1 출력(큐에 10 이하의 값이 없다면)
 * 
 * 실패 조건 : 파란구슬이 출구에 들어갈 때 - 탐색 종료, 다른 곳을 탐색. 
 * 이 때는 -1을 출력하지 않음(다른 곳에서 해결할 수 있기 때문에)
 * 
 * 주의 조건
 */

import java.io.*;
import java.util.*;

public class j_13460 {
    static char[][] board;
    static Deque<Balls> queue = new ArrayDeque<>();
    static int N, M;

    // 보드에서 이동이므로 dx, dy 정의
    static final int[] dx = {0, -1, 1, 0};
    static final int[] dy = {1, 0, 0, -1};
    static final char red = 'R';
    static final char blue = 'B';
    static final char empty = '.';
    static final char hole = 'O';
    static final char wall = '#';

    static class Balls {
        //cnt : 현재 이동 횟수
        int rx, ry, bx, by, cnt;
        Balls (int ry, int rx, int by, int bx, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    static boolean isInside(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static boolean isEmptyWay(int x, int y) {
        return board[y][x] == empty || board[y][x] == hole; // 구멍의 경우 빠짐
    }

    // 다른 공과 충돌 판정
    static boolean isEmptyWay(int x, int y, int anotherX, int anotherY) {
        return !(x == anotherX && y == anotherY) || board[y][x] == hole; //구멍의 경우 바로 빠짐
    }

    // 공을 이동시키는 함수. 장애물이 있으면 바로 종료
    // 다른 공의 좌표값을 another 값에 저장 후 충돌 판정
    // nx, ny 리턴
    static int[] move(int x, int y, int anotherX, int anotherY, int idx) {
        int nx, ny;

        // 장애물을 만나기 전까지 이동 처리
        while (true) {
            nx = x + dx[idx];
            ny = y + dy[idx];

            if (isInside(nx, ny) && isEmptyWay(nx, ny) && isEmptyWay(nx, ny, anotherX, anotherY)) {
                x = nx; y = ny;
                // 구멍에 빠진다면 얼리리턴
                if (board[y][x] == hole) {
                    // System.out.println("hole");
                    break;
                }
            } else {
                break;
            }
        }
        return new int[]{x, y};
    }

    // 이동횟수 리턴
    static int solution() {
        Balls balls;
        int ans = -1;
        int nBlueX, nBlueY, nRedX, nRedY;
        int dirX, dirY; // red와 blue간의 벡터를 단위벡터로 만든 값
        int[] nextLocation;
        boolean isBlueFirstMove;

        // 종료조건 - queue 내에 더이상 없을 경우
        while (!queue.isEmpty()) {
            balls = queue.pollFirst();
            // skip - cnt가 10이상일 경우
            if (balls.cnt > 10) continue;

            nBlueX = balls.bx; nBlueY = balls.by;
            nRedX = balls.rx; nRedY = balls.ry;
        
            // System.out.printf("%d %d %d %d\n", nRedX, nRedY, nBlueX, nBlueY);

            // 종료조건 - 구멍에 빠진 경우
            if (board[nBlueY][nBlueX] == hole) continue; // blue의 경우 버림
            if (board[nRedY][nRedX] == hole) { // 정답 : red가 hole에 빠진 경우
                ans = balls.cnt;
                break;
            }

            // 4방향 이동
            for (int i = 0; i < 4; i++) {
                isBlueFirstMove = false; //default : blue는 나중에 움직이기

                nBlueX = balls.bx; nBlueY = balls.by;
                nRedX = balls.rx; nRedY = balls.ry;
                dirX = nBlueX - nRedX; // red -> blue로 가는 벡터
                if (dirX != 0)
                    dirX = dirX / Math.abs(dirX); // 단위벡터화
                
                dirY = nBlueY - nRedY;
                if (dirY != 0)
                    dirY = dirY / Math.abs(dirY);

                // 단위벡터로 같은 방향성을 가진다면, blue가 red보다 앞에 있음.
                if (dirX == dx[i] && dirY == dy[i]) isBlueFirstMove = true;

                if (isBlueFirstMove) {
                    nextLocation = move(nBlueX, nBlueY, nRedX, nRedY, i);
                    nBlueX = nextLocation[0]; nBlueY = nextLocation[1];
                    nextLocation = move(nRedX, nRedY, nBlueX, nBlueY, i);
                    nRedX = nextLocation[0]; nRedY = nextLocation[1];
                } else {
                    nextLocation = move(nRedX, nRedY, nBlueX, nBlueY, i);
                    nRedX = nextLocation[0]; nRedY = nextLocation[1];
                    nextLocation = move(nBlueX, nBlueY, nRedX, nRedY, i);
                    nBlueX = nextLocation[0]; nBlueY = nextLocation[1];
                }
                // 정지상태의 경우 skip
                if (balls.bx == nBlueX && balls.by == nBlueY && balls.rx == nRedX && balls.ry == nRedY) {
                    continue;
                }

                // 큐에 삽입
                queue.addLast(new Balls(nRedY, nRedX, nBlueY, nBlueX, balls.cnt + 1));
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;
        char c;
        int rx = 0, ry = 0, bx = 0, by = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            str = br.readLine().trim();

            for (int j = 0; j < M; j++) {
                c = str.charAt(j);
                // red, blue 좌표 설정
                if (c == red) {
                    ry = i; rx = j;
                    c = empty;
                }
                if (c == blue) {
                    by = i; bx = j;
                    c = empty;               
                }
                board[i][j] = c;
            }
        }
        // 초기 시작값 삽입
        queue.add(new Balls(ry, rx, by, bx, 0));

        System.out.println(solution());
    }
}
