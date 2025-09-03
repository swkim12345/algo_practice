/*
 * 균형
 * 
 * 문제 요약
 * 정점의 개수를 이용해 만들 수 잇는 최대 높이 구하기.
 * 최대 V개의 정점을 사용할 수 있음.  
 * 
 * 문제 풀이
 * bottom-up 방식 빌드
 * 정점의 개수를 이용해 높이를 만드는 문제가 아니라 높이를 가지고 정점의 개수를 찾는 문제임.
 * 최소 정점을 가지고 높이를 만들고, 이를 이분탐색 혹은 리니어 탐색 (42밖에 안되서 가능함.)을 하면 됨
 * f(x) = f(x - 1) + f(x - 2) + 1;
 * 찾는 높이가 V라면, V < f(x) -> x - 1이 답임
 */

import java.io.*;
import java.util.*;

public class j_22968 {
    static List<Integer> height;

    static void build() {
        height = new ArrayList<>();
        height.add(0); height.add(1); height.add(2);

        for (int i = 3; i <= 43; i++) {
            height.add(height.get(i - 2) + height.get(i - 1) + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        build();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int target = Integer.parseInt(br.readLine());

            for (int j = 1; j <= 43; j++) {
                if (target < height.get(j)) {
                    System.out.println(j - 1);
                    break;
                }
            }
        }
    }
}
