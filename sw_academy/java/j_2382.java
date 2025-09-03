package sw_academy.java;

/*
 * 미생물 격리
 * 
 * 문제 요약
 * 바깥쪽 가장자리 부분에 약품이 발라져 있음.
 * 1. 이동방향에 있는 셀 한칸 이동
 * 2-1. 이동 후 약품 셀 -> 절반 죽고(버림), 이동방향이 반대로 바뀜
 * 2-2. 이동 후 겹침 -> 합쳐짐
 * 2-3. 1, 2번 조건은 동시에 발생하지 않는다...! 가장자리에는 미생물 군집이 배치되어 있지 않고, 같은 셀에
 * 동일한 군집이 처음에는 위치하지 않기 때문이다.
 * 
 * 문제 풀이
 * 1. 처음에 모든 셀을 이동하면서 임시로 PQ에 저장한다. PQ 정렬 순은 x, y 좌표순
 * 2. 처음 pop 한 값을 저장한다. (이전에 pq가 다 비워져 있으면 얼리 리턴)
 * 3. 이전 저장한 값이 만약 border라면 -> 절반, 방향 반대로 해서 다시 넣고 pop한 값 저장
 * 4. 이전 저장한 값과 peek한 값의 좌표가 동일하다면 -> 머지, 머지할 때 최대값보다 크다면 dir 갱신
 * 5. 동일하지 않다면 -> 이전 값 삽입, 그리고 peek한 값으로 업데이트 후 pop
 * 
 * 주의
 * 왜 경계값에서 충돌이 나지? 충돌 나는 케이스는 같은 방향은 안 나고, 다른 방향에서 충돌이 나게 되는 데,
 * 경계값에서 시작하는 값은 없으므로 겹쳐지는 케이스는 1,1 이상 N-1, N-1에 잇지 않나?
 */

import java.io.*;
import java.util.*;

public class j_2382 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    
    static int N, M, K;

    static class Bacteria implements Comparable<Bacteria> {
        int x, y, amount, dir; //dir는 -1을 해서 저장해야함.

        Bacteria(int y, int x, int amount, int dir) { //입력값 순서대로 작성함.
            this.x = x;
            this.y = y;
            this.amount = amount;
            this.dir = dir - 1;
        }

        @Override
        public int compareTo(Bacteria b) {
            int cmp = Integer.compare(this.x, b.x);
            if (cmp != 0) return cmp;
            else return Integer.compare(this.y, b.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        
        for (int testCase = 1; testCase <= T; testCase++) {
            st  = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            Bacteria bacteria;
            List<Bacteria> list = new ArrayList<>();
            int x, y, amount, dir;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
            
                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                amount = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken());
                bacteria = new Bacteria(y, x, amount, dir);
                list.add(bacteria);
            }

            System.out.printf("#%d %d\n", testCase, solution(list));
        }
    }

    static boolean isBorder(int x, int y) {
        return x == 0 || y == 0 || x == N - 1 || y == N - 1;
    }

    static int solution(List<Bacteria> list) {
        PriorityQueue<Bacteria> before = new PriorityQueue<>();

        for (int step = 0; step < M; step++) {
            // 1단계 - 이동
            for (Bacteria b : list) {
                // 검증 없이 더해도 무방
                b.x += dx[b.dir];
                b.y += dy[b.dir];
                before.add(b);
            }
            // list 비움
            list.clear();
            
            mergeStep(before, list);
            before.clear();
        }
        
        // 리턴값 - 전체 박테리아의 수 토탈해서 리턴
        int ans = 0;
        for (Bacteria b : list) {
            ans += b.amount;
        }
        return ans;
    }

    static void mergeStep(PriorityQueue<Bacteria> before, List<Bacteria> list) {
        // 2단계 - 병합 처리 혹은 경계 처리
        // 얼리리턴 - before 없음
        if (before.isEmpty()) return ;

        Bacteria bef = before.poll();
        int mx = bef.amount;
        int dir = bef.dir;
        
        while (!before.isEmpty()) {
            Bacteria target = before.peek();

            // 만약 befBacteria가 경계값이면 - 방향 반대로 해서 다시 넣기
            if (isBorder(bef.x, bef.y)) {
                bef.dir = bef.dir ^ 1;
                bef.amount /= 2;
                if (bef.amount != 0) list.add(bef);

                bef = target;
                mx = bef.amount;
                dir = bef.dir;
            }
            // 같은값이 아니라면
            else if (!(bef.x == target.x && bef.y == target.y)) {
                bef.dir = dir;
                list.add(bef);

                bef = target;
                mx = bef.amount;
                dir = bef.dir;
            } else { // 같은 값이라면 - 최대값 검증해서 머지
                bef.amount += target.amount;
                if (mx < target.amount) { // 만약 타깃이 최대값이라면 -> 갱신.
                    mx = target.amount;
                    dir = target.dir;
                }
            }

            before.poll();
        }
        // 마지막 - 남은 값 넣어줌
        // 만약 befBacteria가 경계값이면 - 나누고, 0이면 버림.
        if (isBorder(bef.x, bef.y)) {
            bef.dir = bef.dir ^ 1;
            bef.amount /= 2;
            if (bef.amount == 0) return;
        } else {
            bef.dir = dir;
        }
        list.add(bef);
    }
}