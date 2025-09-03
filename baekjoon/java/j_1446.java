/*
 * 지름길
 * 
 * 처음에는 그리디라고 생각함 - https://www.acmicpc.net/problem/1931
 * 회의실 배정 문제와 비슷하다고 생각했음. 왜 그리디로 풀 수 없을까?
 * 왜냐면 지름길이 여러개 존재할 수 있고, 지름길을 선택할 때에는 출발 / 도착 값을 고려해야 하므로.
 * 국소 최적해를 구하면, 지름길이 여러개 존재, 지름길마다 길이가 다를 수 있다는 문제에 봉착함
 * 
 * 고속도로 지나감 - 일방통행(고속도로 역주행 안 됨, 지름길 일방통행)
 * 시작위치는 도착위치보다 작을 수 있으나, 도착위치는 초과할 수 있음(초과하면 답이 될 수 없음)
 * 다익스트라...?
 * 다익스트라 : 어느 한점에서 다른 모든 점으로 가는 최소값을 구하는 알고리즘.
 * 1. 다익스트라 풀이...? 왜 다익스트라로 풀어야 하지.dp로 풀면 깔끔한데
 * 
 * 2. dp
 * f(x) = min(f(x - h) + (지름길의 길이), ...., f(x - 1) + 1)
 * 이건 bottom - up 방식으로 빌드하면서 풀면 되는 문제임.
 * 
 * 오답노트
 * priorityqueue에 초기화 안 함;; 
 * Comparable 타입 정보 주지 않음.
 * min 구하는 건데 왜 max 하고 있냐..
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class j_1446 {
    static PriorityQueue<Path> pq = new PriorityQueue<>();
    static int[] road;

    static class Path implements Comparable<Path> {
        public int start, end, length;

        Path(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Path p) {
            return this.start - p.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, D;

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        road = new int[D + 1];

        // road 초기화
        for (int i = 1; i <= D; i++) {
            road[i] = i;
        }

        int start, end, length;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            length = Integer.parseInt(st.nextToken());
            pq.add(new Path(start, end, length));
        }

        // dp 빌드 - bottom up 방식으로 dp 진행

        for (int i = 0; i <= D; i++) {
            if (i != 0)
                road[i] = Math.min(road[i], road[i - 1] + 1);
            //지름길 체크
            while (!pq.isEmpty() && pq.peek().start == i) {
                Path path = pq.poll();
                start = path.start;
                end = path.end;
                length = path.length;
                if (end > D) continue; //지름길이 끝보다 더 길경우

                road[end] = Math.min(road[end], road[i] + length);
            }
        }

        System.out.println(road[D]);
    }
}