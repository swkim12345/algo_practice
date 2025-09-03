/*
 * AC
 * R - 뒤집기, D - 버리기
 * deque를 이용해 구현하는 문제.
 * R을 이용해 뒤집을 때 값을 저장하고, 홀짝일 때 action이 바뀐다.
 * D 도 동일하게 영향, 대신 d의 사이즈에 따라 달라진다.
 * 입력값을 처리할 때 substring(1, string.length() - 1)
 * 
 * 오답노트
 * 1. poll할 때 방향을 잘 작성하자.
 * 2. 0일때 엣지케이스가 있다.
 * 3. 0일때 모두 RRR이면 괜춘함. 에러 아님.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class j_5430 {
    static Deque<Integer> deq = new ArrayDeque<>();

    static void solution(String query) {
        boolean isStraight = true;
        StringBuilder sb = new StringBuilder();

        // 쿼리 실행
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == 'R') {
                isStraight = !isStraight;
            } else {
                if (deq.size() == 0) { // 종료조건 : 배열에 아무것도 없을 때.
                    System.out.println("error"); 
                    return;
                }
                if (isStraight) {
                    deq.pollFirst();
                } else {
                    deq.pollLast();
                }
            }
        }

        // 출력
        sb.append("[");
        while(deq.size() > 0) {
            if (isStraight) {
                sb.append(deq.pollFirst());
            } else {
                sb.append(deq.pollLast());
            }
            if (deq.size() > 0) sb.append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ac, arrayString;
        String[] inputs;
        int tmp;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            deq.clear();
            ac = br.readLine().trim();
            tmp = Integer.parseInt(br.readLine());
            arrayString = br.readLine().trim();
            if (tmp != 0) {
                arrayString = arrayString.substring(1, arrayString.length() - 1);
                inputs = arrayString.split(",");
                for (int j = 0; j < inputs.length; j++) {
                    deq.addLast(Integer.parseInt(inputs[j]));
                }
            }
            solution(ac);
        }
    }
}
