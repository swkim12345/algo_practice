/*
 * 수들의 합 5
 * 
 * 문제
 * 연속된 자연수의 합의 경우의 수가 몇개인 지?
 * 
 * 풀이
 * 1. 투포인터
 * 2. 누적합 후 풀이
 * 3. 세그트리
 * 간단한 방식으로 풀..자..
 */

import java.io.*;
import java.util.*;

public class j_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int start = 0, end = 1, sum = 1, ans = 0;
        while (start <= end) {
            if (sum < N) 
                sum += ++end;
            else if (sum > N) 
                sum -= start++;
            else {
                ans++;
                sum -= start++;
                sum += ++end;
            }
        }
        System.out.println(ans);
    }
}
