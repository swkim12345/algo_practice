/**
 * 배열 합치기
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

priority_queue<ll, vector<ll>, greater<ll> > result;

int main() {
  init();
  ll tmp1, tmp2, tmp;
  cin >> tmp1 >> tmp2;
  for (int i = 0; i < tmp1; i++) {
    cin >> tmp;
    result.push(tmp);
  }
  for (int i = 0; i < tmp2; i++) {
    cin >> tmp;
    result.push(tmp);
  }
  while (!result.empty()) {
    cout << result.top() << " ";
    result.pop();
  }
  cout << "\n";
}
