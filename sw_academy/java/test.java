package sw_academy.java;

import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        long N, rootN, step;

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Long.parseLong(br.readLine());
            step = 0;

            while (N != 2) {
                rootN = (long)Math.sqrt(N);
                if (rootN * rootN == N) {
                    N = rootN;
                    step += 1;
                } else {
                    step += (rootN + 1) * (rootN + 1) - N + 1; // rootN + 1로 나누기까지 포함
                    N = rootN + 1;
                }
            }
            sb.append(String.format("#%d %d\n", testCase, step));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
