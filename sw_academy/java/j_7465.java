package sw_academy.java;

/*
 * 창용 마을 무리의 개수
 * 
 * 문제 요약
 * 사람이 1 ~ N까지 있음.
 * 서로 알거나, 거쳐서 안다면 하나의 무리라 함.
 * 무리의 개수 구하기
 * 
 * 문제 해결
 * 전형적인 union find문제
 * 입력값을 모두 union한다음, 다 끝난다면 한번 find한 뒤 set에 부모를 넣고,
 * set의 개수를 세면 되는 문제
 * 
 * 주의
 * 그룹은 1부터 시작임.
 */

import java.io.*;
import java.util.*;

public class j_7465 {
    static int[] arr;
    static Set<Integer> groups;

    static void init(int N) {
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }
    }

    static int find(int x) {
        if (x == arr[x]) return x;

        return arr[x] = find(arr[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        arr[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        int N, M;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            groups = new HashSet<>();

            init(N);

            int u, v;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                union(u, v);
            }

            for (int i = 1; i <= N; i++) {
                groups.add(find(i));
            }

            sb.append(String.format("#%d %d\n", testCase, groups.size()));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }  
}
