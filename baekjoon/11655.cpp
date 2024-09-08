/**
 * ROT13
 * 브론즈 1
 * 암호화한 다음 출력, 영어 알파벳,
 */

#include <algorithm>
#include <cmath>
#include <iostream>
#include <vector>
typedef long long ll;
using namespace std;

// fast io를 위해 버퍼링을 막음.
void init() {
  cin.tie(0);
  cout.tie(0);
  ios::sync_with_stdio(false);
}

unsigned char S[200];

int main() {
  cin.getline((char *)S, 120);
  for (auto iter = S; *iter != '\0'; iter++) {
    if (*iter >= 'a' && *iter <= 'z') {
      *iter += 13;
      if (*iter > 'z') *iter -= 26;
    } else if (*iter >= 'A' && *iter <= 'Z') {
      *iter += 13;
      if (*iter > 'Z') *iter -= 26;
    }
  }
  cout << (char *)S << endl;
}
