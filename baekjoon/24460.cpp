/**
 * 특별상이라도 받고 싶어
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

int table[1024][1024];

// start 포함, end 배제
int solution(int x_start, int x_end, int y_start, int y_end) {
  int x_tmp, y_tmp, mn[4];

  if (x_start + 1 == x_end && y_start + 1 == y_end)
    return (table[x_start][y_start]);
  else {
    x_tmp = (x_start + x_end) / 2;
    y_tmp = (y_start + y_end) / 2;
    mn[0] = solution(x_start, x_tmp, y_start, y_tmp);
    mn[1] = solution(x_start, x_tmp, y_tmp, y_end);
    mn[2] = solution(x_tmp, x_end, y_start, y_tmp);
    mn[3] = solution(x_tmp, x_end, y_tmp, y_end);
	sort(mn, mn+4);
    return (mn[1]);
  }
}

int main() {
  init();
  ll N, tmp;
  cin >> N;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> tmp;
      table[i][j] = tmp;
    }
  }
  cout << solution(0, N, 0, N) << endl;
}
