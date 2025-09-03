/**
 * 직사각형 네개의 합집합 면적 구하기
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

ll arr[101][101];

int main() {
  init();
  ll low_x, low_y, high_x, high_y, sum = 0;
  for (int i = 0; i < 4; i++) {
    cin >> low_x >> low_y >> high_x >> high_y;
    for (int x = low_x; x < high_x; x++) {
      for (int y = low_y; y < high_y; y++) {
        arr[x][y] = 1;
      }
    }
  }
  for (int x = 1; x <= 100; x++) {
    for (int y = 1; y <= 100; y++) {
      if (arr[x][y] == 1) sum += 1;
    }
  }
  cout << sum << endl;
}
