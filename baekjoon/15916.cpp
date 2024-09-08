/**
 * 가희는 그래플러야
 * 하나만이라도 만나면 됨.
 * y=x를 지나는 게 하나라도 있으면 T, 아니면 F
 * 값을 입력해서 크기가 반대로 되거나, y=x꼴이 되면 T.
 */

#include <algorithm>
#include <cmath>
#include <iostream>
#include <set>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

vector<ll> values;

int main() {
  ll n, tmp, k;
  bool sign;  // true - +
  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> tmp;
    values.push_back(tmp);
  }
  cin >> k;
  // 답 구하기
  if (values[0] - k * 1 == 0) {
    cout << "T" << "\n";
    return (0);
  } else if (values[0] - k * 1 > 0)
    sign = true;
  else
    sign = false;
  for (int i = 1; i < n; i++) {
    tmp = values[i] - k * (i + 1);
    if ((tmp < 0 && sign) || (tmp > 0 && !sign) || tmp == 0) {
      cout << "T" << "\n";
      return (0);
    }
  }
  cout << "F" << "\n";
  return (0);
}
