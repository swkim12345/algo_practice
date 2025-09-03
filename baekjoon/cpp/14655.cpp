/**
 * 육제는 도박쟁이야!! - 실버 5
 * 동전을 뒤집어서 안보이게 세팅할 수 있다.
 * 한번에 3개를 뒤집어야 한다.
 * 다만, 횟수 제한이 없고, 양끝의 동전을 1개, 2개만 뒤집는 것도 가능해
 * 실질적으로 1개를 모든 동전에 대해 뒤집을 수 있다.
 * 첫 라운드에서는 동전의 합이 최대가 되게, 두번째 라운드에서는 동전의 합이
 * 최소가 되게 만든다. 두개 모두 절대값 기호를 사용해 양수로 만들어주고,
 * 합해주면 된다.
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

int main() {
  init();
  ll N, tmp;
  ll first_sum = 0, second_sum = 0;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    first_sum += abs(tmp);
  }
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    second_sum += abs(tmp);
  }
  cout << first_sum + second_sum << endl;
}
