/*
 * 빵집
 * 
 * 문제 요약
 * 
 * 처음 열과 마지막 열을 연결하는 가스관을 만드는 문제,
 * 이동은 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
 * 
 * 문제 해결
 * 점이 중요한 것이 아니라, 연결 경로가 중요한 문제.
 * dp 로 풀 수 있음.
 * 왼쪽 점부터 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선으로 방문한다. 
 * 이때, 이 것이 그리디로 할 수 있냐? - 할 수 있음.
 * 이것 이외에 최적 경로가 없다. (점 끼리 하나만 연결(greedy), 뒤로가는 것 불가능(dp 가능 사유))
 * 
 * 주의
 * 첫번째, 마지막 열은 항상 비워져 있음.
 * 
 * 오답노트
 * 방문한 노드에서 dfs를 한 결과에서 더이상 도달하지 못했다면 -> 더이상 그 노드에서 탐색할 필요가 없음!
 * 따라서 visitied 를 롤백할 필요가 없습니다
 */

import java.io.*;
import java.util.*;

public class j_3109 {
    static char[][] arr;

    static final char BUILDING = 'x';
    static final char EMPTY = '.';
    static final char VISITED = 'O';
    static final int[] dx = {1, 1, 1};
    static final int[] dy = {-1, 0, 1};

    static boolean isInside(int x, int y, int C, int R) {
        return (x >= 0 && x < C && y >= 0 && y < R);
    }

    static boolean isEmptyWay(int x, int y) {
        return arr[y][x] == EMPTY;
    }

    static boolean dfs(int x, int y, int C, int R) {
        int nx, ny;
        // 종료조건 : x 좌표가 C - 1에 도달
        if (x == C - 1) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (isInside(nx, ny, C, R) && isEmptyWay(nx, ny)) {
                arr[ny][nx] = VISITED;
                if (dfs(nx, ny, C, R)) return true;
                // arr[ny][nx] = EMPTY;
            }
        }
        return false;
    }

    static int solution(int C, int R) {
        int ans = 0;

        // 점마다 엣지를 그리디하게 선택
        for (int i = 0; i < R; i++) {
            dfs(0, i, C, R);
        }

        // 마지막에 카운팅
        for (int y = 0; y < R; y++) {
            if (arr[y][C - 1] == VISITED) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;

        int R = Integer.parseInt(st.nextToken()); // y 좌표 길이
        int C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        System.out.println(solution(C, R));
    }    
}
