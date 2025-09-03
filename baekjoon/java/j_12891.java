/*
 * DNA 비밀번호
 * 
 * 문제 해설
 * DNA 문자열이 존재(문자열은 보장), 부분문자열을 구해서 비밀번호 사용
 * 최소기준(acgt의 각각의 개수)가 있음, 이를 만족하는 부분문자열의 개수는? (다른 위치라면 다르게 취급)
 * 
 * 문제 풀이
 * 슬라이딩 윈도우를 이용해 풀이. 풀 때, ACGT 개수를 카운팅해주면서 증가해줘야 함.
 * 투 포인터를 잡지 않고 푼다.
 * build 따로, 투포인터 따로 할걸...
 * 
 */

import java.io.*;
import java.util.*;

public class j_12891 {
    static final int A = 0;
    static final int C = 1;
    static final int G = 2;
    static final int T = 3;

    // cnt는 a,c,g,t의 개수 카운팅, mnCnt는 최소개수 넣기
    static int[] cnt = new int[4];
    static int[] mnCnt = new int[4];

    static void countChar(char c, int diff) {
        switch (c) {
            case 'A' :
                cnt[A] += diff;
                break;
            case 'C' :
                cnt[C] += diff;
                break;
            case 'G' :
                cnt[G] += diff;
                break;
            case 'T' :
                cnt[T] += diff;
                break;
        }
    }

    static int slidingWindow(int S, int P, String str) {
        int ans = 0;

        // init
        for (int i = 0; i < S; i++) {
            countChar(str.charAt(i), 1);

            // build중일 때 - P - 1까지 빌드
            if (i < P - 1) continue;
            if (i >= P) {
                countChar(str.charAt(i - P), -1);
            }
            if (cnt[A] >= mnCnt[A] && cnt[C] >= mnCnt[C] && cnt[G] >= mnCnt[G] && cnt[T] >= mnCnt[T]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String str = br.readLine().trim();
        st = new StringTokenizer(br.readLine());

        mnCnt[A] = Integer.parseInt(st.nextToken());
        mnCnt[C] = Integer.parseInt(st.nextToken());
        mnCnt[G] = Integer.parseInt(st.nextToken());
        mnCnt[T] = Integer.parseInt(st.nextToken());

        System.out.println(slidingWindow(S, P, str));
    }
}
