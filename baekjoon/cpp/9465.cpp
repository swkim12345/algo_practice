/**
 * 스티커
 * 현재 값은 두개로 나뉜다 (위에, 아래 선택)
 * 위에 = max(전 아래, 전전의 최대값)
 * 아래 = max(전 위에, 전전의 최대값)
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

vector<pair<ll, ll> > max_set;
vector<ll> set1, set2;

void solved(ll M) {
  max_set.push_back({set1[0], set2[0]});
  if (M == 1) {
    cout << max(set1[0], set2[0]) << endl;
  }
  max_set.push_back(
      {max(set2[0] + set1[1], set1[1]), max(set1[0] + set2[1], set2[1])});
  if (M == 2) {
    cout << max(max_set[1].first, max_set[1].second) << endl;
  }
  for (int i = 0; i < M - 2; i++) {
    max_set.push_back(
        {max(set2[i + 1] + set1[i + 2], max(set1[i], set2[i]) + set1[i + 2]),
         max(set1[i + 1] + set2[i + 2], max(set1[i], set2[i]) + set2[i + 2])});
  }
  cout << max(max_set[M - 1].first, max_set[M - 1].second) << endl;
}

int main() {
  init();
  ll N, M, tmp;
  cin >> N;
  for (int i = 0; i < N; i++) {
	cin >> M;
    for (int j = 0; j < M; j++) {
      cin >> tmp;
      set1.push_back(tmp);
    }
    for (int j = 0; j < M; j++) {
      cin >> tmp;
      set2.push_back(tmp);
    }
    solved(M);
    max_set.clear();
    set1.clear();
    set2.clear();
  }
}
