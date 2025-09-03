/**
 * 골뱅이 찍기 - ㅁ
 * 브론즈 3
 * ㅁ자 모양은 5개의 셀로 구성되어 있음.
 * 가로의 길이 = 5 * N - 2 * N => 3 * N만큼 N개 3 * N개 N개 출력
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
  ll N;
  cin >> N;
  for (int i = 1; i <= 5 * N; i++) {
    for (int j = 1; j <= 5 * N; j++) {
      if (i <= N || i > 4 * N) {
        cout << "@";
      } else if (j <= N || j > 4 * N) {
        cout << "@";
      } else {
        cout << " ";
      }
    }
    cout << "\n";
  }
}
