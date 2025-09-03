/**
 * 서강그라운드
 * 전형적인 다익스트라 문제.
 * 자기 자신의 것도 얻을 수 있으니 주의하자.
 * visit은 방문하지 않은 경우 INF, 방문하면 임의의 숫자로.
 * 벡터 pair를 이용해 방문한 곳을 지워나가면서 가자.
 * 인접리스트를 이용해 문제를 해결하자.
*/
#include <iostream>
#include <queue>
#define INF 1000001
using namespace std;

typedef long long ll;

vector<pair<ll, ll>> node[101];

ll visit[101]; //초기화를 inf로 해야한다.

int main()
{
	ll n, m, r;
	cin >> n >> m >> r;
	
}
