#include <bits/stdc++.h>
#include <cstdio>

using namespace std;

int n, k;

class Node {
public:
    int pt;
    int nchild;
    int dr;
    int ndesc;
    vector<int> AD;
};

Node C[200005];
int P[200005];

void dfs1(int nd, int PT)
{
    int i, s;
    C[nd].pt = PT;
    s = C[nd].AD.size();
    C[nd].nchild = 0;
    for (i=0; i<s; i++) {
        if (C[nd].AD[i] != C[nd].pt) {
            C[nd].nchild++;
            dfs1(C[nd].AD[i], nd);
        }
    }
}

int dfs2(int nd, int dr)
{
    int i, s;
    s = C[nd].AD.size();
    C[nd].dr = dr;
    C[nd].ndesc = 0;
    for (i=0; i<s; i++) {
        if (C[nd].AD[i] != C[nd].pt) {
            C[nd].ndesc += dfs2(C[nd].AD[i], dr+1);
        }
    }
    return 2 + C[nd].ndesc - 1;
}

int main()
{
    int i, x, y;
    long long ans;
    scanf("%d%d", &n, &k);
    for (i=1; i<n; i++) {
        scanf("%d%d", &x, &y);
        C[x].AD.push_back(y);
        C[y].AD.push_back(x);
    }
    dfs1(1, 0);
    dfs2(1, 0);
    for (i=1; i<=n; i++)
        P[i] = C[i].dr - C[i].ndesc;
    sort(P+1, P+n+1);
    ans = 0;
    for (i=0; i<k; i++)
        ans += P[n-i];
    printf("%lld\n", ans);
    return 0;
}
