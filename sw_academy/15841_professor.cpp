//#include <bits/stdc++.h>
#include <iostream>
using namespace std;

  int A[600000], B[600000], C[600000], D[600000];
int N, M, Q;
  long long ans;
  class node {
 public:
      long long sum;
      int l, r;
};
  node T[500000];
  void build(int x, int L, int R) {
      int mid;
      T[x].l = L;
  T[x].r = R;
      if (L == R) {
            T[x].sum = A[L];
        
  }
      else {
            mid = (L + R) / 2;
            build(x * 2, L, mid);
    build(x * 2 + 1, mid + 1, R);
            T[x].sum = T[x * 2].sum + T[x * 2 + 1].sum;
        
  }
}
  long long query(int x, int L, int R) {
      int mid;
  long long rvl, rvr;
      if (L == T[x].l && R == T[x].r) {
            return T[x].sum;
        
  }
      else {
            mid = (T[x].l + T[x].r) / 2;
            rvl = rvr = 0;
            if (L <= mid) rvl = query(x * 2, L, min(R, mid));
            if (R > mid) rvr = query(x * 2 + 1, max(mid + 1, L), R);
            return rvl + rvr;
        
  }
}
  long long update(int x, int i, int v) {
      int mid;
  long long rvc;
      if (T[x].l == T[x].r) {
            rvc = v - T[x].sum;
            T[x].sum = v;
            return rvc;
        
  }
      else {
            mid = (T[x].l + T[x].r) / 2;
            if (i <= mid) rvc = update(x * 2, i, v);
            else rvc = update(x * 2 + 1, i, v);
            T[x].sum += rvc;
            return rvc;
        
  }
}
 
  long long solve() {
      int i, j, k, R;
      ans = 0;
      cin >> N >> Q;
      for (i = 1; i <= N; i++)
        cin >> A[i];
      build(1, 1, N);
      for (i = 1; i <= Q; i++) {
            cin >> D[i] >> B[i] >> C[i];
            if (D[i] == 1)
            ans += query(1, B[i], C[i]);
            else             update(1, B[i], C[i]);
        
  }
      return ans;
}
 
  int main() {
      cin.tie(0)->sync_with_stdio(0);
      int T;
  cin >> T;
      for (int ts = 1; ts <= T; ts++) {
            cout << "#" << ts << ' ' << solve() << '\n';
        
  }
}
 
                                    
