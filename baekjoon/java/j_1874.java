/*
 * 스택 수열
 * 
 * 문제 해설
 * 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다.
 * 
 * 문제 풀이
 * 정수 관리하는 인덱스 idx (증가밖에 하지 않음. push시 증가)
 * 1. stack.isEmpty() - true => peek을 0으로 취급
 * 2. stack.peek() - x
 * 3. x < target - idx push, + 출력, idx++, 2번으로 돌아가기
 * 4. x == target - pop, - 출력 ,2번 돌아가기
 * 5. x > target - 만들 수 없음. NO 출력 후 얼리 리턴
 * 6. 모든 문자열 스캔 완료 후 StringBuilder에 저장된 문자 출력
 */

import java.io.*;
import java.util.*;

public class j_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();
        int idx = 0, peek, target;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            target = Integer.parseInt(br.readLine());

            while (true) {
                if (stack.isEmpty()) peek = 0;
                else peek = stack.peekLast();

                if (peek < target) { // idx++을 하면서 처리
                    sb.append("+").append("\n");
                    stack.addLast(++idx);
                } else if (peek == target) { // 종료조건 - 동일할 경우
                    sb.append("-").append("\n");
                    stack.pollLast();
                    break;
                } else { // 불가능한 조건 - peek > target, 이럴 경우, stack 안에 target이 있다는 뜻임.
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.print(sb.toString());
    }
}
