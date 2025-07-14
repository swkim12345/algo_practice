/**
 * 팰린드롬수
 * 앞에서 하나, 뒤에서 하나 가는 투포인터 문제
 * 1자리 - 무조건 팰린드롬
 * 2자리 - 0, 1이 동일할 때
 * 3자리 - 0, 2가 동일할 때
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
  string tmp;
  ll size, start, end;
  cin >> tmp;
  while (tmp.compare("0")) {
    size = tmp.length();
    start = 0;
    end = size - 1;
    while (start + 1 <= end) {
      if (tmp[start] != tmp[end]) {
        cout << "no" << "\n";
        break;
      }
      start++;
      end--;
    }
    if (start + 1 > end) cout << "yes" << "\n";
    cin >> tmp;
  }
}
