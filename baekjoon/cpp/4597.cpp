/**
 * 패리티
 * 1의 개수를 세서 짝수인 경우 e, 홀수는 o, 마지막이 지워져 있음 => 4가지 경우의
 * 수
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
  string tmp;
  while (true) {
    ll one_count = 0;
    cin >> tmp;
    if (!tmp.compare("#")) break;
    for (auto iter = tmp.begin(); iter != tmp.end(); ++iter) {
      if (*iter == 'e') {
        if (one_count % 2)
          cout << "1";
        else
          cout << "0";
		  break;
      } else if (*iter == 'o') {
        if (one_count % 2)
          cout << "0";
        else
          cout << "1";
		  break;
      } else if (*iter == '1')
        one_count++;
      cout << *iter;
    }
    cout << "\n";
  }
}
