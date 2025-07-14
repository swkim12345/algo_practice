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
ll arr[3];
ll answer;
int main() {
  string N;
  ll K;
  bool t = false;
  cin >> N >> K;
  for (int i = 0; i < K; i++) {
    cin >> arr[i];
  }
  sort(arr, arr + K);
  // 첫번재부터 순회하면서, 모두다 같으면 같거나 작은거, 하나라도 작은게
  // 선택되면 그 후부터는 무조건 큰 것도 가능.
  for (auto i = N.begin(); i != N.end(); ++i) {
	answer *= 10;
    for (int j = K - 1; j >= 0; j--) {
      if (t) {
        answer += arr[j];
		break;
      } else if (arr[j] == *i - '0') {
        answer += arr[j];
		break;
      } else if (arr[j] < *i - '0') {
        answer += arr[j];
        t = true;
		break;
	  }
    }
  }
  cout << answer << endl;
}
