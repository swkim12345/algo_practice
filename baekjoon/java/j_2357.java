/*
 * 최솟값과 최댓값
 * 
 * 문제 요약
 * N개의 정수가 있을 때, a ~ b 사이의 정수(자기 자신 포함) 중 제일 작은 정수, 제일 큰 정수를 찾기
 * 
 * a는 1부터 시작
 * 
 * 문제 풀이
 * 세그먼트 트리를 빌드하되, 한번에 최대, 최소 트리를 빌드한다.
 * 
 * 주의사항
 * a는 1번부터 시작
 */

import java.io.*;
import java.util.*;

public class j_2357 {
    static class SegTree {
        int N, SEG_N;
        int[] arr;
        int[] minTree;
        int[] maxTree;

        SegTree(int N, int[] input) {
            this.N = N;
            this.SEG_N = 4 * N;

            arr = new int[N];
            minTree = new int[SEG_N];
            maxTree = new int[SEG_N];

            System.arraycopy(input, 0, arr, 0, N);
        }

        // node - segtree, start - arr, end - arr
        void build(int node, int start, int end) {
            if (start == end) {
                minTree[node] = arr[start];
                maxTree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            build(node * 2, start, mid); build(node * 2 + 1, mid + 1, end);
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }

        void build() {
            build(1, 0, N - 1);
        }

        // node - segtree, start, end - arr, p, q - arr
        int minQuery(int node, int start, int end, int p, int q) {
            if (q < start || end < p) return Integer.MAX_VALUE;
            if (p <= start && end <= q) return minTree[node];

            int mid = (start + end) / 2;
            return Math.min(
                minQuery(node * 2, start, mid, p, q),
                minQuery(node * 2 + 1, mid + 1, end, p, q)
            );
        }

        // node - segtree, start, end - arr, p, q - arr
        int maxQuery(int node, int start, int end, int p, int q) {
            if (q < start || end < p) return Integer.MIN_VALUE;
            if (p <= start && end <= q) return maxTree[node];

            int mid = (start + end) / 2;
            return Math.max(
                maxQuery(node * 2, start, mid, p, q),
                maxQuery(node * 2 + 1, mid + 1, end, p, q)
            );
        }

        int[] query(int p, int q) {
            return new int[]{ minQuery(1, 0, N - 1, p, q), maxQuery(1, 0, N - 1, p, q) };
        }

        void printArray() {
            System.out.print("배열 : ");
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegTree segtree = new SegTree(N, arr);
        segtree.build();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] ans = segtree.query(a - 1, b - 1);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
