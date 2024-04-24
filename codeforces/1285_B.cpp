/*
codeforces
1285 B
Just Eat It!
Using prefix sum
*/

#include <iostream>
#include <algorithm>

int main()
{
	long long t;
	long long M;
	long long *sum;
	std::cin >> t;
	for (size_t i = 0; i < t; i++)
	{
		long long n;
		std::cin >> n;
		long long in;
		sum = new long long[n + 1];
		sum[0] = 0;
		for (size_t j = 1; j < n + 1; j++)
		{
			std::cin >> in;
			sum[j] = sum[j - 1] + in;
		}
		in = 0;
		M = -INT_MAX;
		long long tmp = 0;
		for (size_t j = 1; j < n; j++)
		{
			M = std::max(M, sum[j] - sum[in]);
			if (sum[in] > sum[j])
				in = j;
		}
		in = 1;
		for (size_t j = 2; j < n + 1; j++)
		{
			M = std::max(M, sum[j] - sum[in]);
			if (sum[in] > sum[j])
				in = j;
		}
		if (M < sum[n])
			std::cout << "YES" << std::endl;
		else
			std::cout << "NO" << std::endl;
		
		delete[] sum;
	}
}
