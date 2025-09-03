/**
 * 바지 구매
 * 식을 만든 다음, 대입해 등식이 만족되는 지 확인하면 됨.
 * 유의해야 할 점 : 허리부분은 둘레가 가장 큰 위치보다 높거나 같은 곳에서만
 * 걸린다.
 */

#include <algorithm>
#include <cmath>
#include <iostream>
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

int main() {
  ll a, b, c, d, N, u, v, result = 0;
  cin >> a >> b >> c >> d;
  cin >> N;
  while (N--) {
    cin >> u >> v;
    if (u == max(a * (v - b) * (v - b) + c, d) && v >= b) {
      result++;
    }
  }
  cout << result << "\n";
}
