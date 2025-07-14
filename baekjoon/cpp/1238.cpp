/**
 * 파티
 * 다익스트라 알고리즘이지만, 약간 다르다.
 * 1. 단방향인 길이다.
 * 2. 가장 많은 시간이 걸린 사람을 구하는 문제이다.
 * 3. 집에서 X로 가야하고, X에서 집으로 왕복해야 하는 문제이다.
 * 
 * 다만, 일반적인 다익스트라와 다르게 all to one 다익스트라 구현이 필요하다.
 * 방향이 없는 그래프의 경우 one to all 과 all to one이 동일하지만, 여기서는 방향그래프이기 때문에 다르다.
 * 어떻게 하느냐 -> 방향을 정반대로 해서 다익스르다를 진행하면, 다른 노드로부터 오는 all to one 을 한것과 동일한 결과를 나오게 할 수 있다.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;
typedef long long ll;

#define VERTEX_MAX 1001

vector<pair<ll,ll> > graph[VERTEX_MAX];
vector<pair<ll,ll> > reverse_graph[VERTEX_MAX];
ll distFromX[VERTEX_MAX];
ll distToX[VERTEX_MAX];

void dijkstra(ll start, ll dist[], vector<pair<ll,ll> > graph[])
{
	priority_queue<pair<ll,ll>, vector<pair<ll,ll> >, greater<pair<ll, ll> > > pq;
	fill(dist, dist + VERTEX_MAX, LLONG_MAX);
	dist[start] = 0;
	pq.push({0, start}); //먼저 들어가야지 제대로 greater than이 작동함.
	while (!(pq.empty()))
	{
		auto [cost, node] = pq.top();
		pq.pop();
		if (cost > dist[node]) continue;
		for (auto &[next, weight] : graph[node])
		{
			dist[next] = min(dist[next], weight + cost);
			pq.push({cost + weight, next}); //more optimize
		}
	}
}

int main()
{
	ll n, m, x;
	cin >> n >> m >> x;

	for (int i = 0; i < m; i++)
	{
		ll a, b, c;
		cin >> a >> b >> c;
		graph[a].push_back({b, c});
		reverse_graph[b].push_back({a, c});
	}
	dijkstra(x, distFromX, graph);
	dijkstra(x, distToX, reverse_graph);

	ll ret = 0;
	for (int i = 1; i <= n; i++) //plz check equal
	{
		if (distFromX[i] != LLONG_MAX && distToX[i] != LLONG_MAX)
			ret = max(ret, distFromX[i] + distToX[i]);
	}
	cout << ret << endl;
}
