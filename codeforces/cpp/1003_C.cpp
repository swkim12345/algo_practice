#include <iostream>
#include <iomanip>

/*
codeforces 1003_C
C. Intense Heat
Using prefix sum
*/

int main()
{
	long k, n;
	std::cin >> n >> k;
	double a[n + 1];
	double sum[n + 1];
	sum[0] = 0;
	a[0] = 0;
	for (int i = 1; i < n + 1; i++)
	{	
		std::cin >> a[i];
		sum[i] = sum[i - 1] + a[i];
	}
	double max_arr[n + 1];
	double ret = 0;
	for (size_t i = k; i < n + 1; i++)
	{
		max_arr[i] = 0;
		for (size_t j = 0; j < i - k + 1; j++)
		{
			if (max_arr[i] < (sum[i] - sum[j]) / (i - j))
			{
				max_arr[i] = (sum[i] - sum[j]) / (i - j);
			}
		}
		if (ret < max_arr[i])
		{
			ret = max_arr[i];
		}
	}
	std::cout << std::fixed << std::setprecision(15) << ret << std::endl;
	return (0);
}
