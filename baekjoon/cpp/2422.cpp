/**
 * 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 * N종류의 아이스크림, 3종류의 조합
 * 다만, 조합끼리 맛없는 조합이 존재할 수 있다.
 * 완전탐색 -> n * (n - 1)
 * 첫번째 먹는 것에 대해서만 체크하고, 나머지는 곱셉만 하면 되긴 한다.
 * 일단 내가 생각한 방식대로 구현했지만, 제대로 정답이 나오지 않는다.
 * 지금 구현대로 할 경우 1 10에서 1, 5 10을 선택하는 경우가 채택된다. -> 문제가 아주 됨.
*/
#include <iostream>
#define T 201

using namespace std;

long long total[T];
long long node[T][T];

int main()
{
	for (int i = 1; i < T; i++)
	{
		node[i][i] = 1;
	}
	long long N, M, in, out, ret;
	ret = 0;
	cin >> N >> M;
	for (int i = 0; i < M; i++)
	{
		cin >> in >> out;
		node[in][out] = 1;
		node[out][in] = 1;
	}
	for (int i = 1; i <= N; i++)
	{
		for (int j = i + 1; j <= N; j++) 
		{
			if (node[i][j] == 0)
				total[i] += 1;
		}
	}
	for (int i = 1; i <= N; i++)
	{
		for (int j = i + 1; j <= N; j++) //같은 거 주의
		{
			if (node[i][j] == 0)
				ret += total[j];
		}
	}
	cout << ret << endl;
}
