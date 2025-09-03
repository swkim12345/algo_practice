/**
 * 구간 합 구하기 4
 * 부분합을 이용한 문제임.
 * 부분합을 먼저 구하고, 이후 이것의 차이를 구하는 문제이다.
 */

#include <algorithm>
#include <iostream>
using namespace std;
typedef long long ll;
#define total 100000
ll sum_N[total + 1];

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

int main() {
  ll N, M, tmp, start, end;
  init();
  cin >> N >> M;
  for (int i = 1; i <= N; i++)  // 1부터 시작이므로 같은 것 까지 해야 한다.
  {
    cin >> tmp;
    sum_N[i] = sum_N[i - 1] + tmp;
  }
  for (int i = 1; i <= M; i++) {
    cin >> start >> end;
    cout << sum_N[end] - sum_N[start - 1] << '\n';
  }
  return (0);
}
