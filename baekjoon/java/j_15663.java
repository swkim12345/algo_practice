/*
 * N과 M(9)
 * 
 * 문제 요약
 * 중복되는 수 없이 순열을 구하는 문제
 * 
 * 문제 풀이
 * 수를 삽입하기 전 HashSet으로 중복되는 지 체크하고 순열을 만드는 문제임
 * 
 * 주의
 */

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;

public class j_15663 {
    static Set<Integer> set = new HashSet<>();
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void dfs(int N, int M, int idx, Deque<Integer> combination) {
        // 종료조건
        if (combination.size() == M) {
            for (Integer val : combination) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < N; i++) {
            combination.addLast(list.get(i));
            dfs(N, M, i + 1, combination);
            combination.pollLast();
        }
    }

    static void combination(int N, int M) {
        dfs(N, M, 0, new ArrayDeque<>());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M, tmp;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            list.add(tmp);
        }

        list.sort((e1, e2) -> Integer.compare(e1, e2));
        combination(N, M);
        System.out.print(sb.toString());
    }
}
