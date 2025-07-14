/**
 * 숫자 카드놀이
 */

#include <iostream>
using namespace std;

int solved(long long N) {
  long long ret;
  while (true) {
    cout << N << " ";
    if (N / 10 == 0) return 0;
    ret = 1;
    while (N) {
      ret *= N % 10;
      N /= 10;
    }
    N = ret;
  }
  cout << endl;
  return 1;
}

int main() {
  long long N;
  while (true) {
    cin >> N;
    if (N == 0) break;
    solved(N);
  }
}
