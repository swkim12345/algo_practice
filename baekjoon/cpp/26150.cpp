/**
 * Identify, Sort, Index, Solve
 */

#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <cctype>
typedef long long ll;
using namespace std;

struct item {
  string s;
  ll I, D;
  item(string s, ll I, ll D) : s(s), I(I), D(D) {}
  bool operator<(const item i) const { return I > i.I; }
};
priority_queue<item> N;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

string index(struct item i)
{
  return string(1, toupper(i.s.at(i.D - 1)));
}

int main() {
  ll n, i, d;
  string str;
  init();
  cin >> n;
  for (int j = 0; j < n; j++) {
    cin >> str >> i >> d;
    N.push(item(str, i, d));
  }
  str = "";
  while (!N.empty())
  {
    str.append(index(N.top()));
    N.pop();
  }
  cout << str << endl;
}
