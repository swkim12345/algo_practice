/*
 * 스카이라인 쉬운거
 * 
 * 건물의 윤곽 : 스카이라인. 몇채인지 추측하는 프로그램
 * 예시를 보면, 같은 높이 && 연속되어 있으면 한 건물이라고 취급함.
 * 조건이 크게 3가지 경우가 있음(바뀔 때에만 작성하는 것이므로)
 * 1. 이전 y좌표보다 더 높을 경우 - stack에 push
 * 2. 이전 y 좌표보다 더 낮을 경우 - stack에 pop을 하되, 같은 높이가 나올 때 or 더 낮은 높이까지 pop. pop이 되는 개수마다 +1
 * 2-1. 0이 올 경우 - 2번과 동일한 케이스
 * 3. 입력값이 더이상 없을 경우 -> 0이 온 것과 동일하게 취급.
 * 왜 이게 되는 것인가? - 스카이라인의 높이가 바뀔 때, 같은 높이 + 그 사이의 높이가 더 높았다면 하나의 건물로 칭함. 
 * 높이가 더 낮을 때에만 건물로취급
 * 
 * 초기화
 * N
 * x y 입력
 * x는 중요하지 않음.(어짜피 다르니깐)
 * y값을 조건에 따라 stack에 push 혹은 stack을 pop 하면서 카운팅
 * 
 * 오답노트
 * 마지막에 stack 에 남아 있는 값도 건물임. 값이 동일하지 않는다면, 삽입을 해야함.
 * 처음에 0을 삽입해서 땅을 표시함.
 */

import java.io.*;
import java.util.*;

public class j_1863 {
    static Deque<Integer> stack = new ArrayDeque<Integer>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        @SuppressWarnings("unused")
        int N, x, y, cnt = 0;

        N = Integer.parseInt(br.readLine());
        stack.addLast(0);

        //solution과 통합해서 처리
        // 입력값을 받아가면서 조건에 맞춰 stack pop, push를 처리함.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if (stack.peekLast() < y) {
                stack.addLast(y);
            } else if (stack.peekLast() == y) {
                // skip, 같은 경우는 들어오지 않으나, 표시.
            } else {
                while (stack.peekLast() > y) {
                    cnt += 1;
                    stack.pollLast();
                }
                // pop이 끝났을 때, 건물의 높이가 자기 자신과 다를 경우 -> push (새로운 건물이라는 뜻임.)
                if (stack.peekLast() != y) {
                    stack.addLast(y);
                }
            }
        }
        // 마지막에 남아있는 것들을 빼가며 땅까지 빼기
        while (stack.size() > 0 && stack.peekLast() != 0) {
            cnt += 1;
            stack.pollLast();
        }
        System.out.println(cnt);
    }
}
