#include <iostream>
#include <cstdio>

using namespace std;
typedef long long ll;
#define total 1000001

typedef struct s_ll{
	ll a;
	ll b;
} t_ll;

ll n, tmp; //Broken, Not broken
ll BA[total], NBA[total];
t_ll ab[total];

bool compare(t_ll a, t_ll b)
{
	if (a.a < b.a)
		return true;
	else
		return false;
}

int main()
{
	ll tmp;
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> tmp;
		ab[tmp].a = tmp;
		cin >> ab[tmp].b;
	}
	BA[0] = 0;
	NBA[0] = 0;
	for (int i = 1; i < total; i++)
	{
		if (ab[i].a == 0)
		{
			ab[i].a = ab[i - 1].a;
			ab[i].b = ab[i - 1].b;
			continue;
		}
		//if broken
		BA[i] = max(BA[i - 1], NBA[i - 1]);
		//if not broken
		tmp = i - ab[i].b - 1;
		if (tmp < 0)
			NBA[i] = 1;
		else
			NBA[i] = max(BA[tmp], NBA[tmp]) + 1;
		printf("NBA : %lld, BA : %lld, i : %d\n", NBA[i], BA[i], i);
	}
	cout << n - max(BA[total - 1], NBA[total - 1]) << endl;
}
