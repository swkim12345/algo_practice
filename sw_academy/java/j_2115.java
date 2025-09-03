/*
 * 벌꿀채취
 * 
 * 문제 요약
 * N * N개의 벌통에서 M길이만큼 2개 선택(겹치면 안됨) 사이즈가 C인 꿀통에
 * 벌통에서 각각 채취함. 채취할 때 벌통에서 무조건 전체만 채취 가능, C는 넘기면 안됨.
 * 수익의 경우 한 용기에 담긴 꿀의 양의 제곱의 총 합임.
 * 수익의 합이 최대가 되는 경우를 찾고, 최대 수익을 출력하는 프로그램
 * 테케는 50개에 3초, N은 10 이하, M은 5 이하, 벌통의 개수는 N 이하로 구어짐. C는 10 ~30 이하
 * 꿀의 양은 1 이상 9 이하의 정수
 * 
 * 문제 풀이
 * 1. 완탐
 * 모든 격자 그래프에서 M길이짜리를 2개 선택하면 되는 경우임.
 * 순서는 상관 없으므로 조합문제.
 * 러프하게 생각하면, N**2 * M 정도의 시간복잡도를 가짐.
 */

import java.io.*;
import java.util.*;

public class j_2115 {
    static int[][] arr;
    static int N, M, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] table = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    table[i][j] = dfs(j, i, 0, 0, 0);
                }
            }

            int mx = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    for (int a = i; a < N; a++) {
                        for (int b = 0; b < N - M + 1; b++) {
                            if (i == a && j + M > b) continue;
                            mx = Math.max(mx, table[i][j] + table[a][b]);
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n", t, mx);
        }
    }
    static int dfs(int x, int y, int mv, int sum, int powerSum) {
        if (mv == M) { // 기저조건
            return powerSum;
        }

        int newSum = sum + arr[y][x + mv];
        int target = 0;
        if (newSum <= C) target = dfs(x, y, mv + 1, newSum, (int)(powerSum + Math.pow(arr[y][x + mv], 2)));
        target = Math.max(target, dfs(x, y, mv + 1, sum, powerSum));
        return target;
    }   
}