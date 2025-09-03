/**
 * 짐 챙기는 숌
 * 책의 개수가 0개일 수 있음
 * 최대한 많은 곳에 넣어야 함.
 * dp와는 다름 -> 상자의 양은 정해져 있음.
 * greedy -> 책을 하나로 합치는 것이 목표가 됨.
 * 가장 용량이 작은 책과 큰 책을 합치는 것이 목표임
 * 배열을 사용한 다음, 투포인터를 통해 합치는 것을 하자.
 * counting을 하나씩 줄여가면서 하면 되지 않을까.
 */
#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll amount[50];

int main() {
  init();
  ll N, M, ret, start, end;
  cin >> N >> M;
  if (N == 0) {
    cout << 0 << endl;
    return (0);
  }
  ret = 0;
  start = 0;
  end = 0;
  for (int i = 0; i < N; i++) {
    cin >> amount[i];
  }
  while (++end < N) {
    if (amount[start] + amount[end] <= M) {
      amount[start] += amount[end];
    } else {
      start = end;
	  ret++;
    }
  }
  cout << ret << endl;
}
