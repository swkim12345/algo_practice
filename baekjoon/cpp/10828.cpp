/**
 * 스택
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

deque<ll> stk;

inline void push(ll X) { stk.push_back(X); }

inline void pop(void) {
  if (stk.empty()) {
    cout << -1 << endl;
  } else {
    cout << stk.back() << endl;
    stk.pop_back();
  }
}

inline void size(void) { cout << stk.size() << endl; }

inline void empty(void) {
  if (stk.empty())
    cout << 1 << endl;
  else
    cout << 0 << endl;
}

inline void top(void) {
  if (stk.empty()) {
    cout << -1 << endl;
  } else {
    cout << stk.back() << endl;
  }
}

int main() {
  init();
  ll N, in;
  string tmp;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    if (tmp.compare("push") == 0) {
      cin >> in;
      push(in);
    } else if (tmp.compare("pop") == 0) {
      pop();
    } else if (tmp.compare("size") == 0) {
      size();
    } else if (tmp.compare("empty") == 0) {
      empty();
    } else if (tmp.compare("top") == 0) {
      top();
    }
  }
}
