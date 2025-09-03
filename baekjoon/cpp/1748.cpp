/**
 * 수 이어 쓰기 1
 * 1부터 N까지 이어쓰면 몇자리일까?
 * 1의 자리 - 1, 10의 자리 - 2....
 */

#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
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

  ll result = 0;
  ll size = 1, before_tmp = 1, tmp = 10;
  ll N;
  cin >> N;
  // 자리수를 바탕으로 반복해서 받음
  while (tmp <= N) {
    result += (tmp - before_tmp) * size;
    size++;
    before_tmp = tmp;
    tmp *= 10;
  }
  cout << result + (N - before_tmp + 1) * size << endl;
}
