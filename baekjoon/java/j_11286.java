/*
 * 절댓값 힙
 * 
 * 문제 설명
 * 절댓값으로 정렬하는 최소힙 만들기
 * 
 * 구현
 * comparator 재구현 - 절댓값으로 정렬하게 만듦.
 * 다만, 절댓값이 같으면 일반적인 값으로 정렬(마이너스가 더 작음)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class j_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int cmp = Integer.compare(Math.abs(o1), Math.abs(o2));
            if (cmp != 0) return cmp;
            else return Integer.compare(o1, o2);
        });
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int query;
        for (int i = 0; i < N; i++) {
            query = Integer.parseInt(br.readLine());

            if (query == 0) {
                if (pq.isEmpty()) sb.append(0).append("\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                pq.add(query);
            }
        }

        System.out.print(sb.toString());
    }
}
