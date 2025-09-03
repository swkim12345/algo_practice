/**
 * 듣보잡
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

struct gt {
  bool operator()(const string &a, const string &b) const {
    auto ret = mismatch(a.begin(), a.end(), b.begin(), b.end());
    return *(ret.first) > *(ret.second);
  }
};

set<string> tmp;
priority_queue<string, vector<string>, gt> pq;

int main() {
  init();
  ll N, M;
  string in;
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> in;
    tmp.insert(in);
  }
  for (int i = 0; i < M; i++) {
    cin >> in;
    if (tmp.find(in) != tmp.end()) {
      pq.push(in);
    }
  }
  cout << pq.size() << endl;
  while (!pq.empty()) {
    cout << pq.top() << endl;
    pq.pop();
  }
}
