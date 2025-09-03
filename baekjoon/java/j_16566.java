/**
 * 카드 게임
 * 
 * 문제 요약
 * 철수가 내는 카드는 정해져 있음
 * 민수는 철수가 내는 카드를 보고 낼 수 있음
 * 철수가 내는 카드 < 민수가 가지고 있는 카드 => 큰 카드 중 가장 작은 카드
 * 철수가 내는 카드 >= 민수가 가지고 있는 카드 => ?? 몰?루
 * 
 * 문제 풀이
 * 1. upperbound search 로 접근해서 풀자. - 시간 초과. 자료구조 문제라고 생각해 arraylist, linkedlist 둘 다 해봤지만 안 됩니다.
 * 2. upperbound만으로는 시간 초과가 나게 된다. 방문한 노드끼리 union 연산을 진행(큰 노드가 부모)
 * 방문한 노드 수를 줄이는 연산인 union find가 필요한 문제였다.
 * 
 * 주의사항
 * 처음에는 visited를 안 썼음. 이럴 경우 너무 생각하기가 어려움..
 */
import java.io.*;
import java.util.*;

public class j_16566 {
    static int[] minsuCards;
    static int[] arr;
    static boolean[] visited;

    static int find(int idx) {
        if (idx == arr[idx]) return idx;

        return arr[idx] = find(arr[idx]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        // 큰 값이 들어가게 보장
        if (a > b)
            arr[b] = a;
        else
            arr[a] = b;
    }

    static int upperbound(int[] list, int target) {
        int start = 0, end = list.length, mid;

        while (start < end) {
            mid = (start + end) / 2;
            if (list[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    static int step(int target) {
        int idx = upperbound(minsuCards, target);

        if(visited[idx] == true) {
            idx = find(idx) + 1;
        }
        visited[idx] = true;

        if (idx - 1 >= 0 && visited[idx - 1]) {
            union(arr[idx - 1], arr[idx]);
        }

        if (idx + 1 < minsuCards.length && visited[idx + 1]) {
            union(arr[idx], arr[idx + 1]);
        }
        return minsuCards[idx];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        minsuCards = new int[M];
        arr = new int[M];
        visited = new boolean[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            minsuCards[i] = Integer.parseInt(st.nextToken());
            arr[i] = i;
        }

        Arrays.sort(minsuCards);
        st = new StringTokenizer(br.readLine());
        int target;

        for (int i = 0; i < K; i++) {
            target = Integer.parseInt(st.nextToken());

            sb.append(step(target)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
