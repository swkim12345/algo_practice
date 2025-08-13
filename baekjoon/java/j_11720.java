/*
 * 숫자의 합
 * 
 * 문제 요약
 * N개의 숫자, 모두 합쳐서 출력.
 * 
 * 주의사항
 * int 범위 괜춘
 */

import java.io.*;
import java.util.*;

public class j_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine().trim();

        for (int i = 0; i < str.length(); i++) {
            ans += str.charAt(i) - '0';
        }
        System.out.println(ans);
    }
}
