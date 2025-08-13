/*
 * 최솟값 찾기
 * 
 * 문제 요약
 * N개의 수 주어질 때, 슬라이딩 윈도우 내에서 최소값을 출력함. i <= 0인 값은 무시하고 최솟값 출력
 * 
 * 문제 풀이
 * 값, 인덱스를 저장한 우선순위 큐 사용, 정렬은 값만 진행. 만약 인덱스가 윈도우 바깥 - pop  후 다시, 안 - peek
 * 입력과 동시에 우선순위 큐 사용해 문제 풀이
 * 시간 복잡도 - O(NlogN) 500만 * 8 = 4000만. 시간 제한 초과 하지 않음.
 * 하지만, 자바에서는 입력, 출력이 너무 커서 O(NlogN)으로는 풀리지 않는다.
 * 
 * 덱을 이용해 정렬해서 푸는 문제이다.
 * 덱 내부를 정렬하고, 
 * 
 * 주의사항
 * 인덱스가 1부터 시작임.
 */

import java.io.*;
import java.util.*;

public class j_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 0번째 인덱스 : 값, 1번째 인덱스 : 인덱스
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        // initial size
        Deque<int[]> deque = new ArrayDeque<>(L + 1);
        st = new StringTokenizer(br.readLine());
        int tmp;

        for (int i = 1; i <= N; i++) {
            tmp = Integer.parseInt(st.nextToken());

            // deque 내부에는 정렬된 상태를 항상 유지해야 한다.
            // 앞 : 최소, 뒤 : 최대
            // 이때, 입력값을 바탕으로 뒤 => 입력값 => pop, 뒤 < 입력값 => push를 해 무조건 정렬되게 유지한다.
            if (deque.isEmpty()) {
                deque.add(new int[]{tmp, i});
                sb.append(deque.peekFirst()[0]).append(" ");
                continue;
            }

            // 1. 인덱스 범위 이하 제거
            while (!deque.isEmpty() && deque.peekFirst()[1] < i - L + 1) {
                deque.pollFirst();
            }

            // 2. tmp값 이상 모두 제거
            while (!deque.isEmpty() && deque.peekLast()[0] >= tmp) {
                deque.pollLast();
            }

            deque.add(new int[]{tmp, i});
            sb.append(deque.peekFirst()[0]).append(" ");
        }

        bw.write(sb.append("\n").toString());
        bw.flush();
        bw.close();
    }
}
