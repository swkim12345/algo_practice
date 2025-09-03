package sw_academy.java;

/*
 * 요리사
 * 
 * 문제 요약
 * a, b 음식을 만들고, 만들 때 전체 음식 재료 중 절반만 사용함.
 * 절반을 사용해서 만들 때, 두 음식값의 맛의 차이가 최소가 될 때 최솟값을 정답으로 출력
 * 
 * 문제 풀이
 * NCN/2로 선택한 다음 선택된 것에서 더해주는 dfs, 나머지는 전체 합에서 현재 합을 빼줌
 * 따라서, 나머지는 2 * (현재합) - 전체 합의 절대값이다.
 * dfs - step, start, sum
 * 최적화를 위해서는 0 ~ N/2 - 1까지 현재 값을 선택하고, 가지치기하는 방법이 있긴 한데... 굳이..?
 * 식재료를 전역변수로 설정하고 dfs를 이용해 탐색하자. start를 이용해 이미 선택된 것 이전값은 선택하지 않게
 * 처리하자.
 * 
 */

import java.io.*;
import java.util.*;

public class j_4012 {
    static int N;
    static int ans;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = 0;
            arr = new int[N][N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }


        }
    }
}
