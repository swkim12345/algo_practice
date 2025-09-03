/*
 * 전깃줄
 * 전깃줄을 최소한으로 없애 모든 전깃줄이 교차하지 않도록 만드는 문제.
 * 한 위치에는 한 전깃줄만 연결이 가능.
 * 교차란? - 두개의 점으로 이뤄진 선분끼리 겹치는 범위가 발생했을 때 나오는 경우
 * 교차를 없애려면? 교차된 선분 두개가 있다면, 둘 중 하나만 제외
 * 만약 3개가 교차되었을 때 최소한으로 없애려면? - 가장 많이 교차된 선분 순대로 없애면 됨.
 * 교차되었다는 것을 알기 위해선? A (1, 2) / B(3, 4)가 있다면, 1 < 2 and 3 > 4 or 1 >2 and 3 < 4임
 * 이럴 경우 각각의 선분 집합에 교차되었다는 것을 추가 처리
 * 교차를 없앨 때는 어떻게 처리하지? -> 모든 선분을 스캔하며 조건을 검사해야 함.
 * 
 * 선분 표시? -> Pair array (array index - 왼쪽 점, Pair first : 오른쪽 점, second : 교차된 선분 수)
 * 
 * 시간복잡도는 O(N**2)
 * 알고리즘 예측
 * 수학.
 * 
 * 초기화
 * 
 * 솔루션
 * 
 * 힌트
 * ..dp... - 역발상을 통해 문제를 해결
 * 일단 처음에는 dp를 활용하지 않고 문제를 해결하자.
 * 
 * 스포당한 이후
 * ..LIS 최장증가수열 푸는 문제이다.
 * 한쪽 점을 기준으로 정렬을 진행했다고 가정하자. 이때, 가장 적게 교차하려면 모든 다른 점들이 "증가"해야한다.
 * 이때, 가장 많은 점이 증가하는 순서대로라면, 가장 많은 전깃줄이 교차하지 않은 것과 동일하다.
 * 
 * 풀이과정
 * 초기화
 * 1. 값을 입력받은 다음, 나의 경우 왼쪽 점을 기준으로 정렬한다. - 자료구조(우선순위 큐 + Pair) 사용
 * 2. 오른쪽 점만 하나의 배열로 모은다. - pop 이후 ArrayList에 add
 * 솔루션
 * 1. 오른쪽 점에 대해 LIS를 실행한다.
 * 1-1. f(x) = max(f(x - 1), f(x - 2) ...) (if arr[x - 1], arr[x - 2] < arr[x]일 경우에만.)
 * 2. LIS가 모두 실행되면 이후 max(f(x), f(x - 1)...)를 해 최고 값을 구함.
 * 3. 출력(출력시 최고 값만 출력)
 * 
 * 우선순위 큐 + Pair 사용시 정렬 구현 필수
 * https://velog.io/@llocr/Java-Priority-Queue%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84-%ED%81%90-%EC%99%80-Comparable-Comparator
 * 참조
 * 
 * 참고 자료
 * https://www.acmicpc.net/source/96414636
 * https://www.acmicpc.net/source/96427285
 * 
 * 1. Arrays.fill()를 통해 -1, 0, 1로 초기화
 * 2. 이분탐색을 이용해 빠르게 LIS 구현
 * 3. Pair 사용 대신 이차원 배열, Arrays.sort(arr, Comparator.comparingInt(o -> o[0])) 사용.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;

public class j_2565 {
    static int[] arr;
    static int[] dp;
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
    static int solution(int N) {
        int answer = 0;

        //LIS
        for (int i = 1; i < N; i++) { // 기준점
            for (int j = 0; j < i; j++) { // 기준점보다 작은 점들.
                if (arr[j] < arr[i]) // 작은 점 < 기준점 -> LIS 충족
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 자기 자신까지 포함해 +1
            }
        }

        for (int i = 0; i < N; i++) {
            answer = Math.max(dp[i], answer);
        }
        return N - answer; // 지워야할 전깃줄의 개수이므로..!
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int a, b, answer;

        // 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            pq.add(new Pair(a, b));
        }
        arr = new int[N];
        dp = new int[N];
        
        for (int i = 0; i < N; i++) {
            Pair tmp = pq.poll();
            arr[i] = tmp.second;
            dp[i] = 1;
        }

        answer = solution(N);
        System.out.println(answer);
    }
}

