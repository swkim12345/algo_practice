package sw_academy.java;
/*
 * 차량 정비소
 * 
 * 문제 요약
 * 접수창구, 정비창구가 있음, 창구마다는 각각 번호가 있고, 처리되는 데 걸리는 시간이 존재함.
 * 고객은 도착하는 데로 1번부터 고객번호를 부여받음.
 * 접수 창구의 우선순위는
 * 1. 여러 고객이 있을 경우 고객번호가 낮은 순서대로
 * 2. 빈 창구가 여러곳일 경우 창구번호가 낮은 곳으로
 * 
 * 정비 창구의 우선순위는
 * 1. 먼저 기다리는 고객이 우선
 * 2. 두 고객이 동시에 오면 이용했던 접수 창구 번호가 작은 고객이 우선됨.
 * 3. 빈 창구가 여러곳이면 정비창구번호가 작은 곳으로 이동.
 * 주어진 접수창구번호와 정비창구번호가 동일하게 사용한 고객들의 고객번호의 합을 리턴
 */

import java.io.*;
import java.util.*;

public class j_2477 {
    static int N, M, K, A, B, ans;
    static class Customer {
        int id, end, count1, count2;

        public Customer(int id, int end) {
            this.id = id;
            this.end = end;
            this.count1 = 0;
            this.count2 = 0;
        }
    }
    static int[] count1Time;
    static int[] count2Time;
    static Customer[] count1Used;
    static int[] count2Used;

    static Queue<Customer> wait;
    static Queue<Customer> btwWait; // 접수 - 정비

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            count1Time = new int[N + 1]; count2Time = new int[M + 1];
            count1Used = new Customer[N + 1]; count2Used = new int[M + 1];
            ans = 0;

            wait = new PriorityQueue<>((o1, o2) -> {
                int cmp = Integer.compare(o1.end, o2.end);
                if (cmp != 0) return cmp;
                return Integer.compare(o1.id, o2.id);
            });
            btwWait = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                count1Time[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                count2Time[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                wait.add(new Customer(i, Integer.parseInt(st.nextToken())));
            }

            step();
            if (ans == 0) ans = -1;
            System.out.printf("#%d %d\n", t, ans);
        }
    }

    static void step() {
        int time = 0, cnt = 0;
        List<Integer> count1Empty = new ArrayList<>();
        List<Integer> count2Empty = new ArrayList<>();

        while (cnt < K) { 
            count1Empty.clear(); count2Empty.clear();

            // 1. 창구 두개 비우면서 들어갈 수 있는 곳 찾기
            for (int i = 1; i <= N; i++) {
                if (count1Used[i] != null && count1Used[i].end == time) {
                    btwWait.add(count1Used[i]);
                    count1Used[i] = null;
                }
                if (count1Used[i] == null) {
                    count1Empty.add(i);
                }
            }
            for (int i = 1; i <= M; i++) {
                if (count2Used[i] == time) {
                    count2Used[i] = 0;
                }
                if (count2Used[i] == 0) {
                    count2Empty.add(i);
                }
            }

            // 2. 첫번째 웨이팅 비우면서 추가 및 두번째 웨이팅으로 이동처리
            for (int idx : count1Empty) {
                if (wait.isEmpty() || wait.peek().end > time) break;
                Customer cs = wait.poll();
                cs.count1 = idx;
                cs.end = time + count1Time[idx];
                
                count1Used[idx] = cs;
            }

            // 3. 두번째 웨이팅 top을 비교하면서 삽입하기
            for (int idx : count2Empty) {
                if (btwWait.isEmpty() || btwWait.peek().end > time) break;
                Customer cs = btwWait.poll();
                cs.count2 = idx;
                
                count2Used[idx] = time + count2Time[idx];
                cnt++;
                
                // 만약 사용한 순서가 A, B라면 ans에 고객 번호 추가
                if (cs.count1 == A && cs.count2 == B) {
                    ans += cs.id;
                }
            }
            time++;
        }
    }
}
