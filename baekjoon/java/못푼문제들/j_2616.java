package 못푼문제들;
/*
 * 소형기관차
 * 
 * 소형기관차 : 3대, 각각 객체를 최대 몇대 옮길 수 있음, 연속된 객체 끌 수 있음(1번부터 번호 시작)
 * 목표 : 최대한 많은 손님 운송, 소형기관차 사용시 최대 운송 손님 수 구함.
 * 
 * 객차는 중복할 수도 없고, 손님은 이동시킬 수도 없음. - 객차 중복에 대해 고려할 필요가 없음.
 * 따라서, 누적합을 이용해 합을 구한 다음, 한번 더 스캔해 값을 저장
 * 
 * 현재 cnt개수만큼 갈 수 있다. -> prefixSum(x) - prefixSum(x - cnt + 1) 만큼이 한 기관차가 가지고 갈 수 있는 수.
 * 
 * 
 * 초기화
 * 입력값을 받아가면서 누적합을 만들어 prefixSum에 넣는다.
 * 솔루션
 * 1. 0부터 순회하며 x + cnt 값을 
 * 
 * 막히는 부분
 * 3개를 구한다는 점이 dp와 어울리지 않음. dp는 함수를 이용해 구현하기는 쉽지만, 몇개를 초이스하는 데에는 한계가 있음.
 * dp? -> 현재 x꺼를 끝으로 하는 기관차를 선택 / 비선택
 * 3개를 구할 때 완탐이면 O(N ** 3), 시간 초과임.
 * 
 * 오답노트
 * 객차끼리 공유가 불가능하나, 현재 알고리즘 상에선 poll을 이용해 공유를 허용하고 있음.
 * for (int i = 0; i <= N - cnt; i++) { //1번부터 이미 누적합임.
            tmp = prefixSum[i + cnt] - prefixSum[i];
            pq.add(tmp);
    }
    answer = pq.poll() + pq.poll() + pq.poll();
    자바는 최소힙최소힙최소힙
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class j_2616 {
    static int[] prefixSum;
    static int[] dp;

    static void solution(int N, int cnt) {
        for (int i = 0; i + cnt <= N; i++) {
            dp[i + cnt] = dp[i + 1] + prefixSum[i + cnt] - prefixSum[i];
            dp[i + cnt] = Math.max(dp[i - 1 + cnt], dp[i + cnt]);
        }

        System.out.println(dp[N]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, cnt, tmp; //cnt는 소형 기관차가 끌 수 있는 최대 손님 수임.

        N = Integer.parseInt(br.readLine());
        prefixSum = new int[N + 1]; //prefix sum을 위해 0은 비워놓음. 1부터 더해주면 됨.
        dp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + tmp;
            System.out.printf("%d ", prefixSum[i]);
        }
        cnt = Integer.parseInt(br.readLine());
        solution(N, cnt);
    }
}
