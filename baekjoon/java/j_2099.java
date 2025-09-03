
/*
 * The game of death
 * 
 * 문제 요약
 * 한 사람이 자기 자신이 아닌 두 사람을 가리키고, K번째 지목된 사람이 걸리는 게임임.
 * a번 사람이 시작했을 때 b번 사람이 걸리는 경우가 있는 지 없는 지 알아내는 프로그램 작성하기
 * 
 * 문제 풀이
 * 첫번째 아이디어.
 * 인접 리스트가 아닌 인접 행렬로 문제를 바꿔 생각해보자.
 * 0 1 0
 * 0 0 1
 * 0 0 0
 * 이런 인접 행렬이 있다 가정하자.
 * 상단의 1의 경우, a->b로 가는 간선, 두번째 1의 경우 b->c로 가는 간선이다.
 * 만약에, 2번 거쳐서 가는 것을 찾는다고 가정하자.
 * 우리가 찾아야 할 것은, a->b->c이다. 행렬곱을 이용해 구한다 가정하면..
 * 0 0 1
 * 0 0 0
 * 0 0 0
 * 오른쪽 상단의 1을 생각해보자.
 * 행렬곱을 할 때, 왼쪽의 행렬, 최상단 세 점의 경우는 a->(a,b,c)로 가는 간선이었고,
 * 위의 행렬, 최우단 상에서 하로 가는 세 점의 경우는 (a, b, c) -> c로 가는 간선의 모음이었다.
 * 이를 곱을 이용해 구한다면.... a -> (a, b, c) -> c로 가는 간선이 모여져 있다는 것을
 * 관찰할 수 있고, 우리가 원하는 "2번 걸쳐서 갈 수 있는 곳"이 나오게 된다.
 * 
 * 두번째 아이디어.
 * 여기까진 가능하다. 문제는 행렬곱을 이용해 구한다면, 시간복잡도는 N**3이다.
 * 최적화를 통해 최대 N**2.* 정도로 줄일 수 있으나, 여전히 K가 백만이라는 것에는 부족하다.
 * 행렬곱의 시간복잡도의 하한선은 N**2로 정해져있으므로, K의 수를 빠르게 줄여야 한다.
 * 이 때, 분할 정복을 이용한 거듭제곱을 이용할 수 있다.
 * C**N의 경우, C**N/2 * C**N/2 (짝수)로 축소할 수 있으므로,
 * logK만에 거듭제곱을 마칠 수 있다.
 * 
 * 총 시간복잡도
 * 행렬곱 : O(N**3 * logK), 대략 5600만회정도의 계산
 * M번 쿼리 : O(M), 대략 100만회정도의 계산
 * 따라서, 3초라는 시간에 넉넉히 들어갈거라 예상된다.
 * 
 * 주의사항
 * 사람의 경우 1번부터 시작된다.
 * int로 할 경우, stack overflow가 발생할 수 있다.
 * 따라서 boolean을 이용한 풀이로 바꿔야 한다.
 */
import java.io.*;
import java.util.*;

public class j_2099 {
    static int N, K, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][Integer.parseInt(st.nextToken())] = true;
            arr[i][Integer.parseInt(st.nextToken())] = true;
        }

        arr = dividePower(arr, K);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (arr[a][b])
                bw.write("death");
            else
                bw.write("life");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static boolean[][] dividePower(boolean[][] arr, int K) {
        boolean[][] ans = new boolean[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                ans[i][j] = arr[i][j];
            }
        }
        K --;

        while (K > 0) {
            if (K % 2 == 1) {
                ans = power(arr, ans);
            }
            arr = power(arr, arr);
            K /= 2;
        }

        return ans;
    }

    static boolean[][] power(boolean[][] left, boolean[][] upper) {
        int a = left.length;
        int b = upper.length;
        int c = upper[0].length;

        boolean[][] ans = new boolean[a][c];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                for (int k = 0; k < c; k++) {
                    ans[i][k] = ans[i][k] | (left[i][j] & upper[j][k]);
                }
            }
        }
        return ans;
    }
}
