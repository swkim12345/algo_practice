/**
 * 구간 합 구하기.
 * 세그먼트 트리를 이용한 문제.
 * 입력값이 문제는 2**63까지라 오버플로우가 발생할 수 있음.
 * 근데 정답이 2**63이하라네; 그럼 뭐 괜찮지.
 */
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

#define total 1000001
// 세그먼트 트리 내부 자료구조
typedef struct s_seg {
  ll high;
  ll low;
  ll sum;
} t_seg;

t_seg *seg_tree;
ll *input;

// high는 제외, low는 포함하는 트리 빌드.
void build(ll target, ll low, ll high) {
  ll avg = (high + low) / 2;

  seg_tree[target].high = high;
  seg_tree[target].low = low;
  // 먼저 자신이 high 와 low가 동일한 지 확인한다.
  if (low + 1 == high) {
    seg_tree[target].sum = input[low];
    return;
  }
  // 아닐 경우, 다음 노드로 먼저 넘긴 다음, 저장되어 있는 값을 가지고 저장한다.
  else {
    build(target * 2, low, avg);
    build(target * 2 + 1, avg, high);
    seg_tree[target].sum =
        seg_tree[target * 2].sum + seg_tree[target * 2 + 1].sum;
  }
}

void update(ll target, ll n, ll value) {
  ll avg = (seg_tree[target].low + seg_tree[target].high) / 2;
  // 원하는 값을 찾았을 때 업데이트
  if (seg_tree[target].low == n && seg_tree[target].high == n + 1) {
    seg_tree[target].sum = value;
    return;
  }
  // 아닌 경우 왼쪽, 혹은 오른쪽을 찾음.
  if (n < avg) {
    update(target * 2, n, value);
  } else {
    update(target * 2 + 1, n, value);
  }
  // 업데이트
  seg_tree[target].sum =
      seg_tree[target * 2].sum + seg_tree[target * 2 + 1].sum;
}

ll sum_seg_tree(ll target, ll low, ll high) {
  ll child_sum = 0;
  ll avg = (seg_tree[target].low + seg_tree[target].high) / 2;
  if (low == high) {
    return (0);
  }
  if (low == seg_tree[target].low && high == seg_tree[target].high)
    return (seg_tree[target].sum);
  if (avg <= low) {
    return (sum_seg_tree(target * 2 + 1, low, high));
  } else if (avg > high) {
    return (sum_seg_tree(target * 2, low, high));
  } else {
    return (sum_seg_tree(target * 2, low, avg) +
            sum_seg_tree(target * 2 + 1, avg, high));
  }
}

int main() {
  seg_tree = new t_seg[total * 4];
  input = new ll[total];

  ll N, M, K, a, b, c;
  cin >> N >> M >> K;
  for (int i = 1; i <= N; i++) {
    cin >> input[i];
  }
  build(1, 1, N + 1);
  for (int i = 1; i <= M + K; i++) {
    cin >> a >> b >> c;
    if (a == 1) {
      update(1, b, c);
    } else if (a == 2) {
      cout << sum_seg_tree(1, b, c + 1) << endl;
      ;
    }
  }
}
