/**
 * 큐
 * 큐의 기본적인 명령어를 학습하는 단계
 * 디스패쳐 테이블을 쓰려니 익명함수가 안 되어서 귀찮다... 그냥 하자.
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

deque<ll> queue;

int main() {
  init();
  ll amount, tmp;
  string cmd;
  cin >> amount;
  while (amount--) {
    cin >> cmd;
    if (!cmd.compare("push")) {
      cin >> tmp;
      queue.push_back(tmp);
    } else if (!cmd.compare("pop")) {
      if (queue.size() == 0) {
        cout << -1 << endl;
      } else {
        cout << queue.front() << endl;
        queue.pop_front();
      }
    } else if (!cmd.compare("size")) {
      cout << queue.size() << endl;
    } else if (!cmd.compare("empty")) {
      cout << queue.empty() ? 1 : 0;
      cout << endl;
    } else if (!cmd.compare("front")) {
      if (queue.empty()) {
        cout << -1 << endl;
      } else {
        cout << queue.front() << endl;
      }
    } else if (!cmd.compare("back")) {
      if (queue.empty()) {
        cout << -1 << endl;
      } else {
        cout << queue.back() << endl;
      }
    }
  }
}
