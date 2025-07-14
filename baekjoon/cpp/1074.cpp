/**
 * Z
 * 2**N * 2**N 정사각형 배열의 방문순서룰 구하는 문제
 * 재귀적으로 방문한다고 저술되어 있다.
 * 0을 포함 <= r, c < 2**N이다. 주의
 * r은 행, c는 열임. (위에서 아래로 가는 게 행)
*/

#include <iostream>

using namespace std;
typedef long long ll;

int main()
{
	ll N, r,c, ratio, total; 
	cin >> N >> r >> c;
	total = 0;
	while (N)
	{
		ratio = 1 << (N - 1);
		if (r - ratio >= 0) //3, 4번째 방문
		{
			if (c - ratio >= 0) //4번째 방문
			{
				total += ratio * ratio * 3;
				c -= ratio;
			}
			else //3번째 방문
			{
				total += ratio * ratio * 2;
			}
			r -= ratio;
		}
		else //1, 2번째 방문
		{
			if (c - ratio >= 0) //2번째 방문
			{
				total += ratio * ratio;
				c -= ratio;
			}
			else //1번째 방문
			{

			}
		}
		N--;
	}
	cout << total << endl;
}
