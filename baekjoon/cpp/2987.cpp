/**
 * 사과나무
 * 골드 4
 * 단순 구현
 * 삼각형의 넓이는 공식대로 구하면 됨.
 * 삼각형의 내부에 있는 지는 삼각형의 외적을 이용해 구할 수 있다.
 * 세 경우가 모두 >= 혹은 <=인 경우를 카운팅하면 된다.
 */

#include <algorithm>
#include <iomanip>
#include <iostream>
#include <vector>
#include <cmath>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

pair<ll, ll> vc(const pair<ll, ll> &x, const pair<ll, ll> &y) {
  pair<ll, ll> result;

  result.first = x.first - y.first;
  result.second = x.second - y.second;
  return (result);
}

ll outvc(const pair<ll, ll> &org, const pair<ll, ll> &cmp) {
  return (org.first * cmp.second - cmp.first * org.second);
}

int main() {
  init();
  pair<ll, ll> x, y, z, tmp;
  pair<ll, ll> xy, yz, zx, xtmp, ytmp, ztmp;
  ll ret, N, outx, outy, outz;
  double size;

  ret = 0;
  cin >> x.first >> x.second;
  cin >> y.first >> y.second;
  cin >> z.first >> z.second;
  xy = vc(x, y);
  yz = vc(y, z);
  zx = vc(z, x);
  size = fabs(x.first * (y.second - z.second) + y.first * (z.second - x.second) +
         z.first * (x.second - y.second)) / (double)2;
  cout << fixed << setprecision(1) << size << endl;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> tmp.first >> tmp.second;
    xtmp = vc(x, tmp);
    ytmp = vc(y, tmp);
    ztmp = vc(z, tmp);
    outx = outvc(xy, xtmp);
    outy = outvc(yz, ytmp);
    outz = outvc(zx, ztmp);
    if ((outx <= 0 && outy <= 0 && outz <= 0) ||
        (outx >= 0 && outy >= 0 && outz >= 0) ) {
      ret += 1;
    }
  }
  cout << ret << endl;
}
