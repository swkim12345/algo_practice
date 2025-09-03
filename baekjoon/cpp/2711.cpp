/**
 * 오타맨 고창영
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

int main() {
  string in;
  ll N, index;

  cin >> N;
  while (N--) {
    cin >> index >> in;
    for (int i = 0; i < in.length(); i++) {
      if (i == index - 1) continue;
      cout << in[i];
    }
    cout << endl;
  }
}
