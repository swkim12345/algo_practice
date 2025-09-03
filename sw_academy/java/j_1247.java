package sw_academy.java;

/*
 * 3일차 - 최적경로
 * 
 * 문제 요약
 * 좌표가 x, y로 주어지고, 거리 계산은 맨해탄 거리
 * 회사에서 출발해 모두 방문하고 집으로 돌아오는 경로 중 총 이동거리가 가장 짧은 경로 찾기
 * N이 10 이하이고, 효율적으로 찾지 않아도 된다라는 조건항이 있음.
 * 
 * 문제 풀이
 * 완전탐색, 재귀를 이용해 조합을 이용해 선택하고, 방문이 모두 완료되면 집까지 가는 경로와 함께 해서
 * 최소 거리로 갱신해준다.
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class j_1247 {
    static Point house;
    static Point company;
    static ArrayList<Point> targets;
    static boolean[] visited;
    static int ans;

    static int manhattanDst(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    static void combination(int step, int totalDst, Point before, int N) {
        Point target;

        if (step == N) { // 종료조건  step과 N이 동일할 때 - 모든 정점 방문함.
            ans = Math.min(ans, totalDst + manhattanDst(before, house));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == true) continue;
            visited[i] = true;
            target = targets.get(i);
            combination(step + 1, totalDst + manhattanDst(before, target), target, N);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            targets = new ArrayList<>();
            visited = new boolean[N];
            ans = Integer.MAX_VALUE;

            int x, y;
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            company = new Point(x, y);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            house = new Point(x, y);

            for (int i = 0; i < N; i++) {
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                targets.add(new Point(x, y));
            }

            combination(0, 0, company, N);
            System.out.printf("#%d %d\n", testCase, ans);
        }
    }
}
