/*
 * 도서관
 * 최소 걸음수? - 그리디? dp?
 * 
 * M만큼 들고다닐 수 있음. 책은 다 0에 있고, 무조건 0에 돌아올 필요는 없음(책을 중간에 놓을 수 있어요)\
 * 
 * step by step
 * 1. 가장 먼 곳 - 최소 왕복을 한번 해야 한다.
 * 왕복할 때, M-1개만큼 추가로 들고 갈 수 있다.
 * 들고 갈 때, 같은 방향의 가장 먼 곳을 지운다고 가정하자.
 * 증명하기 위해 가장 먼 곳이 아닌 곳을 지운다고 가정할 때, delta, a, b 순으로 있다 가정하자.
 * delta에 이동할 때, a가 아닌 b에 전달한다고 가정하면, 이후 a에 추가적으로 배달해야 한다.
 * 그럴 경우 이동거리가 2 * a > 2 * b이므로, 아니게 된다.
 * 따라서, 가장 먼 곳에 이동하게 되면, 그 다음 먼곳의 책을 들고 이동하는 것이 성립한다.
 * 
 * 완전탐색, dp, 그리디, 그래프(dfs, bfs, 다익스트라, 유니온파인드), 수학(조합론)
 * 1. 그리디 - 같은 방향 중 가장 먼 곳을 M개만큼 묶어서 이동한다.
 * 
 * 최소 거리 - 최소 100의 거리를 무조건 이동(39, 11 이동 처리)
 * 2개를 들고 다닐 수 있음. 따라서, 남은 것은 -29, -28, -6이 남음.
 * 
 * -39 -37 -29 -28 -6 2 11
 * 뭘 잘못이해했나?
 * 0에서 시작, 0에 모든 책이 있음, 한걸음당 좌표 한칸, 최소걸음수 구하기, 최대 M권 들 수 있음. 0으로 돌아올 필요 없음.
 * 마지막에 39에 도달한다고 가정하면 왕복할 필요가 없음.
 * 따라서, 이 문제는 왕복을 하지 "않을" 마지막 이동을 구하는 문제임.
 * 기본적으론 그리디로 움직이되, 가장 거리가 먼 친구를 한번에 가는 문제임.
 * 그리디 답 : 가장 먼 곳을 기준으로 M개를 마지막 단번으로 움직이자. 이외의 경우 가장 먼 곳부터 M개씩 지워나가면서 이동한다.
 * 
 * 증명
 * 1. 가장 먼 곳이 아닌 곳을 기준으로 M개를 마지막 배달한다 가정하자.
 * a> b> c라고 가정하고, 2개를 배달한다 가정할 때, ab 가 아닌, bc를 전달할 때 최적의 답이 나온다고 하자.
 * 각각 a + c * 2 , a * 2 + b -> c *2 - b a
 * c * 2 ???? a + b
 * a > c, b > c 이므로
 * a + c * 2 < a * 2 + b이다.
 * 따라서, 이 가정은 틀린 가정이다. 따라, 이의 역인 가장 먼곳을 배달할 때 단번에 가는 것이 정답이다.
 * 
 * 초기화
 * 입력 처리, 두개의 우선순위 큐를 사용한다. 하나는 최소큐(-), 하나는 최대큐(+)
 * 자바의 경우 최소힙이므로, 최대힙을 integer에서 구현하려면 초기화때 Collections.reverseOrder()를 이용한다.
 * 솔루션 단계
 * 1. 절대값으로 가장 먼 곳을 고르고, M개를 먼저 지운다. - 가장 먼 곳 거리
 * 2. 절대값으로 먼 곳을 먼저 선택한 다음, 이를 M개씩 지운다.(만약 size가 0이라면 바로 이 큐 사용 금지(종료조건)) (가장 먼 곳 거리) * 2
 * 3. 절대값이 바뀌면 바로 나머지 큐만 사용해 이동처리한다.
 * 
 * 책의 위치는 0이 없음. 따라서 고민할 필요가 없어요
 * 
 * 오답노트
 * 자바힙은 최소힙임.
 * 힙이 0일수있음.
 * --M > 0을 while에 사용했을 때, 실제로 true라서 돌아가는 횟수는 M - 1 회이다.
 * M-- > 0으로 while에 사용하자.
 */

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class j_1461 {
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder()), minPQ = new PriorityQueue<>();
    
    // M만큼 지워주는 step 함수, 절대값으로 최대값을 리턴해줌.
    static int step(PriorityQueue<Integer> pq, int M) {
        // 얼리리턴 - size 체크.
        if (pq.size() == 0) return 0;
        int answer = Math.abs(pq.peek());

        while (pq.size() > 0 && M-- > 0) {
            pq.poll();
        }
        return answer;
    }

    static void solution(int M) {
        int answer = 0;
        if (maxPQ.size() > 0 && minPQ.size() > 0) {
            // 처음 단방향 이동, 절대값이 더 큰 친구를 단방향으로 이동.
            if (Math.abs(maxPQ.peek()) >= Math.abs(minPQ.peek())) {
                answer += step(maxPQ, M);
            } else {
                answer += step(minPQ, M);
            }
        } else { // +만 주어질 경우.
            if (maxPQ.size() == 0) {
                answer += step(minPQ, M);
            } else {
                answer += step(maxPQ, M);
            }
        }

        while (maxPQ.size() > 0 || minPQ.size() > 0) {
            answer += step(maxPQ, M) * 2;
            answer += step(minPQ, M) * 2;
        }
        System.out.println(answer);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, M, tmp;
        
        // 초기화 단계
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            if (tmp < 0) {
                minPQ.add(tmp);
            } else {
                maxPQ.add(tmp);
            }
        }
        // 솔루션 단계
        solution(M);
    }
}
