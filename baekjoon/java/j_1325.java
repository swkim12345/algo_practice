/*
 * 효율적인 해킹
 * 
 * 문제 요약
 * A B == B -> A 간선관계 (방향 그래프 문제)
 * 신뢰하는 관계가 주어지면 많은 컴퓨터를 해킹할 수 있는 컴퓨터 번호 출력
 * 
 * 문제 해결
 * 메모이제이션을 이용한 dfs 문제라고 생각하면 될 거 같다.
 * 처음에는 자기 자신 해킹이므로 1로 초기화하고, 임의의 노드부터 시작하되, 1이 아닌 숫자를 만나면 dfs를 종료하고
 * 리턴하면서 +1처리해준다.
 * 
 * 주의점
 * 컴퓨터 : 1번부터 있음
 */

import java.io.*;
import java.util.*;

public class j_1325 {
    static int[] hacking;
    static boolean[] visited;
    static List<List<Integer>> graph; // 방향그래프 저장하는 인접 그래프
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hacking = new int[N];
        Arrays.fill(hacking, 1);

        for (int i = 0; i < N; i++) {
            
        }

        for (int i = 0; i < M; i++) {

        }
    }
}
