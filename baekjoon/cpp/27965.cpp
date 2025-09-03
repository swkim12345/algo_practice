/**
 * N결수
 */

#include <cstdlib>
#include <iostream>
#include <cmath>
using namespace std;
typedef long long ll;

int main() {
  ll N, K, remainder = 0;
  cin >> N >> K;
  for (ll i = 1; i <= N; i++) {
    remainder =
        (remainder * (ll)pow(10, to_string(i).length()) + i) % K;
  }
  cout << remainder << endl;
}
