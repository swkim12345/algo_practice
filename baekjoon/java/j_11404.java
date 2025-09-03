/*
 * 플로이드
 * 문제 요약
 * 모든 도시 쌍에 대해 도시 A -> B로 향하는 비용의 최솟값 구하기
 * 
 * 문제 풀이
 * 시작 도시와 도착 도시를 연결하는 방법은 여러가지임.
 * 하지만, 최소값을 구하려면 최소인 노선만 저장하면 됨.
 * 
 * 주의사항
 * 도시 시작은 1부터이지만, 실제 출력은 0부터이다. 따라서 넣을 때 조작해서 넣어주자.
 */
import java.io.*;
import java.util.*;

public class j_11404 {
    static final int INF = 987654321;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                arr[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 출발, 도착 도시는 1부터 시작이지만, 답은 0부터 주어지므로 조작
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = Math.min(arr[a][b], c);
        }

        floyd(arr);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == INF) arr[i][j] = 0;
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void floyd(int[][] arr) {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                for (int k = 0; k < N; k++) {
                    if (arr[i][k] > arr[i][j] + arr[j][k]) {
                        arr[i][k] = arr[i][j] + arr[j][k];
                    }
                }
            }
        }
    }
}
