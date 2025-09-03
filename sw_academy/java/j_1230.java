package sw_academy.java;
/*
 * 8일차 - 암호문3
 * 
 * 문제요약
 * 암호문 뭉치로 주어지고, 뭉치 사이에 삽입 혹은 삭제, 가장 뒤에 추가하는 명령어가 있다.
 * 60ms내에 쿼리를 모두 실행해라.
 * 
 * 문제 풀이
 * 암호문 뭉치는 LinkedList<String>으로 관리하고, 주어진 세 액션을 다루자.
 * 다룰 때 액션마다 오는 값이 다르므로, 이는 switch case로 관리하자.
 * 
 * 주의
 * 입력, 출력 모두 사이즈가 크다. 따라서, bufferedReader, writer를 사용하자.
 */
import java.io.*;
import java.util.*;

public class j_1230 {
    public static void main(String[] args) throws IOException{
        // System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = 10;
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<String> secrets = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                secrets.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                char query = st.nextToken().charAt(0);
                int x, y;

                switch (query) {
                    case 'I': {
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            secrets.add(x + j, st.nextToken());
                        }
                        break;
                    }
                    case 'D': {
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            secrets.remove(x);
                        }
                        break;
                    }
                    case 'A': {
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            secrets.add(st.nextToken());
                        }
                        break;
                    }
                }
            }
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(secrets.get(i)).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
