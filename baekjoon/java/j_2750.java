/*
 * 수 정렬하기
 * 
 * 문제 의도
 * 버블정렬하기
 */

import java.io.*;
import java.util.*;

public class j_2750 {

    static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, tmp;
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(br.readLine());
            arr[i] = tmp;
        }

        // 버블정렬
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }
}
