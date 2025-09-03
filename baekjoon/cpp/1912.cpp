/**
 * 연속합
 * 부분합의 최대값을 구하는 문제.
 * 부분 합을 구해서 n에 넣은 다음, 투포인터로 최대값을 찾는 문제
 * greedy로 할 경우 문제가 있음.
 * dp를 이용해 구현해야 함.
 * f(x) = max(f(x -1), 0) + n[x];
 * 예외 케이스가 존재함 -> -로 이뤄져 있는 경우
 * minmax 값으로 max의 0을 대체해 비교하면 된다. <- 문제가 됨
 * 차라리, if로 검사해 답이 0일 경우, 이미 구해놓은 minmax값을 출력하면 된다.
 * 반례가 존재함
 * 3
 * 1 1 1 -> 2가 나오는 문제가 있음.
*/
#include <iostream>

#define total 100001
using namespace std;

typedef long long ll;

ll n[total], mx[total];
int main()
{
	ll j, tmp, sum;
	cin >> j;
	sum = INT64_MIN;
	for (int i = 1; i <= j; i++)
	{
		cin >> tmp;
		n[i] = tmp;
	}
	for (ll i = 1; i <= j; i++)
	{
		mx[i] = max((ll)0, mx[i - 1]) + n[i];
		sum = max(mx[i], sum);
	}
	cout << sum << endl;
}
