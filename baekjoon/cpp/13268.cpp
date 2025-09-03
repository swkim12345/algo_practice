/**
 * 셔틀런
 * 반으로 폴딩해서 계산하는 방식을 도입해 구현한다.
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

int main() {
  ll N, lounge, counter = 1, position = 0;

  cin >> N;
  N %= 100;
  if (N <= 10) {
    if (N >= 5) N = 10 - N;
  } else if (N <= 30) {
    N = (N - 10);
    if (N >= 10) N = 20 - N;

  } else if (N <= 60) {
    N = (N - 30);
    if (N >= 15) N = 30 - N;

  } else {
    N = (N - 60);
    if (N >= 20) N = 40 - N;
  }
  if (N == 0)
    cout << 0 << endl;
  else if (N <= 5)
    cout << 1 << endl;
  else if (N <= 10)
    cout << 2 << endl;
  else if (N <= 15)
    cout << 3 << endl;
  else if (N <= 20)
    cout << 4 << endl;
}
