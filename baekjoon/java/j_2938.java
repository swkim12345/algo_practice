/*
 * 3으로 나누어 떨어지지 않는 배열
 * 
 * 문제 해설
 * 수 정렬을 바꿔 인접한 두 수의 합이 3으로 나누어 떨어지지 않는 배열 만들기
 * 
 * 문제 풀이
 * 3으로 나눠떨어지는 수 -> 00 / 12 조합
 * 따라서, 두가지가 안 됨
 * 0 개수 > 1 개수 + 2개수 + 1 -> 안 됨
 * 1 개수 > 0 && 2 개수 > 0, 이 때 0의 개수 == 0 -> 안 됨
 * 이외에는
 * 1 1 1 1 1 2 2 2 2 2 등으로 배열한 다음, 1, 2 사이에는 0이 무조건 들어가고,
 * 나머지 0의 경우는 적절히 배분한다.
 * 
 * 풀이 방식
 * 배열 3개 사용. 0으로 나눠지는 거 하나만 냅두고 나머지는 0 1 0 1 등으로 배치함.
 * 
 * 주의사항
 * N이 1부터임. 3 단독으로 나왔을 때에도 대처가 안 되요..
 * 많은 조건 분기, 해 구성하기 문제
 */

import java.io.*;
import java.util.*;

public class j_2938 {
    static LinkedList<Integer> oneList;
    static LinkedList<Integer> twoList;
    static LinkedList<Integer> threeList;

    static String solution() {
        // 종료조건
        if (threeList.size() > oneList.size() + twoList.size() + 1) return "-1";
        if (!oneList.isEmpty() && !twoList.isEmpty() && threeList.isEmpty()) return "-1";
        if (oneList.isEmpty() && twoList.isEmpty() && threeList.size() == 1) return "-1";

        StringBuilder sb = new StringBuilder();

        while (!twoList.isEmpty()) {
            if (threeList.size() > 1) {
                sb.append(threeList.pollFirst()).append(" ");
            }
            sb.append(twoList.pollFirst()).append(" ");
        }

        sb.append(threeList.pollFirst()).append(" ");

        while (!oneList.isEmpty()) {
            sb.append(oneList.pollFirst()).append(" ");
            if (!threeList.isEmpty()) {
                sb.append(threeList.pollFirst()).append(" ");
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        oneList = new LinkedList<>();
        twoList = new LinkedList<>();
        threeList = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());

        int tmp;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(st.nextToken());

            switch (tmp % 3) {
                case 0:
                    threeList.add(tmp);
                    break;
                case 1:
                    oneList.add(tmp);
                    break;
                case 2:
                    twoList.add(tmp);
                    break;
            }
        }
        bw.write(solution());
        bw.flush();
        bw.close();
    }
}
