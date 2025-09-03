/**
 * 주식 - 실버2
 * 그리디 - 쌀때 사서, 비싸게 판다. 단, 뒤에 있을 때
 */
#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

void solved() {
  ll N, tmp;
  cin >> N;
  vector<ll> days, max_days;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    days.push_back(tmp);
  }
  max_days.push_back(0);
  for (int i = 1; i < N; i++) {
    if (max_days[i - 1] > days[N - i - 1])
      max_days.push_back(days[N - i - 1]);
    else
      max_days.push_back(max_days[i]);
  }
  ll result = 0;
  for( int i = 0; i < N; i++) {
    result += max_days[i] - days[i];
  }
  if (result < 0) result = 0;
  cout << result << "\n";
}

int main() {
  init();
  ll T;
  cin >> T;
  while (T--) {
     solved();
  }
}
