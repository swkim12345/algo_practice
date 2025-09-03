/*
 * 거의 소수
 * 
 * 이 문제는 소수의 N제곱꼴이 범위 내 얼마나 있는 지 확인하는 문제이다.
 * N 제곱꼴은 중복의 위험이 없다. 따라서, 각각의 소수마다 구해주면 된다.
 * 
 * 증명(엄밀하진 않음.)
 * 1. N의 제곱꼴이 소수가 다를 때 중복이라 가정하자.
 * 2. a, b가 모두 소수이자 다른 소수라 가정하고, a의 제곱꼴 == b의 제곱꼴이라 가정하자.
 * 3. 이 때, a의 제곱꼴을 소인수분해할 경우 (1, a, a**2..)이고, b의 제곱꼴을 소인수분해할 경우 (1, b, b**2..)
 * 4. 소인수분해한 값은 같은 값이 경우 동일하므로, a == b이여야지 성립한다.
 * 5. 처음 가정에서 a, b는 다른 소수라 가정했으므로, 이 가정은 거짓인 명제이다.
 * 6. 따라서, N의 제곱꼴은 N(소수)이 다를 경우 다른 수이다.
 * 
 * 1. 소수를 구해야한다. 에라스토테네스의 채를 이용해 구하자.
 * 1-1. 소수를 어디까지 구해야하는가? -> 2제곱부터 "거의 소수"이므로 범위 제한인 10**14의 제곱근인 10**7까지 구하면 된다.
 * 1-2. 다른 범위를 구할때에도 동일하게 풀 수 있다. 제곱근을 구할때는 double이 나오므로, ceil 함수 + long 형변환을 사용한다.
 * 2. 범위는 integer의 범위를 넘어서므로, long을 이용한다.
 * 
 * 초기화
 * 1. 범위를 입력받는다. A <= B <= 10**14
 * 2. B의 제곱근의 범위 이하까지 소수를 에라스토테네스의 채를 이용해 구한다.
 * 2-1. 자료구조의 경우 boolean 배열을 사용한다. (기본 값이 false임, true로 초기화를 해주자.)
 * 
 * 솔루션
 * 1. 구한 소수를 바탕으로 2 부터 끝까지 스캔하며 N의 제곱꼴을 구한다.
 * 2. 만약, 그 값이 범위 내에(이상, 이하) 들어온다면 answer값에 +1한다.
 * 2-1. answer의 경우 int 범위 이상일 수 있으므로 long값으로 처리한다.
 * 
 * 시간복잡도
 * 1. 초기화단계 - root(B) * root(소수개수) <= root(B) * root(B) == B
 * (B의 제곱근(소수값을 모두 확인)을 모두 스캔 * 에라스토테네스의 채로 범위를 소수개수만큼 스캔)
 * (문제는 이럴 경우 시간복잡도 초과의 위험이 있음.)
 * - 첫번째 풀이의 경우 boolean을 사용하자.
 * 2. 솔루션단계 - root(B) * (범위 내의 N 제곱꼴을 구하는 값. 2 기준 50제곱할 경우 10**15이므로, 이 값은 무시)
 * 공간복잡도
 * boolean 배열 이용시 - 10**7 * 1byte = 10mb 사용.
 * 
 * 오답노트
 * long의 제곱을 구할 때 마이너스가 나올 수 있다.
 * https://stackoverflow.com/questions/1657834/how-can-i-check-if-multiplying-two-numbers-in-java-will-cause-an-overflow
 * 따봉스택오버플로우야 고마워 - 2번째 답을 이용해 문제를 해결햇다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class j_1456 {
    static boolean[] decimal;

    static void findDecimal(long mx) {
        decimal[2] = true;

        for (long i = 2; i <= mx; i++) {
            if (decimal[(int)i] == false) continue; // 소수가 아닐 경우 스킵
            for (long j = i * 2; j <= mx; j += i) {
                decimal[(int)j] = false;
            }
        }
    }

    static void init(long mx) {
        decimal = new boolean[(int)mx + 1];
        Arrays.fill(decimal, true);

        findDecimal(mx);
    }

    static void solution(long A, long B, long mx) {
        long answer = 0;

        for (long i = 2; i <= mx; i++) {
            if (decimal[(int)i] == false) continue;
            long tmp = i;
            while(tmp <= Long.MAX_VALUE / i && (tmp = tmp * i) <= B) {
                if (tmp >= A && tmp <= B) answer += 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long A, B, mx;

        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        mx = (long) Math.ceil(Math.sqrt(B));

        init(mx);
        solution(A, B, mx);
    }
}
