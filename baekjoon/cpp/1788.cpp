/**
 * 피보나치 수의 확장
 * 1 미만일때에도 가능하게 해야 한다.
 * n < 0 -> F(n) = F(n + 2) - F(n + 1)
 * n = 0 -> 0
 * n = 1 -> 1
 * n > 1 -> F(n) = F(n - 1) + F(n - 2)
 * 또한 1,000,000,000로 나눈 나머지만 취하므로, 매 연산마다 나머지 연산을
 * 처리한다. c++의 경우 (음수) % (양수) = 음수 가 나오므로 처리할 필요는 없다.
 */

#include <iostream>
using namespace std;
typedef long long ll;
#define total 1000000

ll fibo[total + 2];

void prt(int n) {
  if (fibo[n] < 0) {
    cout << -1;
  } else if (fibo[n] == 0) {
    cout << 0;
  } else {
    cout << 1;
  }
  cout << endl;
  cout << abs(fibo[n]);
}

int main() {
  ll n;
  cin >> n;
  if (n < 0) {
    fibo[total + 1] = 1;
    fibo[total] = 0;
    // 실제 접근 -> total + n -> 값이 되게 됨.
    for (int i = total - 1; i >= total + n; i--) {
      fibo[i] = (fibo[i + 2] - fibo[i + 1]) % 1000000000;
    }
    prt(total + n);
  } else {
    fibo[0] = 0;
    fibo[1] = 1;
    for (int i = 2; i <= n; i++) {
      fibo[i] = (fibo[i - 2] + fibo[i - 1]) % 1000000000;
    }
    prt(n);
  }
}
