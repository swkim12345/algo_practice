/*
 * 좋다
 * 
 * 문제 해설
 * 두개의 합 - 다른 수 => 좋다 좋다 개수 카운팅
 * 
 * 문제 풀이
 * 인덱스 기준으로 2개 브루트포스
 * 좋은 수 판별 - HashSet 이용해서 있으면 좋은 수 카운팅 ++
 */

import java.io.*;
import java.util.*;

public class j_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int tmp;

        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else {
                map.put(tmp, 1);
            }
        }

        int ans = 0, val;

        // 0이 3개 초과
        if (map.containsKey(0)){
            val = map.get(0);
            if (val > 2) {
                ans += val;
                map.remove(0);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                tmp = arr[i] + arr[j];
                if (map.containsKey(tmp)) {
                    val = map.get(tmp);
                    // 만약 (3, 1) / (3, 2) 라면 (3이 동일, 다만 인덱스가 1, 2임)
                    if (val == 1 && (arr[i] == tmp || arr[j] == tmp)) continue;
                    if (val == 2 && arr[i] == tmp && arr[j] == tmp) continue;
                    ans ++;
                    
                    if (val == 1) {
                        map.remove(tmp);
                    } else {
                        map.put(tmp, val - 1);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
