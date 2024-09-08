/**
 * 부당한 퍼즐 - 실버 2
 * 이중 연결리스트의 성질을 사용한 문제
 * 그렇게까지 구현할 필요는 없으니 1을 기준으로 한바퀴를 돌 되,
 * 어느쪽 방향으로 자랄 지를 확인한다.
 */

#include <algorithm>
#include <cmath>
#include <iostream>
#include <set>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

vector<ll> first, second;

int main() {
  ll n, tmp, first_start_point, second_start_point;
  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> tmp;
    if (tmp == 1) first_start_point = i;
    first.push_back(tmp);
  }
  for (int i = 0; i < n; i++) {
    cin >> tmp;
    if (tmp == 1) second_start_point = i;
    second.push_back(tmp);
  }
  // 양 또는 음 방향으로 가야하는 지 체크
  ll direction = 0;
  if (first[(first_start_point + 1) % n] ==
      second[(second_start_point + 1) % n])
    direction = 1;
  else if (first[(first_start_point + 1) % n] ==
           second[(second_start_point - 1) % n])
    direction = -1;
  else {
    cout << "bad puzzle\n";
    return (0);
  }
  // 문제 풀이
  while (true) {
    first_start_point += 1;
    second_start_point += direction;
	if (second_start_point < 0) second_start_point += n;
    if (first[first_start_point % n] != second[second_start_point % n]) {
      cout << "bad puzzle\n";
      return (0);
    }
    if (first[first_start_point % n] == 1) break;
  }
  cout << "good puzzle\n";
}
