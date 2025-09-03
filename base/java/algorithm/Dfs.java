package base.java.algorithm;

import java.util.*;
import java.io.*;

/*
 * DFS 구현
 * 인접 리스트를 이용한 구현체
 * 무방향 그래프를 가정하고 문제 풀이.
 * node 번호가 1 시작, size 이하
 * 방향이 있다면, 반대방향은 삽입하지 않으면 됨.
 * 예시 입력값
7
6
1 6
6 3
3 5
4 1
2 4
4 7

 */

public class Dfs {
    static boolean[] visited;

    static void dfs(List<List<Integer>> graph, int start) {
        List<Integer> node = graph.get(start);
        visited[start] = true;
        System.out.println(start);

        for (Integer val : node) {
            if (!visited[val]) {
                dfs(graph, val);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> graph = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int a, b;
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a); // 방향이 존재한다면, 이걸 없애면 됨.
        }

        dfs(graph, 1);
    }
}
