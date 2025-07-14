/**
 * 두가지로 푸는 방법이 존재함
 * 브루트 포스방식과 공통된 인자를 구해서 하는 방식
 * 여기선 브루트포스로 나이브하게 해결할 생각임.
*/

#include <iostream>
using namespace std;
long long a1, a2, a3, a4, a5, total;
int main()
{
	long long count;
	cin >> a1 >> a2 >> a3 >> a4 >>a5;
	total = a1 * a2 * a3 * a4 *a5;
	for (int i = 1; i <= total; i++)
	{
		count = 0;
		if (i % a1 == 0)
			count ++;
		if (i % a2 == 0)
			count++;
		if (i % a3 == 0)
			count++;
		if (i % a4 == 0)
			count++;
		if (i % a5 == 0)
			count++;
		if (count >= 3)
		{
			cout << i << endl;
			return (0);
		}
	}
}
