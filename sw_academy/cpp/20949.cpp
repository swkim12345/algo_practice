/**
 * 단기 출장2
 * RMQ(Range Minimum Query) 중 업데이트가 가능한 문제
 * segment tree를 이용해 구하자.
 * 기간중 최대값을 구하는 문제이다.
 * 단기 출장(15841)문제와 유사함.
 */

#include <cstring>
#include <iostream>
// #include <bits/stdc++.h>

#define total 800000

typedef long long ll;

using namespace std;

struct Node {
  ll low, high;
  ll max;
};

ll profit[total];
struct Node node[total];

void init_segment(ll num, ll low, ll high) {
  node[num].low = low;
  node[num].high = high;
  if (low == high) {
    node[num].max = profit[low];
  } else {
    ll mid = (low + high) / 2;
    init_segment(num * 2, low, mid);
    init_segment(num * 2 + 1, mid + 1, high);
    node[num].max = max(node[num * 2].max, node[num * 2 + 1].max);
  }
}

ll pull_up(ll org_num) {
  return (max(node[org_num * 2].max, node[org_num * 2 + 1].max));
}

void update_segment(ll num, ll date, ll update) {
  ll mid, ret;

  mid = (node[num].low + node[num].high) / 2;
  if (node[num].low == node[num].high)  // leaf node
  {
    node[num].max = update;
  } else {
    if (date <= mid) {
      update_segment(num * 2, date, update);
    } else {
      update_segment(num * 2 + 1, date, update);
    }
    node[num].max = pull_up(num);
  }
}

ll search(ll num, ll low, ll high) {
  ll left_child_ret = 0, right_child_ret = 0;
  ll mid;

  mid = (node[num].low + node[num].high) / 2;
  if (node[num].low == low && node[num].high == high)
    return (node[num].max);
  else {
    if (low <= mid) {
      left_child_ret = search(num * 2, low, min(high, mid));
    }
    if (high > mid) {
      right_child_ret = search(num * 2 + 1, max(low, mid + 1), high);
    }
    return (max(left_child_ret, right_child_ret));
  }
}

int main() {
  ios_base::sync_with_stdio(false);  // buffering false
  cin.tie(NULL);
  cout.tie(NULL);
  freopen("input.txt", "r", stdin);
  ll cas, N, Q, ret;
  cin >> cas;
  for (int h = 1; h <= cas; h++) {
    ret = 0;
    cin >> N >> Q;
    for (int i = 1; i <= N; i++) {  // 1일부터 시작
      cin >> profit[i];
    }
    init_segment(1, 1, N);
    for (int i = 0; i < Q; i++) {
      ll i1, i2, i3;
      cin >> i1 >> i2 >> i3;
      if (i1 == 1) {
        ret += search(1, i2, i3);
      } else {
        update_segment(1, i2, i3);
      }
    }
    cout << "#" << h << " " << ret << std::endl;
    memset((void *)profit, 0, sizeof(ll) * total);
    memset((void *)node, 0, sizeof(Node) * total);
  }
  return (0);
}
