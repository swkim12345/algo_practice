/**동전1
 * dp로 문제를 해결하는 문제
 * 
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
ll coin[100], size[100];

int main() {
  init();
  ll n, k, tmp;
  cin >> n >> k;
  for (int i = 0; i < n; i++) {
    cin >> tmp;
    coin[i] = tmp;
  }

}
