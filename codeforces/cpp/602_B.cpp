#include <iostream>
#include <cstdio>

using namespace std;
typedef long long ll;

ll n, PC, PNC, NNC, PPI, PI, NI, MAX;

int main()
{
	cin >> n;
	cin >> PI;
	PPI = PI;
	PNC = 0;
	NNC = 1;
	for (int i = 0;i<n - 1; i++)
	{
		cin >> NI;
		if (PI == NI) //같은 입력이 들어올 경우 - 현재 카운팅 증가.
			NNC++;
		else if (PPI == NI) //두번 전 꺼와 같은 입력이 들어올 경우 - 현재 카운팅 증가.
		{
			PC += NNC;
			NNC = 1;
			PPI = PI;
			PI = NI;
		}
		else //다른 입력이 들어올 경우(1, 2, 3 등) NNC만 넘김, 덧셈을 한 뒤, 옛날 카운팅으로 이동.
		{
			MAX = max(MAX, PC + PNC + NNC);
			PC = NNC;
			NNC = 1;
			PNC = 0;
			PPI = PI;
			PI = NI;
		}
		//printf("NNC : %lld, PNC : %lld, PC : %lld, NI : %lld, PI : %lld, PPI : %lld MAX : %lld\n", NNC, PNC, PC, NI, PI, PPI, MAX);
	}
	MAX = max(MAX, PC + PNC + NNC); //마지막까지 단 한번도 바뀌지 않을 경우.
	cout << MAX << endl;
	return (0);
}
