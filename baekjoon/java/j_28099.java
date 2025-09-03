/*
 * 이상한 배열
 * 
 * 문제 요약
 * Ai=Aj일 때, 사이에 있는 모든 수는 Ai보다 작아야 함.
 * 배열 A가 이상한 배열인지 알아보기.
 * 테케 - 20만, 배열 길이 - 20만, N의 합이 20만 이하인 것을 보장함
 * 
 * 문제 풀이
 * 세그먼트 트리를 가지고 풀 되, 구간 사이 최대값을 대표값으로 가질 수 있게 만들면 된다.
 * 이후, 이상한 배열을 판별할 때에는 start map / end map을 이용해 만약 start set에 없으면 넣고,
 * 있으면 end map에 넣고, 나중에 실제로 판별할 때 start map을 순회하며 end set에도 있다면 바로
 * 구간 쿼리를 세그트리에 날려주면 된다.
 * 
 * 주의 - array fill보다 새로 할당해주는 방식으로 바꾸었다
 */

import java.io.*;
import java.util.*;

public class j_28099 {
    static final int MAX = 200_000;
    static Map<Integer, Integer> startMap;
    static Map<Integer, Integer> endMap;

    // segtree 메서드 시작
    static int[] arr;
    static int[] tree;

    static int build(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = Math.max(build(start, mid, node * 2), build(mid + 1, end, node * 2 + 1));
    }

    static int query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return Math.max(query(start, mid, node * 2, left, right), query(mid + 1, end, node * 2 + 1, left, right));
    }

    // segtree 메서드 종료
    // 간략 구현이라 build와 query만 존재함
    // update를 최대값 세그트리에서도 사용할 수 있을까

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // map 들은 여기서 할당
        startMap = new HashMap<>();
        endMap = new HashMap<>();

        for (int t = 0; t < T; t++) {
            startMap.clear(); endMap.clear();

            int N = Integer.parseInt(br.readLine());

            arr = new int[N];
            tree = new int[4 * N];
            st  = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if (!startMap.containsKey(arr[i])) {
                    startMap.put(arr[i], i);
                } else {
                    endMap.put(arr[i], i);
                }
            }
            boolean isStrangeArray = true;

            build(0, N - 1, 1);
            Set<Map.Entry<Integer, Integer>> entrySet = startMap.entrySet();

            for (Map.Entry<Integer, Integer> element : entrySet) {
                int key = element.getKey();
                int value = element.getValue(); 

                if (!endMap.containsKey(key)) continue;
                if (key < query(0, N - 1, 1, value, endMap.get(key))) {
                    isStrangeArray = false;
                    break;
                }
            }
            sb.append(isStrangeArray ? "Yes" : "No").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
