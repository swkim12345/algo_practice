/*
codeforces
using greedy
*/

#include <iostream>
typedef long long ll;

using namespace std;

int main()
{
	ll n;
	ll tmp;
	ll sum = 0;
	ll ret = 0;
	ll own = 0;
	ll coin[101] = {0,};
	cin >> n;
	for (size_t i = 0; i < n; i++)
	{
		cin >> tmp;
		coin[tmp] += 1;
		sum += tmp;
	}
	
	for (size_t i = 100; i > 0; i--)
	{
		while (coin[i])
		{
			own += i;
			ret++;
			coin[i]--;
			if (own > sum / 2)
			{
				cout << ret << endl;
				return (0);
			}
		}
	}
}
