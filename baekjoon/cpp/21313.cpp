/**
 * 문어
 * 손의 순서는 상관 없음
 * 1 2 1 2 ...로 가다가 마지막에 홀수이면 1번이 이미 써지고 있으니깐 3으로
 * 종결되어야 함. 마지막이 짝수면 2로 끝나면 되고요.
 */
#include <iostream>
using namespace std;

int main() {
  long long n, tmp;
  tmp = 1;
  cin >> n;
  for (int i = 0; i < n; i++) {
    if (i == n - 1 && i % 2 == 0) {
      cout << 3;
	  break ;
    }
    cout << tmp << " ";
    tmp = tmp % 2 + 1;
  }
}
