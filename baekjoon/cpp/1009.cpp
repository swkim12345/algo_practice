/**
 * 분산처리
 * 그냥 곱셉을 하고 10으로 나누는 과정
 */

#include <algorithm>
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

int main() {
  ll T, a, b, result;
  cin >> T;
  while (T--) {
    cin >> a >> b;
    result = a;
    result %= 10;
    while (--b) {
      result *= a;
      result %= 10;
    }
    if (result == 0) {
      cout << "10\n";
      continue;
    }
    cout << result << "\n";
  }
}
