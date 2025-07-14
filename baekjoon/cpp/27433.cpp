/**
 * 팩토리얼 2
 * 
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

ll factorial(ll n) {
	if (n == 0)
		return (1);
	return n * factorial(n - 1);
}

int main(){
	ll in;
	cin >> in;
	cout << factorial(in) << endl;
}
