/**
 * 부동산 경매
 * 연속된 집만 살 수 있다 && 입력의 끝이 개수가 정해지지 않음.
 * 연속된 집을 살 때, 1,000,000을 초과되기 전까지 계산한 다음,
 * 그 전까지의 값을 정답 테이블에 담고 입력값에 맞게 출력만 진행한다.
 */

#include <algorithm>
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

#define MAX_VALUE 1000000

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll N[MAX_VALUE + 1];

void solved() {
  ll index = 1, start, add_index;
  while (true) {
    if (index % 2)
      start = ((index - 1) / 2) * index + index * 2;
    else
      start = (index / 2) * (index + 1) + index;
    if (start > MAX_VALUE) break;
    for (ll i = 0;; i++) {
      add_index = start + index * i;
      if (add_index > MAX_VALUE) break;
      N[add_index]++;
    }
    index++;
  }
}

int main() {
  init();
  solved();
  ll tmp;
  while (true) {
    cin >> tmp;
    if (tmp == 0) break;
    cout << N[tmp] << "\n";
  }
}
