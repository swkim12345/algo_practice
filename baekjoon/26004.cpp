/**
 * HI-ARC
 */

#include <algorithm>
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

ll tmp[5];

int main() {
  ll t;
  cin >> t;
  string in;
  cin >> in;
  for (auto iter = in.begin(); iter != in.end(); ++iter) {
    if (*iter == 'H')
      tmp[0]++;
    else if (*iter == 'I')
      tmp[1]++;
    else if (*iter == 'A')
      tmp[2]++;
    else if (*iter == 'R')
      tmp[3]++;
    else if (*iter == 'C')
      tmp[4]++;
  }
  sort(tmp, tmp + 5);
  cout << tmp[0] << endl;
}
