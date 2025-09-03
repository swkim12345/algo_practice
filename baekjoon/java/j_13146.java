/*
 * 같은 수로 만들기 2
 * 
 * 값을 하나로 만드는 것
 * 만들 때 어떻게 만들 것인가?
 * 막히는 부분
 * 단조 증가 - 단조 감소 - 단조 증가 - 단조 감소
 * 이런 패턴일 때, 단조 감소 이 부분을 단조증가로 바꿔야 할 텐데, 3개씩 묶어서 해야한다는 문제가 있음.
 * 이를 어떻게 처리해야하는가?에 대해 잘 모르겠다.
 * 오답노트
 * 이것의 목표는 모든 값이 단조증가하게 만들고, 처음값과 마지막 값을 빼서 구하는 것임.
 * 1. 모든 값을 삽입 - stack 기준 증가할 때
 * 2. 증가 -> 감소 (그냥 삽입)
 * 3. 감소 -> 증가 => 증가 -> 감소할 때(혹은 size가 0일때)까지 pop -> 최고 값 - 감소 값 cnt 에 저장
 * 3-1. 증가한 값 삽입 => 1로 그냥 돌아감.
 * 
 * 목표 -> 스택에 들어가있는 수열을 단조감소로 만드는 것! 단조 감소된 스택은 마지막에 계산만 해주면 됨
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class j_13146 {
    static Deque<Long> stack = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long cnt = 0, N, tmp;

        N = Long.parseLong(br.readLine());
        tmp = Long.parseLong(br.readLine());
        stack.addLast(tmp);
        
        for (int i = 1; i < N; i++) {
            tmp = Long.parseLong(br.readLine());
            long mn = Long.MAX_VALUE;

            while (stack.size() > 0 && stack.peekLast() < tmp) {
                mn = Math.min(mn, stack.peekLast());
                stack.pollLast();
            }
            if (mn != Long.MAX_VALUE) {
                cnt += tmp - mn;
            }
            stack.addLast(tmp);
        }

        if (stack.size() >= 2) {
            cnt += stack.peekFirst() - stack.peekLast();
        }
        System.out.println(cnt);
    }
}