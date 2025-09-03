/**
 * ZOAC
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

bool fun(const pair<ll, char> &a, const pair<ll, char> &b) {
  if (a.second != b.second) {
    return a.second < b.second;
  } else {
    return a.first > b.first;
  }
}

pair<ll, char> tmp[101];
pair<ll, char> out[101];

int main() {
  init();
  string in;
  cin >> in;
  for (int i = 0; i < in.size(); i++) {
    tmp[i] = {i, in[i]};
  }
  sort(tmp, tmp + in.size(), fun);
  for (int i = 0; i < in.size(); i++) {
    out[i].first = tmp[i].first;
    out[i].second = tmp[i].second;
    sort(out, out + i + 1);
    for (int j = 0; j <= i; j++) {
      cout << out[j].second;
    }
    cout << endl;
  }
}
