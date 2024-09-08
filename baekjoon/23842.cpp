/**
 * 성냥개비
 * 정답이 여러개 나올 수 있음 => 계산 줄일 수 있다.
 * 1-2, 2-5, 3-5, 4-4, 5-5, 6-6, 7-3,8-7,9-6,0-6
 */
#include <algorithm>
#include <cmath>
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

int amountStick(int tmp) {
  switch (tmp) {
    case 0:
      return 6;
    case 1:
      return 2;
    case 2:
      return 5;
    case 3:
      return 5;
    case 4:
      return 4;
    case 5:
      return 5;
    case 6:
      return 6;
    case 7:
      return 3;
    case 8:
      return 7;
    case 9:
      return 6;
  }
}

int calStickCount(int tmp) {
  return amountStick(tmp / 10) + amountStick(tmp % 10);
}

int main() {
  int left, right, result, N;
  cin >> N;
  N -= 4;
  for (left = 0; left < 50; left++) {
    for (right = left + 1; left + right <= 99; right++) {
      result = left + right;
      if (calStickCount(left) + calStickCount(right) + calStickCount(result) ==
          N) {
        printf("%02d+%02d=%02d\n", left, right, result);
        return (0);
      }
    }
  }
  cout << "impossible" << endl;
}
