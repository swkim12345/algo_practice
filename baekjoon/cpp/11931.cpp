/**
 * 수 정렬하기 4
 * 중복이 되지 않고, 내림차순으로 정렬만 하면 되므로, 배열에 저장하고 하나씩 읽는 식으로 하자.
 */

#include <iostream>
using namespace std;
#define total 1000000
int n[total * 2 + 1];
int main()
{
	int N, tmp;
	cin >> N;
	for (int i = 0 ; i < N; i++)
	{
		cin >> tmp;
		n[tmp + total] = 1;
	}
	for (int i = total * 2; i >= 0; i--)
	{
		if (n[i] == 1)
		{
			cout << i - total << "\n";
		}
	}
	return (0);
}
