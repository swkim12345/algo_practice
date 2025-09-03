/**
 * 단순한 브루트포스를 이용한 문제
 * 먼저 sum을 구하고, 정렬한 뒤, 두개씩 제외해가면서 찾으면 된다.
 * 문제점
 * RTFM
 * 
*/
#include <iostream>
#include <algorithm>
typedef long long ll;
#define total 9
using namespace std;

ll MAN[total], sum, tmp;
int main()
{
	for (int i = 0; i < total; i ++)
	{
		cin >> MAN[i];
		sum += MAN[i];
	}
	sort(MAN, MAN + total);
	for (int i = 0; i < total; i++)
	{
		for (int j = i + 1; j < total; j++)
		{
			tmp = sum - MAN[i] - MAN[j];
			if (tmp == 100)
			{
				for (int k = 0; k < total; k++)
				{
					if (k != i && k != j)
					{
						cout << MAN[k] << endl;
					}
				}
				return (0);
			}
		}
	}
}
