/*
 * 사이클 없는 그래프 만들기
 * 
 * 문제 설명
 * 모든 정점이 연결된 그래프
 * 지워진 정점을 이용해 사이클이 없어지는 C 시간 구하기.
 * 
 * 문제 해설
 * 1. 처음에는 지울 정점을 바탕으로 bfs를 돌린다.
 * 1-1. 정점이 지워진 순서대로 stack에 삽입한다. (삽입시 스텝도 삽입)
 * 2. stack pop 하면서 엣지가 있는 지 확인.
 * 2-1. 상호간의 엣지가 있으면 union-find
 * 
 * 주의 사항
 * 
 */

import java.io.*;
import java.util.*;

public class j_30870 {
    static Deque<Integer> queue = new ArrayDeque<>();
    static Deque<Node> stack = new ArrayDeque<>();
    static List<List<Integer>> graph = new ArrayList<>(); // 정점마다 다음 
    static int[] parents; // 부모 저장
    
    static class Node {
        int k, time;

        Node(int k, int time) {
            this.k = k;
            this.time = time;
        }
    }

    static void bfs() {

    }

    static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    // boolean
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) 
            return false;
        parents[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M, K;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
    }
}