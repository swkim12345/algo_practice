/**
 * 끝말 잇기
 * 끝말 잇기인데, 문제는 문자열이 하나만 들어올 수도 있다.
 * 비교할 문자열 또한 하나만 들어올 수 있어요; 맞냐고요.
 */

#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;
#define total 100000
ll sum_N[total + 1];

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

vector<string> input;
vector<string> cmp;

int main() {
  string tmp;
  ll i, quest_mark;
  cin >> i;
  while (i--) {
    cin >> tmp;
    if (tmp.compare("?") == 0) quest_mark = i - 1;
    input.push_back(tmp);
  }
  cin >> i;
  while (i--) {
    cin >> tmp;
    cmp.push_back(tmp);
  }
  // 1. 문자열 전체 비교
  // 2. 조건에 따라 문자 앞 뒤를 비교
  // 2.1. i = 0일때는 앞은 비교하지 않음.
  // 2.2 i = length - 1일 경우에는 뒤는 비교하지 않음.
  for (auto iter = cmp.begin(); iter != cmp.end(); iter++) {
    i = 0;
    while (i < input.size()) {
      if (input[i++].compare(*iter) == 0) {
        cmp.erase(iter);
      }
    }
  }
  string front, back;
  bool isTrue = true;
  if (i == 0)
    front = nullptr;
  else
    front = input[i - 1];
  if (i == cmp.size() - 1)
    back = nullptr;
  else
    back = input[i + 1];
  // 2. 조건에 따라 문자 앞 뒤를 비교
  for (auto iter = cmp.begin(); iter != cmp.end(); i++) {
    if (front.size() && front.at(front.size() - 1) != (*iter).at(0))
      isTrue = false;
    if (back.size() && back.at(0) != (*iter).at((*iter).size() - 1))
      isTrue = false;
    if (isTrue) {
      cout << *iter << endl;
      break;
    }
  }
}
