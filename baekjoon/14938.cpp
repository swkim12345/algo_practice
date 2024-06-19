/**
 * 서강그라운드
 * 전형적인 다익스트라 문제.
 * 자기 자신의 것도 얻을 수 있으니 주의하자.
 * visit은 방문하지 않은 경우 INF, 방문하면 임의의 숫자로.
 * 벡터 pair를 이용해 방문한 곳을 지워나가면서 가자.
*/
#include <iostream>
#include <queue>
#define INF 1000001
using namespace std;

typedef long long ll;

vector<pair<ll, ll>> node;

ll visit[101];

int main()
{
	ll n, m, r;
	//출발, 도착지점을 가진 prioriy queue
	priority_queue<pair<ll, ll>> load;
	cin >> n >> m >> r;
	
}
