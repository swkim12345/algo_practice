/*
 * 신기한 소수
 * 
 * 문제 요약
 * 소수가 있을 때, 어떤 N 자리가 소수이면서 왼쪽부터 1, 2,... N - 1자리 수가 모두 소수인 수를 순서대로 출력하는 함수
 * 
 * 문제 풀이
 * 1. 에라스토테네스의 채를 이용해 먼저 소수 채를 빌드한다.
 * 2. N자리 수 첫번째부터 시작해 (2, 3, 5, 7) 각 자리수를 추가할 때 O(1)을 이용해 탐색하자.
 * 3. 탐색해 N자리에 도달했을 때, 이 수가 소수이면 출력. 아니면 버림.
 * 탐색시 작은 수부터 탐색해야하므로, 0 ~ N까지 탐색하자.
 * 
 * 문제 풀이
 * 전처리는 위에, 로직은 이제부터 아래에 두자.
 * 
 * 주의사항
 * N을 boolean으로 바로 선언하면 메모리 초과가 나게 된다.
 * 따라서, 에라스토테네스의 채를 10000까지만 선언하고, 이후 5자리 수부터는 에라스토테네스의 채를 스캔하면서
 * 소수판별을 따로해준다.
 */
import java.io.*;
import java.util.*;

public class j_2023 {
    static boolean[] isDecimal;
    static final int size = 10000;

    static void build(int N) { // 99999까지만 판별하는 build 함수
        for (int i = 2; i <= 100; i++) {
            if (isDecimal[i] == false) continue; // 기저 조건 - 소수 아님
            for (int j = i * i; j < size; j += i) {
                isDecimal[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        isDecimal = new boolean[size];
        Arrays.fill(isDecimal, true);
        isDecimal[0] = false; isDecimal[1] = false;

        build(N);

        find(0, 0, N, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void find(int before, int len, int N, StringBuilder sb) {
        if (len == N) { // 종료조건 - 길이가 N과 같을 때
            sb.append(before).append("\n");
            return;
        }
        int after;

        for (int i = 0; i < 10; i++) {
            after = before * 10 + i;
            // len이 5 이상일 때
            if (len + 1 >= 5) {
                int idx = 2;
                for (; idx < size; idx++) {
                    if (isDecimal[idx] == false) continue;
                    if (after % idx == 0) break;
                }
                if (idx == size) find(after, len + 1, N, sb);
            } else if (isDecimal[after] == true) find(after, len + 1, N, sb);
        }
    }
}