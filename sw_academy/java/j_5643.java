package sw_academy.java;
/*
 * 키 순서
 * 
 * 문제 요약
 * 어떤 친구는 자신의 키를 정확히 알 수 있다.
 * 나보다 키가 큰 사람을 알고, 나보다 더 작은 사람의 수를 알아야 한다.
 * 이 사람들의 합( + 나까지)해서 모든 사람의 수와 동일하면 나의 키를 정확히 알 수 있다.
 * 자신의 키를 정확히 알 수 있는 사람의 수는?
 * 
 * 문제 해결
 * 모든 간선을 저장한 다음, 한 점에서 시작하는 dfs를 시작
 * 키 큰 사람으로 가는 간선 저장 / 키 작은 사람으로 가는 간선(역방향)을 따로 저장
 * 한 점에서 시작하는 dfs를 시작해 rank를 노드에 저장함(방문하지 않았다면 0만 있음.)
 * (rank는 시작값이 1임)
 * 양쪽 간선에 대해 dfs를 끝내고, (각 노드의 두 rank의 합 - 1) == (전체 사람 수) 라면
 * cnt++를 해준다.
 * 
 * 주의 사항
 * 1부터 사람 번호 시작
 */

import java.io.*;
import java.util.*;

public class j_5643 {
    static int N;
    static Node[] student;

    static class Node {
        int smallerRank, biggerRank;
        List<Integer> smaller;
        List<Integer> bigger;
        Node() {
            smallerRank = 0; biggerRank = 0;
            smaller = new ArrayList<>();
            bigger = new ArrayList<>();
        }
    }
    
    /**
     * 작은 것, 큰 것을 기준으로 하는 dfs, 각 스텝별로 더 커지면서 진행됨.
     * 사이클은  발생하지 않음(키 순서대로 있다는 것이 보장됨.)
     * @param step
     * @param isSmaller
     */
    static void dfs(int step, boolean isSmaller) {

    
    }

    static int solution() {
        dfs(0, true);
        dfs(0, false);
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            if (student[i].smallerRank + student[i].biggerRank - 1 == N) ans++;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int M;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            student = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                student[i] = new Node();
            }

            int a, b;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                student[b].smaller.add(a);
                student[a].bigger.add(b);
            }

            System.out.printf("#%d %d\n", testCase, solution());
        }
    }
}