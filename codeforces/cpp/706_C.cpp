#include <iostream>
#include <cstdio>
#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

long long n, ans, PC, RPC, NC, RNC, NC1, NC2, RNC1, RNC2;
//now string / reverse now string, prev string, reverse prev string
char NS[100001], RNS[100001], PS[100001], RPS[100001];

void	revStr(char *str, char *rev_str)
{
	int	len;

	len = strlen(str);
	for (int i =0; i < len; i++)
	{
		rev_str[len - i - 1] = str[i];
	}
	rev_str[len] = '\0';
}

ll	cmp_ll(ll first, ll second)
{
	if (first == -1 && second == -1)	return (-1);
	else if (first != -1 && second == -1)	return (first);
	else if (first == -1 && second != -1)	return (second);
	else									return(min(first, second));
}

int main()
{
	ll *cost;
	ll len;
	cin >> n;
	cost = (ll *)malloc(sizeof(ll) * n);
	for (int i = 0; i< n; i++)
		cin >> cost[i];
	cin >> PS;
	revStr(PS, RPS);
	PC = 0; RPC = cost[0];
	for (int i = 0; i < n - 1; i++)
	{
		NC = -1; RNC = -1, NC1 = -1, NC2 = -1, RNC1 = -1, RNC2 = -1;
		cin >> NS;
		revStr(NS, RNS);
		//순전 - 순전, 반전 - 순전, 순전 - 반전, 반전 - 반전
		if (strcmp(PS, NS) <= 0 && PC != -1) NC1 = PC;
		if (strcmp(RPS, NS) <= 0 && RPC != -1) NC2 = RPC;
		if (strcmp(PS, RNS) <= 0 && PC != -1) RNC1 = PC + cost[i + 1];
		if (strcmp(RPS, RNS) <= 0 && RPC != -1) RNC2 = RPC + cost[i + 1];

		NC = cmp_ll(NC1, NC2);
		RNC = cmp_ll(RNC1, RNC2);
		//printf("PS : [%s], RPS : [%s], NS : [%s], PNS: [%s]\n", PS, RPS, NS, RNS);
		//printf("PC : %lld, RPC : %lld, NC : %lld, RNC : %lld\n", PC, RPC, NC, RNC);
		if (NC == -1 && RNC == -1)
		{
			cout << -1 << endl;
			return (0);
		}
		else
		{
			PC = NC;
			RPC = RNC;
			strcpy(PS, NS);
			strcpy(RPS, RNS);
		}
	}
	cout << cmp_ll(PC, RPC) << endl;
}
