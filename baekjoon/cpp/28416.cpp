/**
 * 회장님께 바치는 합성함수.
 */

#include <algorithm>
#include <iostream>
#include <vector>
#define total 3000
typedef long long ll;
using namespace std;

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

func mul_func(func in, func out)
{
	func ret = {0, 0, 0};
	//2차항 -> in.b ** 2 * out.a
	ret.a = in.b * in.b * out.a;
	//1차항 -> 2 * in.b * in.c * out.a + in.c * in.c *out.b
	ret.b =  2 * in.b * in.c * out.a + in.c * in.c * out.b;
	//0차항 -> in.c * in.c * out.a + in.c * out.b + out.c
	ret.c = in.c * in.c * out.a + in.c * out.b + out.c;
	return (ret);
}

int main() {
	func in, out, p_func, q_func;
	cin >> out.a >> out.b >> out.c;
	cin >> in.b >> in.c;
	in.a = 0;
	p_func = mul_func(in, out);
	q_func = mul_func(out, in);

}
