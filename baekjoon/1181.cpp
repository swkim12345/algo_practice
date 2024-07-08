/**
 * 단어 정렬
 * set 자료구조를 통해 정렬 후 도출
 */
#include <algorithm>
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

bool cmp_string(const string &a, const string &b) {
  ll a_len = a.length();
  ll b_len = b.length();
  if (a_len == b_len)
    return(a < b);
  else
    return (a_len < b_len);
}
set<string, decltype(cmp_string) *> tmp(cmp_string);

int main() {
  ll N;
  string in;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> in;
    tmp.insert(in);
  }
  for (auto iter = tmp.begin(); iter != tmp.end(); ++iter) {
    cout << *iter << endl;
  }
}
