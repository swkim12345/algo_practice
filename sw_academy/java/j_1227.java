package sw_academy.java;

/*
 * 미로2
 * 
 * 문제 해설
 * 미로에서 시작점과 도착점이 주어질 때, 나갈 수 있는 길이 있는 지 파악하는 프로그램 제작
 * 
 * 문제 풀이
 * bfs로 풀면 됨
 * 가능하면 1, 불가능하면 0
 * 
 * 주의사항
 * 0,0 시작, 벽은 조건에 없지만, 혹시나 모르니 나갈수도 있다고 생각하자.
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class j_1227 {
    static final int empty = 0;
    static final int wall = 1;
    static final int start = 2;
    static final int exit = 3;

    static final int size = 100;
    
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    static int[][] maze = new int[size][size];
    static int startX, startY;

    static boolean isGoodToGo(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && maze[y][x] != wall;
    }

    static int solution() {
        Deque<Point> queue = new ArrayDeque<>();
        int ans = 0;

        queue.add(new Point(startX, startY));
        maze[startY][startX] = wall;

        Point p, nxP;
        while (!queue.isEmpty()) {
            p = queue.pollFirst();
            
            for (int i = 0; i < 4; i++) {
                nxP = new Point(p.x + dx[i], p.y + dy[i]);
                if (!isGoodToGo(nxP.x, nxP.y)) continue;
                if (maze[nxP.y][nxP.x] == exit) { // 종료조건 - 도달
                    ans = 1;
                    break;
                }
                maze[nxP.y][nxP.x] = wall;
                queue.addLast(nxP);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        for (int i = 1; i <= 10; i++) {
            br.readLine();
            for (int y = 0; y < size; y++) {
                str = br.readLine().trim();
                for (int x = 0; x < size; x++) {
                    maze[y][x] = str.charAt(x) - '0';
                    if (maze[y][x] == start) {
                        startX = x; startY = y;
                    }
                }
            }

            System.out.printf("#%d %d\n", i, solution());
        }
    }
}