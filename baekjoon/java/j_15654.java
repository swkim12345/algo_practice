/*
 * N과 M
 * 
 * 수열
 * 정렬을 한 다음, 이를 바탕으로 조합을 하는 문제임.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class j_15654 {
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void combination(int N, int M, boolean[] visited, Deque<Integer> combination) {
        // 종료조건 : M과 combination 사이즈가 동일할 때
        if (combination.size() == M) {
            for (int value : combination) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == true) continue;
            visited[i] = true;
            combination.addLast(arr[i]);
            combination(N, M, visited, combination);
            visited[i] = false;
            combination.pollLast();
        }
    }

    static void solution(int N, int M) {
        combination(N, M, new boolean[N], new ArrayDeque<Integer>());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        solution(N, M);
        System.out.print(sb.toString());
    }
}
