/**
 * 평범한 배낭
 * dp를 이용한 문제
 * greedy는 안 됨 -> 최상의 경우의 수가 존재하지 않음.
 * 만약 최상의 가치를 지니는 것만 선택할 경우 -> 여러개의 합으로 더 나은 결과를 만들 수 있음
 * bruteforce하게 풀면 고르는 경우 / 안 고르는 경우가 발생할 수 있으니 2^N이 존재한다.
 * 
*/
#include <iostream>
#include <algorithm>

using namespace std;
typedef long long ll;
# define total 100'001
ll value[total];

int main()
{
	ll N, K;
	cin >> N >> K;
	ll *W = new ll[N];
	ll *V = new ll[N];
	for (int i = 0; i< N; i++)
	{
		cin >> W[i] >> V[i];
		if (value[i] < W[i])
			value[i] = W[i];
	}
	//dp
	for (int i = 0; i <= K; i++)
	{
		//max(현재, 현재 - 물품의 가치)
	}

}
