/**
 * 카드 정렬하기
 * 최소 우선순위큐를 가지고 푸는 문제.
 */
#include <iostream>
#include <queue>
#include <vector>
using namespace std;
typedef long long ll;
priority_queue<ll, vector<ll>, greater<ll> > pq;

ll check_pop() {
  ll ret;

  if (pq.empty()) return (0);
  ret = pq.top();
  pq.pop();
  return (ret);
}

int main() {
  ll N, tmp, a, b, count;
  count = 0;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    pq.push(tmp);
  }
  if (N == 1) {
    cout << 0 << endl;
    return (0);
  }

  while (true) {
    a = check_pop();
    b = check_pop();
    if (b == 0) {  // 하나만 존재할 경우 -> 종료해야 한다.(이미 그 전에 count 에
                   // 저장했으므로 괜찮음.), 다만 이럴경우 N = 1일 경우가 문젠데
      cout << count << endl;
      return (0);
    }
    count += a + b;
    pq.push(a + b);
  }
}
