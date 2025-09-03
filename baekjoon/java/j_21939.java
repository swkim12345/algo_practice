/*
 * 문제 추천 시스템 version 1
 * 
 * 문제 요약
 * 문제 번호, 난이도를 이요해 들어오게 됨.
 * recommand x
 * 1 일 경우 -> 가장 어려운 문제 번호. 어려운 문제 여러개시 문제번호 큰 것 출력
 * -1 -> 가장 쉬운 문제 번호, 쉬운 문제 여러개시 문제번호 낮은 것 출력
 * add P L -> 난이도가 L인 문제번호 P 추가, 삭제된 것이 들어올 수도 있음.
 * solved P - 추천 문제 리스트에서 문제번호 P 제거
 * 
 * 문제 풀이
 * 가장 어려운 문제 / 쉬운 문제 + 문제번호 순 정렬이므로, 앞 뒤로 정렬이 되어 있는 자료구조가 
 * 필요하다.
 * 따라서, TreeSet을 이용해 문제를 풀자.
 * 추가, 삭제 시 treeMAp을 전체 순회할 수 없으므로, key - 문제번호 인 set을 사용하자.
 * boolean이어도 상관 없음(10만밖에 되지 않음)
 * 
 * 주의사항
 */

import java.io.*;
import java.util.*;

public class j_21939 {
    static TreeSet<Problem> problems;
    static Map<Integer, Problem> isExists;

    static class Problem implements Comparable<Problem> {
        int P, L;
        Problem(int P, int L) {
            this.P = P;
            this.L = L;
        }

        @Override
        public int compareTo(Problem p) {
            int cmp = Integer.compare(this.L, p.L);
            if (cmp == 0) {
                return Integer.compare(this.P, p.P);
            } else {
                return cmp;
            }
        }
    }

    static void add(int P, int L) {
        Problem p = new Problem(P, L);
        problems.add(p);
        isExists.put(P, p);
    }

    static void delete(int P) {
        problems.remove(isExists.get(P));
    }

    static String recommend(int x) {
        String ret;

        switch (x) {
            case -1:
                ret = Integer.toString(problems.first().P);
                break;
            case 1:
                ret = Integer.toString(problems.last().P);
                break;
            default:
                ret = "";
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        problems = new TreeSet<>();
        isExists = new HashMap<>();

        StringTokenizer st;
        int N, P, L;

        // 초기화
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            add(P, L);
        }

        // 실제 쿼리문
        N = Integer.parseInt(br.readLine());
        String ret;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "recommend":
                    ret = recommend(Integer.parseInt(st.nextToken()));
                    sb.append(ret).append("\n");
                    break;
                case "add":
                    add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    break;
                case "solved":
                    delete(Integer.parseInt(st.nextToken()));
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
