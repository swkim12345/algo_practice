/*
codeforces
189_A cut ribbons
using dynamic programming
*/

#include <iostream>

using namespace std;

int main()
{
	int x[4001];
	for (int i = 0; i <= 4000; i++)
		x[i] = INT32_MIN;
	int n, a, b, c;
	cin >> n >> a >> b >> c;
	x[a] = 1; x[b] = 1; x[c] = 1;
	for (int i = 0; i <= 4000; i++)
	{
		if (i - a >= 0)
			x[i] = max(x[i], x[i - a] + 1);
		if (i - b >= 0)
			x[i] = max(x[i], x[i - b] + 1);
		if (i - c >= 0)
			x[i] = max(x[i], x[i - c] + 1);
		if (i == n)
			break ;
	}
	cout << x[n] << endl;
}
