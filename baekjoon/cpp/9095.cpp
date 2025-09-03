/**
 * 1,2,3 구하기
 * f(n) = f(n - 1) + f(n-2) + f(n-3)로 구현할 수 있다.(앞뒤로 올 수 있음)
 * dp가 잘 안 풀릴 때에는 하나씩 써보자. 그럼 답 나올수도.
 */

#include <algorithm>
#include <iostream>
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

ll n[11];

void solved() {
	ll tmp;
	cin >> tmp;
	cout << n[tmp] << endl;
}

int main() {
	init();
	n[0] = 0;
	n[1] = 1;
	n[2] = 2;
	n[3] = 4;
	for (int i = 4; i < 11; i++) {
		n[i] = n[i - 1] + n[i - 2] + n[i - 3];
	}
	ll T;
	cin >> T;
	while (T--){
		solved();
	}
}
