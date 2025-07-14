/**
 * 최소힙
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

priority_queue<ll, vector<ll>, greater<ll> > pq;

int main() {
  init();
  ll tmp, N;
  cin >> N;
  while (N--) {
    cin >> tmp;
    if (tmp == 0) {
      if (!pq.size()) {
        cout << 0 << "\n";
      } else {
        cout << pq.top() << "\n";
        pq.pop();
      }
    } else {
      pq.push(tmp);
    }
  }
}
