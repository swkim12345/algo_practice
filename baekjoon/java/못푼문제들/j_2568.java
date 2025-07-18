package 못푼문제들;
/*
 * 전깃줄 - 2
 * 
 * 이 문제의 경우 10만개의 전깃줄이 들어올 수 있음.
 * 따라서, O(NlogN) 이하 알고리즘만 가능함. -> LIS에 lowerbound를 이용한 이분탐색이 필요함.
 * 다만, LIS에 이분탐색알고리즘을 섞으면 트래킹이 안될거 같음. - 된다네요;
 * https://lazyren.github.io/devlog/lis-algorithm.html
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class j_2568 {
    static int[] arr;
    static int[][] dp;
    // 인터페이스인 Comparable을 상속받고, 제너릭을 Pair로 설정, 이후 compareTo를 구현.
    static class Pair implements Comparable<Pair> {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        // 리턴값이 1, 0, -1로 리턴
        @Override
        public int compareTo(Pair p) {
            if (p.first < this.first) return 1;
            else if (p.first == this.first) return 0;
            else return -1;
        }
    }
    static void solution(int N) {
        int answer = 0, idx = 0;
        boolean[] check = new boolean[N];

        //LIS
        for (int i = 1; i < N; i++) { // 기준점
            for (int j = 0; j < i; j++) { // 기준점보다 작은 점들.
                if (arr[j] < arr[i] && dp[i][0] < dp[j][0] + 1) // 작은 점 < 기준점 -> LIS 충족
                {
                    dp[i][0] = dp[j][0] + 1;
                    dp[i][1] = j;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dp[idx][0] < dp[i][0]) idx = i;
        }
        while (dp[idx][1] != idx) {
            check[idx] = true;
            answer += 1;
            idx = dp[idx][1];
        }
        check[idx] = true;
        answer += 1;

        System.out.println(N - answer); // 지워야할 전깃줄의 개수이므로..!
        for (int i = 0; i < N; i++) {
            if (check[i] == false) {
                System.out.println(i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int a, b;

        // 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            pq.add(new Pair(a, b));
        }
        arr = new int[N];
        dp = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            Pair tmp = pq.poll();
            arr[i] = tmp.second;
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        solution(N);
    }
}
