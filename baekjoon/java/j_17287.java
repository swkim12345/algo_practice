/*
 * The Deeper, The Better
 * 
 * 여기서 숫자는 단순히 대괄호, 중괄호, 소괄호가 아니라, 점수를 집계할 수 있는 곳이다. 숫자 자체는 의미가 없다.
 * 
 * 입력
 * 대괄호, 중괄호, 소활호, 숫자 - 문자열
 * 
 * 계산
 * 모두 올바른 괄호 문자열 보장 - 괄호 열고 닫힌 것이 올바름, 빈문자열도 올바름
 * [] - 3점
 * {} - 2점
 * () - 1점
 * 문자열에 "포함"되면 점수를 획득. 점수는 누적으로 획득하게 됨.
 * 
 * 출력
 * 가장 높은 점수를 출력함. (같은 숫자여도 위치가 다르면 점수를 따로 계산함.)
 * 
 * 초기화
 * 문자열을 입력받음
 * 풀이과정
 * 이 문제는 후위표현식을 만드는 문제와 동일하다. - 아님.
 * 예제 입력 1과 (1)[3{(5)}((4))] 일 때
 * 1()35(){}4()()[]
 * 1()35(){}4()()[]로 동일하게 나옴. 후위표현식과 다르다.
 * 여기서 점수는 자신 앞의 여는 괄호의 개수와 동일하다.
 * 풀이과정
 * 열린 괄호마다 cnt를 증가. 숫자가 나올 때 마다 answer를 업데이트한다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class j_17287 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int cnt = 0, answer = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '[': 
                    cnt += 1;
                case '{': 
                    cnt += 1;
                case '(': {
                    cnt += 1;
                    break;
                }
                case ']': 
                    cnt -= 1;
                case '}': 
                    cnt -= 1;
                case ')': {
                    cnt -= 1;
                    break;
                }
                default: {
                    answer = Math.max(answer, cnt);
                }
            }
        }
        System.out.println(answer);
    }
}
