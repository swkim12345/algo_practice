/**
 * 적록색약
 * 한 구역을 구하는 문제
 * RGB, 적록 색약의 경우 RG를 같은 색으로 판단함.
 * 구현하기 편한 dfs를 이용해 구현을 진행함.
 */

#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
#include <vector>
typedef long long ll;

#define R 1
#define G 2
#define RG 3
#define B 4

using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

pair<ll, ll> M[] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

ll color_map[100][100];
bool find_map[100][100];

void dfs(pair<ll, ll> position, ll color, ll N) {
  if (find_map[position.first][position.second])
    return;
  else if (!(color_map[position.first][position.second] & color))
    return;

  find_map[position.first][position.second] = 1;
  for (int i = 0; i < 4; i++) {
    pair<ll, ll> next_position = {position.first + M[i].first,
                                  position.second + M[i].second};
    if (next_position.first < 0 || next_position.first >= N ||
        next_position.second < 0 || next_position.second >= N)
      continue;
    dfs(next_position, color, N);
  }
}

int result(bool is_blindness, ll N) {
  ll count = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (find_map[i][j]) continue;
      count++;
      if (color_map[i][j] == B)
        dfs({i, j}, B, N);
      else if (color_map[i][j] == R && !is_blindness)
        dfs({i, j}, R, N);
      else if (color_map[i][j] == G && !is_blindness)
        dfs({i, j}, G, N);
      else
        dfs({i, j}, RG, N);
    }
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      find_map[i][j] = 0;
    }
  }
  return count;
}

int main() {
  ll N;
  string color;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> color;
    for (int j = 0; j < color.size(); j++) {
      if (color[j] == 'R')
        color_map[i][j] = R;
      else if (color[j] == 'G')
        color_map[i][j] = G;
      else if (color[j] == 'B')
        color_map[i][j] = B;
    }
  }
  cout << result(false, N) << " " << result(true, N) << endl;
}
