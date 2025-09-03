/**
 * 1로 만들기
 * 여기에선 배열 인덱스 : 정수의 값
 * dp의 경우 n = min(n / 3 + 1, n / 2 + 1, n - 1 + 1, n)이다.
 * 1로 만드는 것이니깐 기준이 되는 1 인덱스의 경우는 0으로 초기화.
 */

#include <climits>
#include <iostream>
typedef long long ll;
#define total 1000001
using namespace std;
ll N[total];

int main() {
  ll X;
  cin >> X;
  // 0 - 0 / 1 - 0로 초기화
  N[0] = 0;
  N[1] = 0;
  for (int i = 2; i <= X; i++) {
    N[i] = LLONG_MAX;
  }
  for (int i = 1; i <= X; i++) {
    N[i] = min(N[i], N[i - 1] + 1);
	if (i % 3 == 0)
	{
		N[i] = min(N[i], N[i / 3] + 1);
	}
	if (i % 2 == 0)
	{
		N[i] = min(N[i], N[i / 2] + 1);
	}
  }
  cout << N[X];
}
