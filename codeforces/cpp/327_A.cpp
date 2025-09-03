#include <bits/stdc++.h>
#include <cstdio>
 
using namespace std;
 
int n, ans;
int A[1000], B[1000], D[1000];
 
int main()
{
    int i, mx;
    scanf("%d", &n);
    ans = 0;
    for (i=0; i<n; i++) {
        scanf("%d", &A[i]);
        if (A[i] == 1) ans++;
        if (A[i] == 1) B[i] = -1;
        if (A[i] == 0) B[i] = 1;
    }
    // D[i] = maximum sum ending at A[i] must include A[i]
    D[0] = B[0];
    for (i=1; i<n; i++) {
        if (D[i-1] + B[i] > B[i]) D[i] = D[i-1] + B[i];
        else D[i] = B[i];
    }
    mx = -100;
    for (i=0; i<n; i++)
        mx = max(mx, D[i]);
    ans += mx;
    printf("%d\n", ans);
    return 0;
}
