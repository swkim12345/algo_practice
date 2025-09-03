package sw_academy.java;


/*
 * 7일차 - 수열 편집
 * 
 * 문제 요약
 * 수열에 쿼리가 날라감.
 * 
 * 문제 풀이
 * 그냥 그대로 구현
 */

import java.io.*;
import java.util.*;

public class j_5122 {
    public static void main(String[] args) throws IOException{
        // System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                char query = st.nextToken().charAt(0);
                int x, y;

                switch (query) {
                    case 'I': {
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        list.add(x, y);
                        break;
                    }
                    case 'D': {
                        x = Integer.parseInt(st.nextToken());
                        list.remove(x);

                        break;
                    }
                    case 'C': {
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        list.set(x, y);
                        break;
                    }
                }
            }
            sb.append("#").append(t).append(" ");
            if (L >= list.size()) sb.append(-1);
            else sb.append(list.get(L));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
