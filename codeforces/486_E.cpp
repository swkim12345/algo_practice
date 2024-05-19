//정답 아님!
#include <iostream>
#define N 100000 + 3

using namespace std;

long long a[N];
long long FM[N], Fm[N], BM[N], Bm[N];

int main()
{
	int	n;
	cin >> n;
	for (int i =1; i <= n; i++)
	{
		cin >> a[i];
	}
	FM[0] = a[0] - 1;
	Fm[0] = a[0] + 1;
	BM[n + 1] = a[n] - 1;
	Bm[n + 1] = a[n] + 1;
	for (int i =1; i <=n; i++)
	{
		if (FM[i - 1] <= a[i])
			FM[i] = a[i];
		else
			FM[i] = FM[i - 1];
		if (Fm[i - 1] >= a[i])
			Fm[i] = a[i];
		else
			Fm[i] = Fm[i - 1];
	}
	for (int i = n; i >= 1; i--)
	{
		if (BM[i + 1] <= a[i])
			BM[i] = a[i];
		else
			BM[i] = BM[i + 1];
		if (Bm[i + 1] >= a[i])
			Bm[i] = a[i];
		else
			Bm[i] = Bm[i + 1];
	}
	for (int i =1; i<= n; i++)
	{
		//check '3'
		if (FM[i - 1] < a[i] && Bm[i + 1] > a[i])
			cout << "3";
		else if (Fm[i - 1] > a[i] && BM[i + 1] < a[i])
			cout << "1";
		else
			cout << "2";
	}
	cout << endl;
	return (0);
}
