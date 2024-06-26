/**
 * 쉬운 최단거리
 * 다익스트라를 이용한 문제 풀이.
 * 목표지점부터 시작해서 가는 최단거리를 구하는 문제 one to all
 * 인접행렬로 주워져서 이걸 가지고 문제를 풀면 된다.
 * 편의상 첫번째 행렬을 x, 두번째 행렬을 y로 가정한 다음 문제를 풀 것이다.
 * 문제를! 꼼꼼히! 읽자!!!!!!! 바보야!!!!!1
 */

#include <climits>
#include <iostream>
#include <queue>
#include <vector>
typedef long long ll;
using namespace std;
#define total 1001

struct s_cmp {
  ll x, y, cost;
  s_cmp(ll x, ll y, ll cost) : x(x), y(y), cost(cost) {}
  bool operator<(const s_cmp& b) const {
    return cost > b.cost;  // 이렇게 해야 최소 우선순위 큐가 됩니더
  }
};

ll map[total][total];      // 방문 가능, 불가능을 저장하는 배열
ll visited[total][total];  // 코스트를 저장하는 배열.
ll mv[4][2] = {
    {0, 1}, {1, 0}, {-1, 0}, {0, -1}};  // x, y를 쉽게 접근하기 위한 배열
priority_queue<s_cmp> pq;

bool check_outside(ll x, ll y, ll n, ll m) {
  if (x < 0 || x >= n || y < 0 || y >= m)
    return (true);
  else
    return (false);
}

void djistra(ll n, ll m) {
  // 먼저 방문했는 지 확인
  ll tmp_x, tmp_y;

  while (!pq.empty()) {
    s_cmp p = pq.top();
    pq.pop();
    for (int i = 0; i < 4; i++) {
      tmp_x = p.x + mv[i][0];
      tmp_y = p.y + mv[i][1];
      if (check_outside(tmp_x, tmp_y, n, m) || map[tmp_x][tmp_y] == 0) continue;
      if (p.cost + 1 < visited[tmp_x][tmp_y]) {
        pq.push(s_cmp(tmp_x, tmp_y, p.cost + 1));
        visited[tmp_x][tmp_y] = p.cost + 1;
      }
    }
  }
}

int main() {
  ll n, m, tmp, x, y;
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> tmp;
      if (tmp == 2) {
        x = i;
        y = j;
      }
      map[i][j] = tmp;
      visited[i][j] = LLONG_MAX;
    }
  }
  // 초기화, 처음 시작을 0으로 잡고, queue에 삽입
  pq.push(s_cmp(x, y, 0));
  visited[x][y] = 0;
  djistra(n, m);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (map[i][j] == 0) { //원래부터 가지 못하는 데
        cout << 0 << " ";
        continue;
      }
      if (visited[i][j] == LLONG_MAX) { //갈 수는 있는 곳이나, 막혀있어 가지 못하는 데.
        cout << -1 << " ";
        continue;
      }
      cout << visited[i][j] << " ";
    }
    cout << endl;
  }
}
