/*
 * 좋다
 * 
 * 문제 해설
 * 두개의 합 - 다른 수 => 좋다 좋다 개수 카운팅
 * 현재 값은 같지만, 다른 인덱스라면 다른 수로 취급
 * 수의 개수는 2000개, 수는 정수 -10억 ~ +10억까지
 * 좋은 수 = 다른 두 수의 합으로 나타낼 수 있다면 좋은 수, 다른 인덱스라면 같은 값이여도 다른 수 취급
 * 
 * 문제 풀이
 * 값을 하나하나 검증, 검증할 두 수는 투포인터를 이용해 검사. start < end일 때 작동
 * 다만, 대상 값의 인덱스와 투포인터의 인덱스가 동일하다면 안 됨(다른 수 두개의 합으로 나타내야 하기 때문)
 * 
 * 오답노트
 * hashmap으로 풀려고 했음. 굳이.... 이렇게 갈 필요가 없었습니다...!
 */

import java.io.*;
import java.util.*;

public class j_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort((o1, o2) -> Integer.compare(o1, o2));

        // 타깃값 결정
        int target, p1, p2, tmp, ans = 0;
        for (int i = 0; i < N; i++) {
            target = list.get(i);
            p1 = 0; p2 = N - 1;
            
            while (p1 < p2) {
                // 인덱스와 같은 포인터 인덱스 - continue
                if (p1 == i) {
                    p1++;
                    continue;
                }
                if (p2 == i) {
                    p2--;
                    continue;
                }
                // 합이 같아면 ans 추가
                tmp = list.get(p1) + list.get(p2);
                if (tmp == target) {
                    ans++;
                    p1++; p2--;
                    break;
                } else if (tmp < target) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        System.out.println(ans);
    }
}