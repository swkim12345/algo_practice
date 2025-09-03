/*
 * 모바일 광고 입찰
 * 
 * A - 몰로코 입찰 가격
 * B - 다른 업체 입찰 가격
 * A >= B - 입찰 성공
 * A + x를 일괄적으로 했을 때, K 이상의 지면을 낙찰받게 되는 가장 작은 음이 아닌 정수 X 찾기
 * 
 * B - A를 가지고 인덱스 - 이건 너무 범위가 큼. ArrayList로 관리하고, 이를 sort하는 것이 더 나음.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class j_31246 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, K, A, B, tmp, ans, x;
        ArrayList<Integer> list = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        x = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            tmp = B - A;
            if (tmp <= 0) {
                ans ++; //낙찰 성공된 경우는 고려하지 않음.
            } else {
                list.add(tmp); // 낙찰 실패한 경우
            }            
        }

        list.sort((o1, o2) -> o1 - o2);

        for (int i = 0; i < list.size() && ans + i < K; i++) {
            x = list.get(i);
        }

        System.out.println(x);
    }
}
