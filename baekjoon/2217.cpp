/**
 * 로프
 * 그리디, 정렬을 먼저 한 다음, 큰 순서대로 피킹함.
 * 만약, 다음 로프를 추가할 때 견딜 수 있는 중량이 똑같거나, 작으면 답출력후
 * 종료
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

ll arr[100001];

int main() {
  ll N, tmp, result, before_result;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp;
    arr[i] = tmp;
  }
  sort(arr, arr + N, greater<ll>());
  before_result = arr[0];
  for (int i = 1; i < N; i++) {
	result = arr[i] * (i + 1);
	//종료조건
	if (before_result > result) {
		cout << before_result << endl;
		break;
	}
	before_result = result;
  }
}
