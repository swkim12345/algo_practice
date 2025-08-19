package sw_academy.java;

/*
 * 햄버거 다이어트
 * 
 * 문제 요약
 * 정해진 칼로리 이하에서 최대의 점수를 가져올 수 있는 햄버거의 점수를 출력하는 함수
 * 
 * 문제 접근
 * 조합문제, 순서는 상관 없음.
 * 조건 - 칼로리. 칼로리 "이하" 조합
 * 정답 - 최대 점수
 * 재료 저장 자료구조 - List<int[]> - 0 : 점수, 1 : 칼로리
 * 
 * 재귀로 구현
 * 칼로리 초과시 INF로 리턴
 * 알고리즘 분류 - 브루트포스, 조합
 * 
 * 조심
 * dfs를 이용해 구현할 때 조심하자.
 */

import java.io.*;
import java.util.*;

public class j_5215 {
    static List<int[]> ingredients;
    static int ans;
    
    final static int INF = Integer.MAX_VALUE;

    static void solution(int idx, int calorie, int score, int targetCalorie) {
        // 종료조건 - 칼로리 초과시 - 바로 리턴
        if (calorie > targetCalorie) {
            return;
        }
        
        // 종료조건 2 - ingredients의 끝에 도달했을 경우 - 칼로리 측정 후 리턴
        if (idx == ingredients.size()) {
            ans = Math.max(ans, score);
            return ;
        }

        // 선택
        solution(idx + 1, calorie + ingredients.get(idx)[1], score + ingredients.get(idx)[0], targetCalorie);
        // 미선택
        solution(idx + 1, calorie, score, targetCalorie);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T, N, L, score, K;

        T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            ans = 0;
            ingredients = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                score = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                ingredients.add(new int[]{score, K});
            }
            solution(0, 0, 0, L);

            System.out.printf("#%d %d\n", testCase, ans);
        }
    }
}
