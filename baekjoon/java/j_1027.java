/*
 * 고층 건물
 * 
 * N이 50 - O(N**5)까지 가능
 * a, b를 기준으로 선분을 만들었을 때 다른 점이 이 선분보다 위나 같은 곳에 있으면 안 된다.
 * 알고리즘 예상
 * 수학, 구현(완전탐색)
 * 알고리즘
 * 수학, 브루트포스, 기하학
 * 
 * 주의사항
 * idx이 1부터 시작
 * 
 * 초기화
 * i는 x(idx), 높이는 1이상 10억 이하 자연수
 * 
 * 솔루션
 * 선분보다 높게 있는 것만 검사해주면 끝임.
 * (a, b) / (c, d)
 * y < (d - b) / (c - a) x + b - (d - b) / (c - a) * a
 * 다만, 나눗셈을 진행할 때 double이 나오니깐, 정수에서 문제가 발생할 수 있다.
 * (c - a) y < (d - b) x + b * (c - a) - (d - b) * a - (c - a > 0)일경우에만 볼 수 있음.
 * 나눗셈을 없앤 이 공식을 사용해 문제를 풀자.
 * 
 * 오답노트
 * 이거 계산하다보면 Integer 값 범위 넘어감.
 * <= 같은 문제는 곱셉할 때 +, -를 조심하자. 0도 조심.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class j_1027 {
    static long[] buildings;

    static void solution(long N) {
        long a, b, c, d, tmp, mx = 0;
        for (long i = 1; i <= N; i++) {
            tmp = 0;
            for (long j = 1; j <= N; j++) { // 두 빌딩 선택
                if (i == j) continue;
                a = i;
                b = buildings[(int)i];
                c = j;
                d = buildings[(int)j];

                
                // 사이 빌딩 검증
                for (long k = Math.min(i, j) + 1; k < Math.max(i, j); k++) {
                    long leftSide = (c - a) * buildings[(int)k];
                    long rightSide = (d - b) * k + b * (c - a) - (d - b) * a;
                    if ((c - a < 0 && leftSide <= rightSide) 
                    || (c - a > 0 && leftSide >= rightSide)) { // 만약 빌딩이 솟아있음 못보지요
                        tmp -= 1;
                        break; // 종료조건 - 볼수없으면 더이상 검사할 필요가 없음.
                    }

                }
                tmp += 1;
            }
            mx = Math.max(mx, tmp);
        }
        System.out.println(mx);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        long tmp;
        buildings = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            buildings[i] = tmp;
        }

        solution(N);
    }
}
/*
10
1 1 1 1 1 2 1 1 1 1
 */