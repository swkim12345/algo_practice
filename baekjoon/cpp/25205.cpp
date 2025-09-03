/**
 * 경로당펑크 2077
 * 노가다 on
 */

#include <algorithm>
#include <iostream>
#include <queue>
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

char c[] = {'q', 'w', 'e', 'r', 't', 'a', 's',
            'd', 'f', 'g', 'z', 'x', 'c', 'v'};

int main() {
  ll N;
  string s;
  cin >> N >> s;
  for (int i = 0; i < 14; i++) {
    if (s[N - 1] == c[i]) {
      cout << "1\n";
      return 0;
    }
  }
  cout << "0\n";
  return 0;
}
