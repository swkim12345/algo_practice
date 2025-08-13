/*
 * 평균
 * 
 * 문제 요약
 * 기말고사 망쳐서 최대값을 100점으로 두고, 다른 점수 조작
 * 
 * 주의사항
 * double을 사용해야 하는 문제이다.
 */
import java.io.*;
import java.util.*;

public class j_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        double[] arr = new double[N];
        double m = Double.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(st.nextToken());
            if (arr[i] > m) m = arr[i]; // 최대값 갱신
        }

        double sum = 0;
        for (int i = 0; i < N; i++){
            sum += arr[i] / m * 100;
        }
        System.out.println(sum / N);
    }
}
