/**
 * Union Maplestory
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

ll ch[100];

int main() {
  init();
  ll m[] = {0, 60, 100, 140, 200, 250};
  ll N, M, level = 0, levelup = 0;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> M;
    ch[i] = M;
  }
  sort(ch, ch + N, greater<ll>());
  N = N > 42 ? 42 : N;
  for (int i = 0; i < N; i++) {
    level += ch[i];
    for (int j = 5; j >= 0; j--) {
      if (m[j] <= ch[i]) {
        levelup += j;
        break;
      }
    }
  }
  cout << level << " " << levelup << endl;
}
