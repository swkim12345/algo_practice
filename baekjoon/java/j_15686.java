/*
 * 치킨 배달
 * 
 * 문제 요약
 * 크기가 N*N 도시, 1부터 시작
 * 빈칸0, 집1, 치킨집2
 * M개만큼 치킨집을 남겼을 때, 도시의 치킨거리가 최소일 때 값을 출력하라.
 * 
 * 문제 풀이
 * 1. 집과 치킨집간의 거리를 모두 구한 뒤 정렬
 * 2. 남길 치킨집 M을 구한 뒤, 치킨집의 인덱스를 바탕으로 아까 구한
 * 집과 치킨집간의 거리 배열을 순회하면서 최소값을 찾고, 최소값을 바탕으로 
 * 전역변수 ans 업데이트
 * 3. ans 출력
 */
import java.io.*;
import java.util.*;
import java.awt.*;

public class j_15686 {
    static final int HOUSE = 1;
    static final int CHICKEN = 2;

    static int ans, N, M;
    static int[][] arr;
    static ArrayList<Point> chickens;
    static ArrayList<Point> houses;
    static ArrayList<ArrayList<Integer>> distances; // 집 인덱스 - 치킨집으로 접근

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        ans = Integer.MAX_VALUE;
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        distances = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == HOUSE) {
                    houses.add(new Point(j, i));
                    distances.add(new ArrayList<>());
                }
                else if (arr[i][j] == CHICKEN) chickens.add(new Point(j, i));
            }
        }

        for (int i = 0; i < houses.size(); i++) {
            for (Point chicken : chickens) {
                int dist = distance(houses.get(i), chicken);
                distances.get(i).add(dist);
            }
        }

        combination(0, new HashSet<>());
        System.out.println(ans);
    }

    static int distance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    static void combination(int len, HashSet<Integer> select) {
        if (len == chickens.size() && select.size() < M) return;
        // 기저 조건 : select.size == M 
        if (select.size() == M) {
            // 순회하면서 최소값 구해야함
            int localAns = 0;
            for (int i = 0; i < houses.size(); i++) {
                int min = Integer.MAX_VALUE;
                ArrayList<Integer> distance = distances.get(i);

                for (int j = 0; j < chickens.size(); j++) {
                    if (!select.contains(j)) continue;
                    min = Math.min(min, distance.get(j));
                }
                localAns += min;
            }
            ans = Math.min(ans, localAns);
            return;
        }
        // 현재 선택 혹은 미선택
        combination(len + 1, select);
        select.add(len);
        combination(len + 1, select);
        select.remove(len);
    }
}
