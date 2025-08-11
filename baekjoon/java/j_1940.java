/*
 * 주몽
 * 
 * 문제 요약
 * 두 재료의 고유 번호를 합쳐 M이 되면 갑옷 만들어짐
 * 
 * 문제 풀이
 * 투포인터를 이용한 풀이
 * 포인터 값이 같아지기 전까지(자기 자신을 가지고 만들 순 없으므로) 돌면서 진행
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

public class j_1940 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.sort((o1, o2) -> Integer.compare(o1, o2));

        // 투포인터
        int p1 = 0, p2 = arr.size() - 1, target, ans = 0;

        while (p1 < p2) {
            target = arr.get(p1) + arr.get(p2);

            if (target == M) {
                ans++;
                p1++;
                p2--;
            } else if (target > M) {
                p2--;
            } else {
                p1++;
            }
        }

        System.out.println(ans);
    }
}
