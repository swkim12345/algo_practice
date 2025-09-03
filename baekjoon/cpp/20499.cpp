/**
 * Darius님 한타 안 함?
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

int main() {
  string input;
  ll kda[3];
  cin >> input;
  string separator = "/";
  int cur_position = 0;
  int position;
  int i = 0;

  while (i < 3) {
    position = input.find(separator, cur_position);
    int len = position - cur_position;
    string result = input.substr(cur_position, len);
    kda[i++] = atol(result.c_str());
    cur_position = position + 1;
  }
  if (kda[0] + kda[2] < kda[1] || kda[1] == 0) cout << "hasu\n";
  else cout << "gosu\n";
}
