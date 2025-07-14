/**
 * New Year Transportation
 */

#include <iostream>

using namespace std;

typedef long long ll;

#define TOTAL 30001

ll N[TOTAL];

int main()
{
	ll n, t;
	cin >> n >> t;
	for (int i = 1; i < n; i++) //n -1까지이므로
	{
		cin >> N[i];
	}
	ll tmp = 1;
	while (tmp < t)
	{
		tmp += N[tmp];
	}
	if (tmp == t)
	{
		cout << "YES" << endl;
	}
	else
	{
		cout << "NO" << endl;
	}
}
