/**
 * :danceplant:
 * 가지 싫은데...
 * 그리디로 움직여가면서 최적의 해를 찾아 이동하는 문제
 * 양분의 총 개수는 3000 * 3000 -> 9000000, 대략 10**7
 * 현재 최대로 많이 먹을 수 있는 곳 -> 그리디
 * 몸을 움직일때마다 연속해서 계속해서 계산을 진행하면 많은 연산량이 요구된다.
 * 따라서, 이를 해결하기 위해 상하좌우의 값을 저장하는 struct를 만든다(start,
 * end, total) 현재 어디에 있는 지 저장하기 위해 상하좌우의 값을 저장한다. 만약,
 * 값이 달라진다면 그 이상의 값을 더하는 방식으로 해결한다. 결국, 시간 복잡도는
 * n**2 -> 10**7정도이다.
 *
 * 주의해야 할 점 : 0 미만, n 초과일 때에는 추가로 더하지 않는다.
 */
#include <algorithm>
#include <iostream>
#include <vector>
#define total 3000
typedef long long ll;
using namespace std;

struct amount {
  ll s;
  pair<ll, ll> start, end;
};

// 상하좌우(U,D,L,R), 좌우로 늘어나는 것도 고려를 해야함.
pair<ll, ll> coord[] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
char str[] = {'U', 'D', 'L', 'R'};
amount t[4];

ll N[total + 1][total + 1];

void init(amount tmp, pair<ll, ll> start, pair<ll, ll> end) {
  tmp.s = 0;
  tmp.start = start;
  tmp.end = end;
  for (int i = start.first; i <= end.first; i++) {
    for (int j = start.second; j <= end.second; j++) {
      tmp.s += N[i][j];
    }
  }
}

// 문제 풀이. 만약 값이 전의 값하고 달라진다면 3개의 값을 갱신해야 함.
// 만약, 상이라면 0번은 통째로 계산, 2, 3은 하나를 추가해서 계산을 진행해야 함.
// 가장 최상의 경우만 증가하는 방향으로 이동함.
// 종료조건은 몸을 늘인 공간이 없거나, 먹을 수 있는 양분이 0이하면 더이상 몸을
// 늘리지 않음. start는 홀수(위, 왼쪽), end는 짝수(아래, 오른쪽)일 때 갱신됨.
ll problemSolving(ll n, pair<ll, ll> start, pair<ll, ll> end) {
  pair<ll, ll> tmp;  // 첫번째 값은 0,1,2,3 / 두번째 값은 최대값.
  amount am;
  ll ret = 0;
  tmp.first = 0;
  tmp.second = 0;

  while (true) {
    tmp.first = 0;
    for (int i = 0; i < 4; i++) {
      if (tmp.second < t[i].s) {
        tmp.first = i;
        tmp.second = t[i].s;
      }
    }
    // 종료조건 추가 필요.
    // 종료조건 1. 0이하인 조건
    if (tmp.second == 0)
      break;
    else
      ret += t[tmp.first].s;
    // 계산 필요
    if (tmp.first <= 1)  // 위 아래.
    {
      // 위, 아래는 init 을 통해서 다시 할당이 필요.
      am.start = {t[tmp.first].start.first + coord[tmp.first].first,
                  t[tmp.first].start.second + coord[tmp.first].second};
      am.end = {t[tmp.first].end.first + coord[tmp.first].first,
                t[tmp.first].end.second + coord[tmp.first].second};
	  init(t[tmp.first], am.start, am.end);
	  //좌우의 경우 또한 갱신이 필요함.
    } else {
    }
  }
}

int main() {
  ll n;
  pair<ll, ll> start = {n / 2, n / 2};
  pair<ll, ll> end = {n / 2 + 1, n / 2 + 1};
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> N[i][j];
    }
  }
  // 상하좌우
  init(t[0], {start.first - 1, start.second}, {start.first - 1, end.second});
  init(t[1], {end.first + 1, start.second}, {end.first + 1, end.second});
  init(t[2], {start.first, start.second - 1}, {end.first, start.second - 1});
  init(t[3], {start.first, end.second + 1}, {end.first, end.second + 1});
  // 문제 풀이 시작
}
