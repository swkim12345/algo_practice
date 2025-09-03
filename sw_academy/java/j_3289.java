package sw_academy.java;

/*
 * 서로소 집합
 * 
 * 문제 해설
 * query가 들어오고, 0이면 union, 1이면 같은 부모 확인해 같으면 1, 아니면 0 반환
 * 
 * 문제 풀이
 * 시뮬레이션, unionfind
 */

import java.io.*;
import java.util.*;

public class j_3289 {
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
