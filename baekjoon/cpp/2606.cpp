/**
 * 바이러스
 * 1부터 시작하는 dfs로 구현하자
 * 무방향이므로 양쪽에 추가하자
 */

#include <algorithm>
#include <cmath>
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

vector<ll> gp[101];
bool isSameGroup[101];
ll ret;

void result(ll start) {
  isSameGroup[start] = true;
  while (gp[start].size() > 0) {
    ll tmp = gp[start].back();
    gp[start].pop_back();
    if (isSameGroup[tmp]) continue;
    result(tmp);
    ret++;
  }
}

int main() {
  init();
  ll N, start, end;
  cin >> start >> N;
  while (N--) {
    cin >> start >> end;
    gp[start].push_back(end);
    gp[end].push_back(start);
  }
  result(1);

  cout << ret << endl;
}
