/*
 * 버블 소트
 * 
 * 문제 요약
 * 정렬인 상태를 물어보는 문제 전체 정렬이라면, 정렬되었을 때의 인덱스를 출력함.
 * 
 * 문제 접근
 * 1. 당연히 시간 초과 나겠지만 버블소트로 단순히 구현.
 * 
 * 버블정렬의 경우 앞으로 한칸밖에 갈 수 없음. 따라서, 비정렬 상태 인덱스 - 정렬 상태 인덱스
 * 의 최대값이 정답이 된다.
 */

import java.io.*;
import java.util.*;

public class j_1377 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, tmp;
        N = Integer.parseInt(br.readLine());

        List<int[]> arr = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(br.readLine());

            arr.add(new int[]{tmp, i});
        }

        arr.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));

        int ans = 1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, arr.get(i)[1] - i + 1);
        }
        System.out.println(ans);
    }
}
