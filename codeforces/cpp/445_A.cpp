/*
codeforces
445 A Boredom

using dp
*/
#include <iostream>
#include <vector>

using namespace std;

int main()
{
	long long n, tmp;
	long long v[100001] = {0};
	cin >> n;
	for (long long i = 0; i < n; i++)
	{
		cin >> tmp;
		v[tmp - 1] += tmp;
	}
	long long **chose = new long long *[2];
	chose[0] = new long long [100001];
	chose[1] = new long long [100001];
	chose[0][0] = 0;
	chose[1][0] = v[0];
	chose[0][1] = max(chose[0][0], chose[1][0]);
	chose[1][1] = v[1];
	for (long long i = 2; i <= 100000; i ++)
	{
		chose[0][i] = max(chose[0][i - 1], chose[1][i - 1]);
		chose[1][i] = max(chose[1][i - 2] + v[i], chose[0][i - 1] + v[i]);
	}
	cout << max(chose[0][100000], chose[1][100000]) << endl;
}
