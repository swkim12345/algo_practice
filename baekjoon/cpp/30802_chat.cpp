#include <iostream>
#include <cmath>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
    cin.tie(0);
    cout.tie(0);
    ios::sync_with_stdio(false);
}

ll N, tshirt[6], T, P;

void solved() {
    ll tshirt_sum = 0;

    // 티셔츠의 최소 묶음 수 계산
    for (int i = 0; i < 6; i++) {
        // 정수 나눗셈을 통해 올림값을 구함
        tshirt_sum += (tshirt[i] + T - 1) / T;
    }
    cout << tshirt_sum << endl;

    // 펜의 최대 묶음 수와 나머지 계산
    cout << N / P << " " << N % P << endl;
}

int main() {
    init(); // fast io 초기화

    // 입력 처리
    cin >> N;
    for (int i = 0; i < 6; i++) {
        cin >> tshirt[i];
    }
    cin >> T >> P;

    solved();
}
