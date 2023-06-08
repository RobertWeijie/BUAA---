#include<stdio.h>
#include<string.h>

#define N 100010
char A[N], B[N];
int a[N], b[N], m, n;

int cmp(char a[], char b[]) {
    if (strlen(a) != strlen(b)) {
        if (strlen(a) > strlen(b))
            return 1;
        else return 0;
    }

    for (int i = 0; i < strlen(a); i++) {
        if (a[i] != b[i]) {
            if (a[i] > b[i])
                return 1;
            else return 0;
        }
    }

    return 1;
}

void sub(int a[], int b[], int m, int n) {
    int c[N], t = 0;
    for (int i = 0; i < m; i++) {
        t = a[i] - t;
        if (i < n)
            t = t - b[i];
        c[i] = (t + 10) % 10;
        if (t < 0) t = 1;
        else t = 0;
    }

    while (c[m - 1] == 0 && m - 1 > 0) m--;
    for (int i = m - 1; i >= 0; i--)
        printf("%d", c[i]);
}

int main()
{
    scanf("%s%s", &A, &B);
    m = strlen(A); n = strlen(B);
    for (int i = strlen(A) - 1; i >= 0; i--) a[m - i - 1] = A[i] - '0';
    for (int i = strlen(B) - 1; i >= 0; i--) b[n - i - 1] = B[i] - '0';
    if (cmp(A, B) == 1)
        sub(a, b, m, n);
    else {
        printf("-");
        sub(b, a, n, m);
    }
    return 0;
}