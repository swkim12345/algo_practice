#include <iostream>

typedef long long ll;

using namespace std;

ll taxi[5];

int main()
{
	ll in;
	ll tmp;
	cin >> in;
	while (in--) //이후 전체 택시 개수를 사용하지 않아도 되는 이유는 taxi에 넣고 다니기 때문이다
	{
		cin >> tmp;
		switch (tmp)
		{
			case 1:
			taxi[1] += 1;
			break;
			case 2:
			taxi[2] += 1;
			break;
			case 3:
			taxi[3] += 1;
			break;
			case 4:
			taxi[4] += 1;
			break;
		}
	}
	tmp = min(taxi[1], taxi[3]);
	taxi[4] += tmp;
	taxi[1] -= tmp;
	taxi[3] -= tmp;
	tmp = taxi[2] / 2;
	taxi[4] += tmp;
	taxi[2] -= tmp * 2;

	tmp = min(taxi[2], taxi[1] / 2);
	taxi[4] += tmp;
	taxi[2] -= tmp;
	taxi[1] -= tmp * 2;

	tmp = taxi[1] / 4;
	taxi[4] += tmp;
	taxi[1] -= tmp * 4;

	tmp = taxi[2] + taxi[1];
	taxi[2] = 0;
	taxi[1] = 0;
	taxi[4] += tmp / 4;
	taxi[tmp % 4] += 1;
	cout << taxi[1] + taxi[2] + taxi[3] + taxi[4] << endl;
}
