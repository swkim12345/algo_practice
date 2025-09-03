/**
 * 거스름돈
 */

#include <algorithm>
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

ll check_minus(ll *n, ll check)
{
	ll ret = 0;
	while (*n - check >= 0)
	{
		*n -= check;
		ret += 1;
	}
	return (ret);
}

int main()
{
	ll N, ret;
	cin >> N;
	ret = 0;
	N = 1000 - N;
	ret += check_minus(&N, 500);
	ret += check_minus(&N, 100);
	ret += check_minus(&N, 50);
	ret += check_minus(&N, 10);
	ret += check_minus(&N, 5);
	ret += check_minus(&N, 1);
	cout << ret << endl;
}
