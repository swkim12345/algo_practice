/**
 * 포도주 시식
 * 전형적인 dp 문제
 * 기준점 -> 포도주 양을 최대화함.
 * 자료구조 -> 2차원 배열
 * 경우는 2가지 -> 현재를 선택하느냐 선택하지 않느냐
 * 1. 선택하지 않은 경우 -> 어느 값이든 상관 없음, x - 1의 값이 모든 경우를
 * 포함함 -> 이를 합해서 더함.
 * 2. 선택하는 경우 -> 이전에 0번째, 1번째 선택의 경우의 수를 합해야 함.
 * 	2.1. 선택하는 경우세 2가지가 나뉨 -> 이번째가 1번째인지, 2번째인
 * 따라서 배열은 3가지 경우의 수를 고려해야하고, 음료 선택은 선택, 혹은 현재
 * 선택 안 함으로 고려해야 한다.
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

ll wine[10001];
ll sum_wine[3][10001];

int main() {
  ll N, tmp;
  cin >> N;
  for (ll i = 1; i <= N; i++) {
    cin >> tmp;
    wine[i] = tmp;
  }
  sum_wine[0][1] = wine[1];
  sum_wine[1][1] = wine[1];
  sum_wine[2][1] = 0;
  for (ll i = 1; i <= N; i++) {
    // 현재 와인을 선택하지 않는 경우 -> 이전의 값(x - 1) 어떤 것이든 가능함
    sum_wine[0][i] =
        max(max(sum_wine[0][i - 1], sum_wine[1][i - 1]), sum_wine[2][i - 1]);
    // 현재 와인을 선택하는 경우 -> 두가지 경우로 나뉨.
    sum_wine[1][i] = sum_wine[0][i - 1] + wine[i];
    sum_wine[2][i] = sum_wine[1][i - 1] + wine[i];
  }
  cout << max(max(sum_wine[0][N], sum_wine[1][N]), sum_wine[2][N]) << endl;
}
