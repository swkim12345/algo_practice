/**
 * 단기 출장
 * RMQ(Range Minimum Query)중 업데이트가 가능한 문제
 * segment tree 를 이용해서 구하자.
 * 여기서는 이익의 최대값을 구하는 문제이므로, 최대값을 저장하자.
 */

#include <iostream>
#include <cstring>
//#include <bits/stdc++.h>

#define total 800000

typedef long long ll;

using namespace std;

struct Node {
  ll low;
  ll high;
  ll total_profit;
};

ll profit[total];
struct Node node[total];

void init_segment(ll num, ll low, ll high) {
  node[num].low = low;
  node[num].high = high;
  if (low == high)  // 하나만 있는 경우
  {
    node[num].total_profit = profit[low];
  } else  // 왼쪽, 오른쪽을 찾아 max_profit을 더함.
  {
    ll mid = (low + high) / 2;
    init_segment(num * 2, low, mid);
    init_segment(num * 2 + 1, mid + 1, high);
    node[num].total_profit = node[num * 2].total_profit + node[num * 2 + 1].total_profit;
  }
}
/**
 * date : update될 날짜.
 * num : 노드 번호
 * update : 업데이트될 숫자
 * return : 업데이트 된 뒤 적용해야 할 숫자.
 */
ll update_segment(ll num, ll date, ll update) {
  ll mid;
  ll ret;

  mid = (node[num].low + node[num].high) / 2;
  if (node[num].low == node[num].high) {
    ret = update - node[num].total_profit;
    node[num].total_profit = update;
    return (ret);
  } else {
    if (date <= mid)  // 왼쪽 child 참색
    {
      ret = update_segment(num * 2, date, update);
    } else {
      ret = update_segment(num * 2 + 1, date, update);
    }
    node[num].total_profit += ret;
    return (ret);
  }
}

ll search(ll num, ll low, ll high) {
  ll left_child_ret = 0, right_child_ret = 0;
  ll mid;

  mid = (node[num].low + node[num].high) / 2;
  if (node[num].low == low && node[num].high == high)
    return (node[num].total_profit);
  else {
    if (low <= mid) {
      left_child_ret = search(num * 2, low, min(high, mid));
    }
    if (high > mid) {
      right_child_ret = search(num * 2 + 1, max(low, mid + 1), high);
    }
    return (left_child_ret + right_child_ret);
  }
}

int main() {
  ios_base::sync_with_stdio(false); //buffering false
  cin.tie(NULL);
  cout.tie(NULL);
  //freopen("input.txt", "r", stdin);
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
      if (i1 == 1)
      {
        ret += search(1, i2, i3);
      } else
      {
        update_segment(1, i2, i3);
      }
    }
    cout << "#" << h << " " << ret << std::endl;
    memset((void *)profit, 0, sizeof(ll) * total);
    memset((void *)node, 0, sizeof(Node) * total);
  }
  return (0);
}
