/**
 * 회사 문화 1
 * 상사가 자신의 번호보다 작음 -> 작은 번호부터 업데이트를 진행하면 됨.
 * 
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

ll employ[100001];
ll hometee[100001];

int main() {
	ll N, M, tmp1, tmp2;
	cin >> N >> M;
	//사원은 1부터 시작
	for (int i = 1; i <= N; i++) {
		cin >> tmp1;
		employ[i] = tmp1;
	}
	for (int i = 0; i < M; i++) {
		cin >> tmp1 >> tmp2;
		hometee[tmp1] += tmp2;
	}
	//사장은 칭찬을 받지 않으므로, 사원부터 시작하면서 체크를 하게 됨.
	for (int i = 2; i <= N; i++) {
		hometee[i] += hometee[employ[i]];
	}
	for (int i = 1; i <= N; i++) {
		cout << hometee[i] << " ";
	}
	cout << endl;
	return (0);
}
