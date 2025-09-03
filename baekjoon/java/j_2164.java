/*
 * 카드
 * 
 * 문제 요약
 * 위 - 1 ~ N 까지 순서대로 카드
 * 1. 위에 있는 카드 버리기
 * 2. 위에 있는 카드 밑으로 옮기기
 * 
 * 문제 풀이
 * queue로 푸는 문제
 */

import java.io.*;
import java.util.*;

public class j_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            queue.addLast(i);
        }

        // 시뮬레이션
        while (queue.size() != 1) {
            queue.pollFirst();
            queue.addLast(queue.pollFirst());
        }

        System.out.println(queue.poll());
    }
}