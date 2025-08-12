/*
 * 수열과 쿼리 15
 * 
 * 문제 요약
 * 1 i v : i번째 값을 v로 바꿈
 * 2 : 수열에서 크기가 가장 작은 값의 인덱스 출력, 값이 여러개면 인덱스 작은 것 출력
 * 
 * 
 * 풀이
 * 1. 우선순위 큐. 1순위 : 크기, 2순위 : 인덱스
 * 
 * 2. 세그먼트 트리
 * 1. build, sum(구간 합), update(값 업데이트)
 * 구간합을 저장해놓은 자료구조.
 * 이 구간합을 이용해서 크기가 가장 작은 값의 인덱스를 출력, 값이 여러개면 인덱스 작은 것 출력
 * 1번 - left, right child 비교해 left가 작으면 left로 이동. 하지만, 이게 값이 가장 작다는 것을 보장하지 않음. 기각
 * 
 * 인덱스에 값 저장
 * 1. 정렬, 이후 업데이트하게 되면... 세그트리를 어떻게 하지.
 * 
 * 함정
 * 수열은 1번부터 시작
 */

import java.io.*;
import java.util.*;

public class j_14427 {
    static int[] arr;

    static void solutionUsingPriorityQueue(BufferedReader br, StringTokenizer st, int N, int M) throws IOException {
        StringBuilder sb = new StringBuilder();

        // 0번째 인덱스 : 값, 1번째 인덱스 : arr의 인덱스값
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int sizeCmp = Integer.compare(o1[0], o2[0]); // 값은 오름차순 정렬

            if (sizeCmp == 0) {
                return Integer.compare(o1[1], o2[1]); // 인덱스는 오름차순 정렬
            } else {
                return sizeCmp;
            }
        });

        for (int i = 1; i <= N; i++) {
            pq.add(new int[]{arr[i], i});
        }
        
        int value, idx;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) { 
                idx = Integer.parseInt(st.nextToken());
                value = Integer.parseInt(st.nextToken());

                pq.add(new int[]{value, idx});
                arr[idx] = value;
            } else { // 크기가 가장 작은 값의 인덱스 출력
                int[] peek;
                
                while (true) {
                    peek = pq.peek();
                    value = peek[0];
                    idx = peek[1];
                    
                    if (arr[idx] != value) {
                        pq.poll();
                    } else {
                        break;
                    }
                }
                
                sb.append(idx).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        solutionUsingPriorityQueue(br, st, N, M);
    }
}
