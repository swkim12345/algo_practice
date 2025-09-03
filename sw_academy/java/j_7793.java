package sw_academy.java;

import java.io.*;
import java.util.*;

public class j_7793 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] arr;
    static boolean[][] visited;
    static boolean[][] flood; //물 퍼짐
    static int N, M;
    static Deque<int[]> visitQueue = new ArrayDeque<>();
    static Deque<int[]> floodQueue = new ArrayDeque<>();
    static int x, y;
    static int targetX, targetY;

    static char empty = '.';
    static char water = '*';
    static char stone = 'X';
    static char beaver = 'D';
    static char kaktus = 'S';
    
    static boolean isInside(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static void bfs(boolean[][] visit, Deque<int[]> queue, boolean isKaktus) {
        int nx, ny;
        int[] now;

        int size = queue.size();
        while (size-- > 0) {
            now = queue.pollFirst();
            x = now[0]; y = now[1];
            // System.out.printf("%d %d %b\n", x, y, isKaktus);

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i]; ny = y + dy[i];
                if (isInside(nx, ny) && !visit[ny][nx] && arr[ny][nx] != stone) {
                    if (visit[ny][nx] == true) continue;
                    if (isKaktus) { // 고슴도치일 경우 - 물 안 됨
                        if (flood[ny][nx]) continue;
                    } else { // 물일 경우 - 비버 굴 통과 안 됨
                        if (arr[ny][nx] == beaver) continue;
                    }
                    visit[ny][nx] = true;
                    queue.addLast(new int[]{nx, ny});
                    // System.out.printf("queuing %d %d %b\n", x, y, isKaktus);
                }
            }
        }
    }

    static void solution() {
        int ans = -1;
        int step = 0;

        // 물부터 먼저 이동
        while(!visitQueue.isEmpty()) {
            step += 1;
            bfs(flood, floodQueue, false);
            bfs(visited, visitQueue, true);
            if (visited[targetY][targetX]) {
                ans = step;
                break;
            }
        }
         
        if (ans == -1) {
            System.out.println("GAME OVER");
        } else {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new char[N][M];
            visited = new boolean[N][M];
            flood = new boolean[N][M];
            visitQueue.clear();
            floodQueue.clear();

            String str;
            for (int i = 0; i < N; i++) {
                str = br.readLine().trim();
                for (int j = 0; j < M; j++) {
                    arr[i][j] = str.charAt(j);
                    if (arr[i][j] == kaktus) {
                        arr[i][j] = empty;
                        x = j; y = i;
                        visitQueue.add(new int[]{j, i});
                        visited[i][j] = true;
                    }
                    if (arr[i][j] == water) {
                        arr[i][j] = empty;
                        floodQueue.add(new int[]{j, i});
                        flood[i][j] = true;
                    }
                    if (arr[i][j] == beaver) {
                        targetX = j; targetY = i;
                    }
                }
            }
            System.out.printf("#%d ", t);
            solution();
        }
    }
}
