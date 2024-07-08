/**
 * 카드 2
 * 순서가 있나..?
 * 버리는 것은 홀수를 버리고, 짝수를 버리게 된다.
 * 그냥 구현하자;
 */
#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
typedef long long ll;
using namespace std;

deque<ll> tmp;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}
int main() {
  init();
  long long N, result, isBottom = 0;
  cin >> N;
  if (N == 1) {
    cout << 1 << endl;
    return (0);
  }
  for (ll i = 1; i <= N; i++) {
    tmp.push_back(i);
  }
  while (!tmp.empty()) {
    result = tmp.front();
    tmp.pop_front();
    if (isBottom % 2 == 1) {
      tmp.push_back(result);
    }
    isBottom = (isBottom + 1) % 2;
  }
  cout << result << endl;
}
