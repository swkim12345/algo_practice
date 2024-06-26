/**
 * ATM
 * 최소? 그리딘가?
 * 왜 그리디임? - 첫번째부터 a1 * n + a2 * (n - 1) ...등의 형식으로 구해지게 되는 데, 이는 처음 오는 값이 작아야 한다는 뜻이다.
 * 음수일 때에도 작동한다 -> 작은 값을 구해야하므로.
 */

#include <iostream>
#include <algorithm>
using namespace std;

long long N[1001];

int main()
{
	long long in, ret;
	ret = 0;
	cin >> in;
	for (int i = 0; i < in; i++)
	{
		cin >> N[i];
	}
	sort(N, N + in);
	for (int i = 0; i < in; i++)
	{
		ret += N[i] * (in - i);
	}
	cout << ret << endl;
}
