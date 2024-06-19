/**
 * 소거법을 이용해 구현이 필요.
*/

#include <iostream>

using namespace std;

int main()
{
	long long a, b, c, d, e,f, x, y;
	cin >> a>>b>>c>>d>>e>>f;
	x = (c * e - b * f) / (a * e - b * d);
	y = (c * d - a * f) / (b * d - a * e);

	cout << x << " " << y << endl;
}
