/**
 * 이친수
 * 1로만 시작, 11은 불가
 * 1 - 0
 * 0 - 0, 1
 * 0으로 끝나는 것과 1로 끝나는 것을 다르게 배열로 관리
 * 그러면 피보나치와 같게 된다.
 */

#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll dp[2][100];

int main() {
  init();
  ll N;
  cin >> N;
  dp[0][1] = 0;
  dp[1][1] = 1;  // 1로 시작
  for (int i = 2; i <= N; i++) {
    dp[0][i] = dp[0][i - 1] +
               dp[1][i - 1];  // 0으로 끝날 수 있는 값 - 그 전값은 상관 없음
    dp[1][i] = dp[0][i - 1];  // 전값이 1이 되면 안 됨.
  }
  cout << dp[0][N] + dp[1][N] << endl;
}
