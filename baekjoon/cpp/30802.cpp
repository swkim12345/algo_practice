/**
 * 웰컴 키트
 * 브론즈 3
 * 단순 구현.
 * 티셔츠의 경우 나눗셈을 한 수에 올림함.
 * 볼펜의 경우 전체 합을 나눈 수를 내림한 수를 출력, 나머지를 출력한다.
 */

#include <algorithm>
#include <iostream>
#include <vector>
#include <cmath>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll N, tshirt[6], T, P;
ll tshirt_sum = 0;
void solved() {
  for (int i = 0; i < 6; i++) {
    tshirt_sum += ceil((double)tshirt[i] / (double)T);
  }
  cout << tshirt_sum << endl;
  cout << N / P << " " << N % P << endl;
}

int main() {
  cin >> N;
  for (int i = 0; i < 6; i++) {
    cin >> tshirt[i];
  }
  cin >> T >> P;
  solved();
  return (0);
}
