/*
 * 소수 찾기
 * 
 * 1. 에라스토테네스의 체를 이용해 7자리 숫자까지 소수를 구한다.
 * 2. number를 dfs를 이용해 값을 만든다. 이 때, 011만 처리한다(11은 처리하기 귀찮음)
 * 3. 값을 소수 배열에서 확인
 */
class Solution {
    final static int max = 1000000 + 1;
    static boolean[] prime = new boolean[max];
    
    int primeFinder(int n) {
        for (int i = 1; i <= n; i++) {
            prime[i] = true;
        }
        int answer = 0;
        int sqrtN = (int)Math.sqrt(n);
        
        for (int i = 2; i <= n; i++) {
            if (prime[i]) answer++;
            else continue;
            for (int j = 2; i * j <= n; j++) {
                prime[i * j] = false;
            }
        }
        return answer;
    }
    public int solution(int n) {
        int answer = 0;
        answer = primeFinder(n);
        return answer;
    }
}