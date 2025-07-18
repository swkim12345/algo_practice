/*
 * 사이클 게임
 * https://www.acmicpc.net/problem/20040
 * 
 * 사이클을 확인하는 법은 다양하게 있는 데, 이 문제의 경우 각각 쿼리가 날라가는 형태이다.
 * dfs도 가능하고, 서로소집합(유니온 파인드) 도 가능하다.
 * 여기의 경우 선분이고, 방향이 없으므로 서로소 집합을 가지고 풀자.
 * 
 * 처음 사이클이 만들어지는 것 -> union 연산 시 부모 노드가 동일할 경우
 * 
 * 오답노트
 * 1. 여기서는 0부터 시작이네 슈벌;
 */

import java.io.*;
import java.util.*;

public class j_20040 {
    static int node[];
    StringBuilder sb;

    static boolean solution(int one, int two) {
        

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m, one, two;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        node = new int[n];
        for (int i = 0; i < n; i++) {
            node[i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            one = Integer.parseInt(st.nextToken());
            two = Integer.parseInt(st.nextToken());
            if (solution(one, two)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }
}
