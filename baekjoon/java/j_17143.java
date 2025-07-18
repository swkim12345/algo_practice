/*
 * 낚시왕
 * 
 * 시뮬레이션 문제. 문제를 풀 때 시뮬레이션대로 진행하되, 추가적으로 발생되는 조건을 잘 해결해야 한다.
 * 
 * 1. 낚시왕 오른쪽으로 한칸 이동
 * 2. 땅에 가장 가까운 상어 잡기 - y좌표 기준 작은 상어를 잡는다.
 * 3. 상어 모두 움직이기
 * 3-1. 상어 중 겹치는 것은 무게가 큰 얘가 나머지 얘를 잡아먹음.
 * 이 과정 모두가 1초에 한번 이뤄짐.
 * x 좌표가 총 6이라면 6번 반복됨.
 * 
 * 막히는 부분
 * 상어 이동 시 넘어가는 부분에서 막힘.
 * -> 0 미만 -> 방향 * -1, dir 0,1 / 2,3 플립
 * -> x, y 초과 -> 2 *(x len or y len) + 방향 * -1, dir 0,1 / 2, 3
 * 
 * 각각 y, x, 속력, 이동방향(위, 아래, 오른쪽, 왼쪽), 크기
 * 
 * 오답노트
 * x, y 인풋이 1부터 시작임. idx도 동일함.
 * break가 삽입되어야 하는 부분에 삽입되지 않음. 종료조건 체크 실패
 * 막히는 부분에 작성한 식이 잘못됨.
 * 방향을 잘못 삽입했음.
 * 
 * 크기 3, 위치 3 -> 크기 - 1 - (위치 - (크기 - 1)) == 2 * 크기 - 2 - 위치
 * 오름차순 정렬이 아닌 내림차순 정렬을 하고 있었음.
 * 상어가 서로 잡아먹었을 때, 커지는 것이 아님.
 * 
 * 참고한 답
 * https://www.acmicpc.net/source/96376462
 * 1. 우선순위 큐를 사용하지 않음. - 다만 보드를 사용하되, 움직이기 전 보드와 움직인 후 보드를 따로 둠.
 * 2. 1부터 시작하게 처리함. 나의 경우 모든 array 를 사용하는 것이 0부터 시작하게 설정함. 이 문제의 경우 1부터 시작하는 것이 더 나았음.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class j_17143 {

    // y, x 좌표 순으로 되어 있음.
    final static int[][] mv = {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1},
    };
    static PriorityQueue<Shark> beforeRun = new PriorityQueue<>(); // 상어가 움직이기 전 나타내는 함수
    static PriorityQueue<Shark> afterRun = new PriorityQueue<>(); // 상어가 움직인 이후 나타내는 우선순위 큐

    static class Shark implements Comparable<Shark> {
        public int x, y, s, d, z;

        Shark(int y, int x, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark s) {
            if (this.x != s.x) return this.x - s.x;
            return this.y - s.y;
        }
    }

    static Shark mv(Shark sk, int R, int C) {
        // 1. 상어의 속력만큼 더한 값을 저장
        // 2. 그냥 이동시켜(x, y).... 만약 초과하면 direction을 바꾼 다음, 바꾼 방향에서 
        // 3. 종료조건은 남은 이동 거리 + x or y 이면 더하고 종료
        sk.x += mv[sk.d][1] * sk.s;
        sk.y += mv[sk.d][0] * sk.s;
        while (true) {
            //종료조건
            if (0 <= sk.x && sk.x < C && 0 <= sk.y && sk.y < R) {
                return sk;
            } else {
                if (sk.x < 0) {
                    sk.x = -1 * sk.x;
                    sk.d = 2;
                } else if (sk.x >= C) {
                    sk.x = 2 * C - sk.x - 2;
                    sk.d = 3;
                } else if (sk.y < 0) {
                    sk.y = -1 * sk.y;
                    sk.d = 1;
                }  else{
                    sk.y = 2 * R - sk.y - 2;
                    sk.d = 0;
                }
            }
        }
    }

    // 상어끼리 잡아먹을 때, 크기가 큰 상어만 살아남음.
    static Shark mergeShark(ArrayList<Shark> samePosSharks) {
        int maxIdx = 0;
        if (samePosSharks.size() == 1) return samePosSharks.get(maxIdx);
        for (int i = 1; i < samePosSharks.size(); i++) {
            if (samePosSharks.get(maxIdx).z < samePosSharks.get(i).z) maxIdx = i;
        }
        return samePosSharks.get(maxIdx);
    }

    static void solution(int R, int C) {
        int fisher = -1, answer = 0;
        Shark tmp;
        boolean isCatch = false;
        ArrayList<Shark> samePositionShark = new ArrayList<>();
        while (++fisher < C) {
            isCatch = false;
            // 1. 낚시왕 이동
            // 2. 상어 이동 - 이동시 out of range 조심
            // 2-1. 상어 이동 중 낚시왕과 동일한 x 좌표가 처음 나오면 그게 잡히는 상어임.
            while (beforeRun.size() > 0) {
                tmp = beforeRun.poll();
                if (isCatch == false && tmp.x == fisher) {
                    isCatch = true;
                    answer += tmp.z;
                    continue;
                }
                afterRun.add(mv(tmp, R, C));
            }
            // 3. afterRun에서 pop 하면서 동일 좌표 상어 확인
            // 3-1. 동일 좌표 상어라면 합침
            while(afterRun.size() > 0) {
                samePositionShark.add(afterRun.poll());
                while (afterRun.size() > 0) {
                    tmp = afterRun.peek();
                    if (samePositionShark.get(0).x == tmp.x && samePositionShark.get(0).y == tmp.y) {
                        samePositionShark.add(afterRun.poll());
                    } else {
                        // 종료 조건
                        beforeRun.add(mergeShark(samePositionShark));
                        samePositionShark = new ArrayList<>();
                        break;
                    }
                }

                // 남은 떨거지 처리
                if (samePositionShark.size() > 0) {
                    beforeRun.add(mergeShark(samePositionShark));
                    samePositionShark = new ArrayList<>();
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int R, C, M;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Shark sk = new Shark(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken())
            );
            beforeRun.add(sk);
        }

        solution(R, C);
    }
}
