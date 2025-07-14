#include <iostream>
typedef long long ll;
using namespace std;
int main()
{
	ll M, N, left, mid, right;
	ll tmp;
	ll ret = 0;
	ll *input;
	cin >> N >> M;
	input = new ll[M];
	for (size_t i = 0; i < N; i++)
	{
		cin >> input[i];
	}
	sort(input, input + N);
	if (input[0] * -1 > input[N - 1])
	{
		tmp = 0;
		left = 1;
		right = N;
	}
	else
	{
		tmp = N;
		left = 0;
		right = N - 1;
	}
	mid = N / 2;
	while (1)
	{
		if (input[mid] == 0 || left == right || right == left + 1)
			break;
		if (input[mid] > 0)
		{
			right = mid;
			mid = (right + left) / 2;
		}
		if (input[mid]  < 0)
		{
			left = mid;
			mid = (right + left) / 2;
		}
	}
	cout << input[mid] << endl;
}
