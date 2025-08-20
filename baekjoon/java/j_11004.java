/*
 * k 번째 수
 * 
 * 퀵정렬 알고리즘
 */

import java.io.*;
import java.util.StringTokenizer;

public class j_11004 {
    static int[] arr;

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void quicksort(int left, int right) {
        if (left >= right) return; // 종료 조건

        int pivot = arr[(left + right) / 2]; // 가운데 원소 피벗, 피벗값만 사용해서 문제를 해결
        int i = left, j = right;

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                swap(i, j);
                i++; j--;
            }
        }

        if (left < j) quicksort(left, j);
        if (i < right) quicksort(i, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quicksort(0, N - 1);
        
        System.out.println(arr[K - 1]);
    }
}
