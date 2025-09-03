/*
 * 유기농 배추
 * 
 * 문제 요약
 * 배추를 해충에서 보호받을 수 있는 최소 배추흰지렁이의 개수를 구하는 문제
 * 
 * 문제 해결
 * 완전탐색을 이용해 bfs로 푼다.
 * 
 * 주의사항
 * 이 문제는 테스트케이스가 존재하는 문제이다.
 */

import java.io.*;
import java.util.*;

public class j_1012 {
    static final int BAECHU = 1;
    static Deque<int[]> deque;
    static int N, M;
    static final int[] dx = {0, 1, -1, 0};
    static final int[] dy = {1, 0, 0, -1};

    static boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
    
    // bfs를 이용 - 배추 그룹 지우기
    static void bfs(int x, int y, int[][] arr) {
        int nx, ny;
        int[] tmp;
        deque = new ArrayDeque<>();

        deque.addLast(new int[]{x, y});

        while (!deque.isEmpty()) {
            tmp = deque.pollLast();
            x = tmp[0]; y = tmp[1];
            arr[y][x] = 0;

            for (int i = 0; i < 4; i++) {
                nx = dx[i] + tmp[0];
                ny = dy[i] + tmp[1];

                if (!isInside(nx, ny)) continue; //나갔으므로
                if (arr[ny][nx] == BAECHU)
                    deque.addLast(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int K, x, y, ans;
        int[][] arr;

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;

            arr = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                arr[y][x] = BAECHU;
            }

            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == BAECHU) {
                        ans++; //그룹마다 ++
                        bfs(j, i, arr);
                    }
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.print(sb.toString());
    }
}
