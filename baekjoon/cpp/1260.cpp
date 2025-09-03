/**
 * DFS와 BFS
 * dfs로 방문한 노드와 bfs로 방문한 노드에 대해 각각 출력하는 과제
 * 인접 리스트, stack / queue를 사용해 구현하자.
 * 또한, 방문한지 안 한지를 알기 위해 배열을 만들고, 저장하자.
 */

#include <iostream>
#include <queue>
#include <stack>
#include <vector>
typedef long long ll;
using namespace std;
#define total 1001
ll dfs_visited[total];
ll bfs_visited[total];
priority_queue<ll> dfs_vector[total];
priority_queue<ll, vector<ll>, greater<ll> > bfs_vector[total];

void dfs(ll V) {
  ll tmp;
  stack<ll> stc;

  // dfs 결과 출력
  dfs_visited[V] = 1;
  while (!dfs_vector[V].empty()) {
    stc.push(dfs_vector[V].top());
    dfs_vector[V].pop();
  }
  cout << V << " ";
  while (!stc.empty()) {
    tmp = stc.top();
    stc.pop();
    if (dfs_visited[tmp] == 0) {
      while (!dfs_vector[tmp].empty()) {
        stc.push(dfs_vector[tmp].top());
        dfs_vector[tmp].pop();
      }
      dfs_visited[tmp] = 1;
      cout << tmp << " ";
    }
  }
  cout << endl;
}

void bfs(ll V) {
  ll tmp;
  queue<ll> que;

  bfs_visited[V] = 1;
  while (!bfs_vector[V].empty()) {
    que.push(bfs_vector[V].top());
    bfs_vector[V].pop();
  }
  cout << V << " ";
  while (!que.empty()) {
    tmp = que.front();
    que.pop();
    if (bfs_visited[tmp] == 0) {
      while (!bfs_vector[tmp].empty()) {
        que.push(bfs_vector[tmp].top());
        bfs_vector[tmp].pop();
      }
      bfs_visited[tmp] = 1;
      cout << tmp << " ";
    }
  }
}

int main() {
  ll N, M, V, in, out, tmp;
  cin >> N >> M >> V;
  for (int i = 0; i < M; i++) {
    cin >> in >> out;
    dfs_vector[in].push(out);
    dfs_vector[out].push(in);
    bfs_vector[in].push(out);
    bfs_vector[out].push(in);
  }
  dfs(V);
  bfs(V);
}
