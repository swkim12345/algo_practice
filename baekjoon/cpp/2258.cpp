/**
 * 정육점
 * 가성비 좋은 것들부터 담는 그리디 문제
 * 가격보다 싼 고기를 받게 되고, 추가로 덩어리를 살 수 있다.
 * -> 가격으로 정렬한 후, 합을 구하면서 찾는 문제.
 * 가격이 같을 수도 있으므로 이를 주의하자.
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

vector<pair<ll, ll> > meat;

int main() {
  ll N, M, tmp, tmp2, sum = 0, tmp_sum = 0;
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> tmp >> tmp2;
    meat.push_back({tmp2, tmp});  // 고기 가격을 처음으로.
  }
  sort(meat.begin(), meat.end());
  tmp = meat[0].first;
  for (int i = 0; i < N; i++) {
    // 만약 가격이 달라진다면 초기화
    if (tmp != meat[i].first) {
      sum += tmp_sum;
      tmp_sum = 0;
      tmp = meat[i].first;
    }
    tmp_sum += meat[i].second;
    // 현재 고기와 이거보다 작은 고기들의 합이 크다면
    if (sum + meat[i].second >= M) {
      cout << tmp << "\n";
      return 0;
    }
  }
  tmp = meat[N - 1].first;
  // 만약 끝까지 봤는 데에도 아니라면 => 마지막 고기들을 모아서 구매
  for (int i = N - 1; meat[i].first == tmp; i--) {
    sum += meat[i].second;
    if (sum >= M) {
      cout << (N - i) * meat[i].first << "\n";
      return 0;
    }
  }
  cout << "-1\n";
}
