/**
 * 부분 수열의 합
 * 수열 s가 주어질 때 이의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를
 * 구하는 프로그램을 작성하시오 20 이하이므로 하나씩 테스트해 진행 각각의 수열을
 * 선택하는 거는 true, false 두가지로 표현할 수 잇음.
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

bool b[20];
ll in[20];
bool sum[2000000];

inline void calSum(int end) {
  ll N = 0;

  for (int i = 0; i < end; i++) {
    if (b[i] == true) N += in[i];
  }
  sum[N] = true;
}

inline void setTrigger(int current, int end) {
  if (current == end) {
    calSum(end);
    return;
  }
  b[current] = !b[current];
  setTrigger(current + 1, end);
  b[current] = !b[current];
  setTrigger(current + 1, end);
}

int main() {
  init();
  ll N;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> in[i];
  }
  setTrigger(0, N);
  for (int i = 1; i <= 2000000; i++) {
    if (sum[i] == false) {
      cout << i << endl;
      return (0);
    }
  }
}
