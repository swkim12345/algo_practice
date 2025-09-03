/*
 * 회전 초밥
 * 2531 문제와 동일하지만, 시간복잡도를 고려해 더 짧게 만들어야 하는 문제
 * 다만, 내가 푼 풀이도 짧아서...
 * 
 * 초밥을 연속해서 먹을 때 할인행사, 할인행사 참여한다고 했으니 무조건 연속해서 먹는다고 가정하자.
 * (사실은 충분조건이라 역이 성립된다고 할 순 없다. 다만, 맥락상 필요충분조건이라고 간주해 문제를 풀고 있으니 그렇게 풀자.)
 * 
 * 연속된 초밥을 먹을 때 가짓수의 최대값을 구하는 문제임.
 * 다만, 쿠폰이라는 게 있어서, 초기화 단계 다음에 쿠폰까지 고려해서 솔루션 단계에 들어가야 함.
 * N, d, k, c
 * 알고리즘 예측
 * 완전 탐색, 투포인터, (해시맵)
 * 초기화
 * 1. 회전초밥 레일, 가짓수를 받아 초기화(0으로) - main
 * 2. N만큼 회전초밥 레일위의 초밥 받아 초기화 - main
 * 3. answer에 투포인터를 이동시키며 추가
 * 3-1. 추가시 0 -> 1이면 answer+=1, 1->0이면 answer-= 1 (이 때, 투 포인터를 이동시키는 단계를 조심하자.)
 * 중간
 * 1. 쿠폰 고려해서 추가.
 * 솔루션
 * 1. 1번 포인터(앞 포인터)에 있는 초밥을 -1개 하자.(초밥의 종류에 맞춰서)
 * 2. 포인터를 +1 하자(두 포인터 모두)
 * 3. 2번 포인터에 있는 초밥을 +1 하자.
 * 4. 1 ~ 3번까지의 단계를 총 N번 반복해 모든 초밥 경우의 수에 대해 검증.
 * 
 * 오답노트
 * beforeAnswer같은 것을 쓸 때, beforeAnswer를 바탕으로 조작하고, answer로 덮어씌우면 안 된다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class j_15961 {
    static int[] sushi; // 스시 종류
    static int[] rails; // 레일 위 스시

    static int solution(int N, int k, int answer) {
        int beforeAnswer = answer;
        int firstPointer = 0, secondPointer = k - 1;

        for (int i = 0; i < N; i++) {
            // 1 ~ 3 까지 하나의 스텝, 스텝이 종료되면 answer 업데이트 시도
            // 1. 첫번째 포인터의 초밥 개수 -1, 0이라면 beforeAnswer - 1
            sushi[rails[firstPointer]] -= 1;
            if (sushi[rails[firstPointer]] == 0) {
                beforeAnswer -= 1;
            }
            // 2. 이동 
            firstPointer = (firstPointer + 1) % N;
            secondPointer = (secondPointer + 1) % N;
            // 3. 두번째 포인터의 초밥 개수 + 1, 1이라면 beforeAnswer + 1
            sushi[rails[secondPointer]] += 1;
            if (sushi[rails[secondPointer]] == 1) {
                beforeAnswer += 1;
            }
            // 스텝 종료 후 업데이트 시도.
            answer = Math.max(answer, beforeAnswer);
        }

        return answer;
    }

    static int init(int N, int k, int c) {
        int answer = 0;

        for (int i = 0; i < k; i++) {
            sushi[rails[i]] += 1;
            if (sushi[rails[i]] == 1) {
                answer += 1;
            }
        }
        // 쿠폰도 추가
        sushi[c] += 1;
        if (sushi[c] == 1) {
            answer += 1;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, d, k, c, answer;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[d + 1]; // 1부터 d까지 초밥의 종류를 나타냄.
        rails = new int[N];
        Arrays.fill(sushi, 0);

        for (int i = 0; i < N; i++) {
            rails[i] = Integer.parseInt(br.readLine());
        }

        answer = init(N, k, c);
        
        answer = solution(N, k, answer);
        System.out.println(answer); //출력이 단 한줄로 끝나기 때문에 바로 출력 후 종료 처리.
    }
}
