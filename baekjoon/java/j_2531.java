/*
 * 회전 초밥
 * 
 * 
 * 초밥이 한줄로 늘어져 있고(다시 끝이 이어져 있음)
 * 여러개를 연속해서 먹을 때, 쿠폰을 포함해서 가장 많이 먹을 수 있는 경우의 수?
 * N : 접시수
 * d : 가지수
 * k : 연속 먹는 접시 수
 * c : 쿠폰 번호
 * 
 * 가짓수를 int 으로 관리
 * init 단계
 * 첫번째 시작부터 k개까지 초밥을 먹는다고 가정, c 포함 - 현재 개수가 몇개인 지 파악
 * solution 단계
 * 포인터를 하나씩 이동하면서 가짓수 int 값을 변경
 * 가짓수 변경 시 0 -> 1은 beforeAnswer +1, 1->0은 -1
 * 모든 포인터가 한바퀴를 돌았을 때 max 값 출력
 * 
 * 완전탐색 + 투포인터 문제.
 * 
 * 시간 복잡도 - O(d ** 2)
 * 
 * 오답노트
 * 1. 1부터 시작하는 스시 가짓수
 * 2. +1을 잘못된 곳에 더함
 * 3. 굳이 N - k로 해서 처음의 값을 계산하지 않음.
 * 
 * 참고한 답
 * 해시맵을 이용해 중복된 것을 제외함.
 */

import java.io.*;
import java.util.*;

public class j_2531 {
    static int[] eat; //초밥의 현재 먹은 개수를 표현함, d + 1 초밥의 가짓수
    static int[] sushi; //레일 위 초밥, N 과 연관

    static int init(int k) {
        int answer = 0;

        for (int i = 0; i < k; i++) {
            eat[sushi[i]] += 1;
            // 방금 막 늘었을 때
            if (eat[sushi[i]] == 1) {
                answer++;
            }
        }
        return answer;
    }

    static void solution(int k, int N, int beforeAnswer) {
        int start = 0, end = k - 1, answer = beforeAnswer;

        // 처음 초밥 제외, 마지막 + 1 초밥 + 1
        for (int i = 0; i < N; i++) {

            eat[sushi[start]] -= 1;
            if (eat[sushi[start]] == 0) {
                beforeAnswer -= 1;
            }
            start = (start + 1) % N;
            end = (end + 1) % N;
            eat[sushi[end]] += 1;
            if (eat[sushi[end]] == 1) {
                beforeAnswer += 1;
            }

            answer = Math.max(answer, beforeAnswer);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int  beforeAnswer = 0, N, d, k, c;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        eat = new int[d + 1];
        for (int i = 1; i <= d; i++) eat[i] = 0;

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        beforeAnswer = init(k);
        eat[c]++;
        if (eat[c] == 1) {
            beforeAnswer++;
        }
        solution(k, N, beforeAnswer);
    }
}
