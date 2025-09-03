# Problem Solving

### CPP 기본 헤더 + fast io

```c++
#include <algorithm>
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
```
### struct 작성 예시

```c++
struct func{
	ll a, b, c;
	func operator-(const func &in) const{
		func ret;
		ll this_a, in_a;
		this_a = a; 
		in_a = in.a;
		ret.a = a - in.a;
		ret.b = b - in.b;
		ret.c = c - in.c;
		return (ret);
	}
};
```
