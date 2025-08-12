/*
 * 오큰수
 * 
 * 문제 요약
 * 오른쪽에 있으면서, Ai보다 큰 수중에 가장 왼쪽에 있는 수
 * 
 * 문제 풀이
 * 1. 단조 감소
 * 단조 감소의 경우 단조 감소하는 수를 Stack으로 저장
 * 
 * 2. 단조 감소가 아닐 경우
 * stack top에 있는 수와 비교해
 * top >= target -> stack에 삽입
 * top < target -> pop, 인덱스에 저장
 * 
 * 자료구조
 * Deque<Node>
 * Node - idx, value
 * int[] arr - 값 저장, 1번 인덱스부터 시작
 * 
 * 주의사항
 * 1번 인덱스부터 시작함.
 */

import java.io.*;
import java.util.*;

public class j_17298 {

    static class Node {
        int idx, value;

        Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Node> stack = new ArrayDeque<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1]; // 1부터 시작
        int[] ans = new int[N + 1];

        Arrays.fill(ans, -1); // 초기값 - -1로 초기화.
        st = new StringTokenizer(br.readLine());

        for (int i= 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {        
            // 비어있거나, 단조감소이기 전까지 진행
            while (!stack.isEmpty() && stack.peekLast().value < arr[i]) {
                Node node = stack.pollLast();
                ans[node.idx] = arr[i];
            }
            // 값을 무조건 stack에 삽입
            stack.addLast(new Node(i, arr[i]));
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
