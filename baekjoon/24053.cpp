/**
 * 알고리즘 수업 - 삽입 정렬 3
 * 구현문제.
 * 의사코드를 바탕으로 구현한 다음 이와 비슷한 상황이 발생할 수 있는 지 확인.
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

bool cmp_(ll A[], ll cmp[], ll n) {
  for (int i = 1; i <= n; i++) {
    if (A[i] != cmp[i]) return (false);
  }
  cout << 1 << endl;
  return (true);
}

void insertion_sort(ll A[], ll cmp[], ll N) {
  ll new_item, loc;

  for (int i = 2; i <= N; i++) {
    loc = i - 1;
    new_item = A[i];
    while (1 <= loc && new_item < A[loc]) {
      A[loc + 1] = A[loc];
      loc--;
      if (cmp_(A, cmp, N)) exit(0);
    }
    if (loc + 1 != i) A[loc + 1] = new_item;
    if (cmp_(A, cmp, N)) exit(0);
  }
}

int main() {
  ll N;
  init();
  cin >> N;
  ll *A = new ll[N + 1]; //어떤 머저리가 ()로 배열할당을 해...
  ll *sorted_A = new ll[N + 1];
  for (int i = 1; i <= N; i++) {
    cin >> A[i];
  }
  for (int i = 1; i <= N; i++) {
    cin >> sorted_A[i];
  }
  insertion_sort(A, sorted_A, N);
  cout << 0 << endl;
}
