/**
 * 양념 반 후라이드 반
 * 반반치킨 - 양념 0.5, 후라이드 0.5
 * 일반 양념 / 2 + 후라이드 / 2 >= 반반치킨 => 반반치킨으로 최대한 맞추고, 나머지는 양념 후라이드로
 * 반대 => 일반 양념 후라이드
 * "최소"라는 조건이 있으므로 이것에 대한 처리도 필요하다.
*/
#include <iostream>
using namespace std;

int main()
{
	long long A,B,C,X,Y,sum, XY_min;
	float	half;
	std::cin >> A >> B >> C >> X >> Y;
	half = (A + B) / (float)2;
	sum = 0;
	XY_min = min(X, Y);
	if (half > C) // 반반이 더 쌀 경우 -> 최소만 맞춰서 구매 혹은 최대 맞춰서 구매 중 싼 거 선택
	{
		sum += XY_min * C * 2;
		if (XY_min == X)
		{
			sum += (Y - XY_min) * B;
		}
		else
		{
			sum += (X - XY_min) * A;
		}
		sum = min(sum, max(X, Y) * C * 2);
	}
	else //그냥 후라이, 양념만 시키는 게 더 쌀 경우
	{
		sum += X * A + Y * B;
	}
	cout << sum << endl;
}
