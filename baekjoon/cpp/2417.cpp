/**
 * 정수 제곱근
 * q**2 >= n인 가장 작은 음이 아닌 정수 q를 출력하는 문제
 * 1. 간단하게 1부터 테스트하는 방법 -> 시간 초과날 가능성이 높아보임 =>
 * 시간초과
 * 2. 이진탐색 => 숫자를 2로 나눈 다음, 계속 쪼개가면서 찾아가기.
 * 이때, q**2 >= n인 가장 작은 음이 아닌 정수를 찾는 것이므로, q**2 < n인 가장
 * 큰 정수 + 1을 하자.
 */

#include <iostream>
using namespace std;
int main() {
  unsigned long long tmp, ret, low, high;
  cin >> tmp;
  // 0일 때 알고리즘이 작동하지 않음
  if (tmp == 0) {
    cout << 0 << endl;
    return (0);
  }
  // 들어가기 전 2의 제곱꼴로 대략 구하기, 2**32의 제곱은 2 **64이므로 오버플로우가 발생한다 -> 조심해서 처리하자.
  for (high = 1; high <= (long long)INT32_MAX + 1; high *= 2) {
    if (high * high >= tmp) break;
  }
  low = 0;
  while (true) {
    ret = (high + low) / 2;
    long long ret2 = ret * ret;

    if (ret2 >= tmp) {
      long long ret21 = (ret - 1) * (ret - 1);
      if (ret21 < tmp) {
        cout << ret << endl;
        break;
      } else
      {
        high = ret - 1;
      }
    } else {  //ret2 < tmp
      low = ret + 1;
    }
  }
}
