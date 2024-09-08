/**
 * e 계산
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

double factorial(long long i) {
  double result = 1;
  while (i) result *= i--;
  return result;
}

int main() {
  double e = 2.5;
  cout << "n e\n" << "- -----------\n";
  cout << "0 1\n" << "1 2\n" << "2 2.5\n";
  for (double i = 3; i <= 9; i++) {
    e += (double)1 / factorial(i);
	cout.precision(9);
    cout << (int)i << " " << fixed << e << "\n";
  }
}
