//#include <bits/stdc++.h>
#include <cstdio>
#include <iostream>
 
using namespace std;
 
long long n, Cost[100005];
 
char CO[100005], CR[100005], PO[100005], PR[100005];
 
long long ans, AO, AR, AO1, AR1, AO2, AR2, PAO, PAR;
 
int main()
{
    int i, j, len;
    scanf("%lld", &n);
    for (i=1; i<=n; i++) scanf("%lld", &Cost[i]);
    scanf("%s", CO);
    len = strlen(CO);
    for (j=0; j<len; j++) CR[len-j-1] = CO[j];
    CR[len] = '\0';
    AO = 0; AR = Cost[1];
    for (i=2; i<=n; i++) {
        strcpy(PO, CO); strcpy(PR, CR);
        PAO = AO; PAR = AR;
        scanf("%s", CO);
        len = strlen(CO);
        for (j=0; j<len; j++) CR[len-j-1] = CO[j];
        CR[len] = '\0';
        // AO, AR
        if (PAO == -1 && PAR == -1) { AO = -1; AR = -1;}
        else {
            AO1 = -1; AO2 = -1;
            if (strcmp(CO, PO) >= 0 && PAO != -1) AO1 = PAO;
            if (strcmp(CO, PR) >= 0 && PAR != -1) AO2 = PAR;
            if (AO1 == -1 && AO2 == -1) AO = -1;
            else if (AO1 == -1 && AO2 != -1) AO = AO2;
            else if (AO1 != -1 && AO2 == -1) AO = AO1;
            else AO = min(AO1, AO2);
            AR1 = -1; AR2 = -1;
            if (strcmp(CR, PO) >= 0 && PAO != -1) AR1 = PAO + Cost[i];
            if (strcmp(CR, PR) >= 0 && PAR != -1) AR2 = PAR + Cost[i];
            if (AR1 == -1 && AR2 == -1) AR = -1;
            else if (AR1 == -1 && AR2 != -1) AR = AR2;
            else if (AR1 != -1 && AR2 == -1) AR = AR1;
            else AR = min(AR1, AR2);
        }
    }
    if (AO == -1 && AR == -1) ans = -1;
    else if (AO == -1 && AR != -1) ans = AR;
    else if (AO != -1 && AR == -1) ans = AO;
    else
        ans = min(AO, AR);
    printf("%lld\n", ans);
    return 0;
}
