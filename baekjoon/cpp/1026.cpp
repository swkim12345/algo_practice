#include <iostream>
#include <algorithm>

typedef long long ll;
using namespace std;
ll n;
ll a[51], b[51];

int main()
{
	cin >> n;
	for (int i = 0; i< n; i++)
	{
		cin >> a[i];
	}
	for (int i =0; i < n; i++)
	{
		cin >> b[i];
	}
	sort(a, a + n, greater<ll>());
	sort(b, b + n, less<ll>());
	ll ret = 0;
	for (int i = 0; i < n; i++)
	{
		ret += a[i] * b[i];
	}
	cout << ret << endl;
}
