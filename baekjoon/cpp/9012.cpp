/**
 * 괄호
 * 원래 의도는 스택을 이용해 push, pop을 이용해 구현하는 것을 원한다.
 * 동일하게 구현.
 */

#include <algorithm>
#include <deque>
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

deque<char> bracket;

int main() {
  init();
  ll N;
  string in;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> in;
    bracket.clear();
    auto iter = in.begin();
    for (; iter != in.end(); ++iter) {
      if (*iter == '(') {
        bracket.push_back(*iter);
      } else {
        if (bracket.empty()) {
          break;
        }
        bracket.pop_back();
      }
    }
    if (iter == in.end() && bracket.empty()) {
      cout << "YES" << endl;
    } else {
      cout << "NO" << endl;
    }
  }
}
