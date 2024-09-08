/**
 * Longest Path
 */

#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

#define total 10001

vector<ll> edge[total];

ll max_N[total];

void dfs(ll start) {
  ll ret;
  ret = 0;
  // 만약 0이 아니라면 이미 dfs를 실행한 노드이므로 바로 리턴함.
  // 혹은 엣지가 0개여도 최대값이 없는 것과 마찬가지 이므로 바로 리턴함.
  if (max_N[start] != 0 || edge[start].size() == 0) {
    return;
  }
  for (vector<ll>::iterator iter = edge[start].begin();
       iter != edge[start].end(); ++iter) {
    // 가지고 있는 엣지에서 dfs를 실행.

    dfs(*iter);
    // 다음 dfs보다 한단계 더 간것이므로 1을 더해준다.
    ret = max(ret, max_N[*iter] + 1);
    //cout << "*iter : " << *iter << " ret : " << ret << endl;
  }
  max_N[start] = max(max_N[start], ret);
}

int main() {
  ios_base::sync_with_stdio(false);  // buffering false
  cin.tie(NULL);
  cout.tie(NULL);
  freopen("input-3.txt", "r", stdin);
  ll works, n, m;
  cin >> works;
  for (int i = 1; i <= works; i++) {
    cin >> n >> m;
    for (int j = 0; j <= total; j++) {
      edge[j].clear();
      max_N[j] = 0;
    }
    ll start, end;
    for (int j = 0; j < m; j++) {
      cin >> start >> end;
      edge[start].push_back(end);
    }
    for (int j = 1; j <= n; j++) {
      dfs(j);
    }
    ll max_value = 0;
    for (int j = 1; j <= n; j++) {
      max_value = max(max_value, max_N[j]);
    }
    cout << "#" << i << " " << max_value << endl;
  }
}
