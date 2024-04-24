/*
codeforces
446_C
C. Number of Ways
Using prefix sum
*/

#include <iostream>

int main()
{
	int n;

	std::cin >> n;
	long long a[n + 1];
	long long sum[n + 1];
	long long count = 0;
	sum[0] = 0;
	a[0] = 0;
	for (int i = 1; i < n + 1; i++)
	{
		std::cin >> a[i];
		sum[i] = sum[i - 1] + a[i];
	}
	if (sum[n] % 3 != 0)
	{
		std::cout << 0 << std::endl;
		return (0);
	}
	else
	{
		long long target = sum[n] / 3;
		long long target2 = target * 2;
		long long t = 0;
		for (size_t i = 1; i < n; i++)
		{
			if (sum[i] == target2)
			{
				count += t;
			}
			if (sum[i] == target)
			{
				t += 1;
			}
		}
	}
	std::cout << count << std::endl;
	return (0);
}
