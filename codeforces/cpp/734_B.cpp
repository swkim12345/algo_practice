/*
codeforces
using greedy
*/

#include <iostream>
typedef long long ll;

using namespace std;

int main()
{
	ll k2, k3, k5, k6;
	ll p, q, f, sum;
	std::cin >> k2 >> k3 >> k5 >> k6;
	p = min(k5, k6);
	q = min(p, k2);
	sum = q * 256;
	k2 -= q;
	q = min(k2, k3);
	sum += q * 32;
	cout << sum << endl;
	return (0);
}
