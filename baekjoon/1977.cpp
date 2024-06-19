/**
 * 완전제곱수
 * M은 N보다 항상 작던지 같음
 * M보다 큰 root된 값을 구하면 됨
 * N보다 작은 root 된 값을 구함
 * 만약, 두 값 중 M에서 구한 root된 값이 N에서 구한 값보다 크다면 -1
 * 아니면 합과 최소값만 출력.
*/
#include <iostream>
#include <algorithm>

using namespace std;
long long M, N, i, ii, s, mn;
int main()
{
	cin >> M >> N;
	i = 1;
	ii = i * i;
	while (ii <= N)
	{
		ii = i * i;
		if (M <= ii && ii <= N)
		{
			if (mn == 0)
				mn = ii;
			s += ii;
		}
		i += 1;
	}
	if (mn == 0)
		cout << -1 << endl;
	else
	{
		cout << s << endl;
		cout << mn << endl;
	}
	return (0);
}
