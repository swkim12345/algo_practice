/**이항 계수 1 */

#include <iostream>
using namespace std;

int main() {
	long long N, K, ret = 1, div = 1;
	cin >> N >> K;
	for (int i = K + 1; i <= N; i++)
	{
		ret *= i;
	}
	for (int i = 1; i <= N - K; i++)
	{
		div *= i;
	}
	cout << ret / div << endl;
}
