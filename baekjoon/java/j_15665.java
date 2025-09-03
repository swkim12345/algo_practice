/*
 * N과 M(11)
 * 
 * 길이가 M인 수열을 모두 구하는 문제
 * 같은 수를 여러번 골라도 됨. 
 * 각각 분리되어 있는 조건, n * n * n ... (m번 반복)
 * 다만, 중복되는 수열을 여러번 출력하면 안된다 -> 동일한 수는 출력할 수 없음.
 * set을 이용해 중복되는 수를 지운 다음, 각각의 수를 array 에 담아서 출력하는 전략을 사용하자.
 * 자바의 경우 재귀 한계는 대략 만번정도이다. 이문제의 경우 입력으로 주어지는 수가 10000번밖에 되지 않기 때문에
 * 이를 수정해서 내놓으면 된다.
 * 이전의 값을 가지고 있어야 하는 데, StringBuilder의 경우 계속 가져가면서 누적된다.
 * 따라서, String을 바로 넘기는 방식으로 구현하자.
 * 
 * 오답노트
 * - 재귀 종료조건할 때 return을 걸어서 내놓아야 한다.
 * - 각각의 수열 값은 공백으로 분리해서 내놓아야 한다.
 * - 마지막에 제출할 때에는 전체적으로 로직을 확인한 다음 런하자.
 * 
 * 이후 참조 답
 * https://www.acmicpc.net/source/96254922
 * 차이점
 * 1. StringBuilder와 static int[]를 이용해 값을 저장하고, 마지막 종료조건때 Stringbuilder에 추가
 * 2. 모두 추가된 다음, recursive(dfs로 표현)가 끝나면 StringBuilder에 있는 것을 모두 출력함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Arrays;

public class j_15665 {
    static Integer[] arr;
    // 이거 출력할 때 시간 초과 날거같은데...
    static void recursive(String answer, int idx, int M) {
        // 종료조건
        if (idx == M) {
            System.out.println(answer);
            return;
        }


        for (int i = 0; i < arr.length; i++) {
            recursive(answer + " " + arr[i] ,idx + 1, M);
        }
    }
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int N, M;
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());

       st = new StringTokenizer(br.readLine());

       Set<Integer> set = new HashSet<>();
        // StringTokenizer 는 iterator가 없음
       for (int i = 0; i < N; i++) {
        set.add(Integer.parseInt(st.nextToken()));
       }
        arr = set.toArray(new Integer[0]);

       Arrays.sort(arr);

       for (int i = 0; i < arr.length; i++) {
            recursive(Integer.toString(arr[i]), 1, M);
       }
    }
}
