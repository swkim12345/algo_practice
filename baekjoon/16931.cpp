/**
 * 겉넓이 구하기
 * 상하는 사이즈만큼으로 정해져 있음.
 * 나머지 면이 문제임. 어떻게 함?
 * 구해야지.. 뭐....
 */

#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;
#define total 100000
ll sum_N[total + 1];

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

int town[102][102];

int main() {
  init();
  ll M, N, tmp, result = 0;
  cin >> N >> M;
  result = N * M * 2;  // 상, 하
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= M; j++) {
      cin >> tmp;
      town[i][j] = tmp;
    }
  }
  // 한 줄마다 계산이 필요함.
  // 상하좌우에 있는 것과 마이너스 연산을 한 값과 0과 max를 한 다음 result에 더해줌.
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= M; j++) {
      result += max(0, town[i][j] - town[i][j - 1]) +
                max(0, town[i][j] - town[i - 1][j]) +
                max(0, town[i][j] - town[i + 1][j]) +
                max(0, town[i][j] - town[i][j + 1]);
    }
  }
  cout << result << endl;
}
