#include<stdio.h>
#define N 1060

int st[N], res[N];
int n, idx;

void bfs(int k) {
	if (k == n ) {
		for (int i = 0; i < idx; i++) {
			printf("%d ", res[i]);
		}
		puts("");
		return;
	}

	for (int i = 1; i <= n; i++) {
		if (!st[i]) {
			st[i] = 1;
			res[idx++] = i;
			bfs(k + 1);
			idx--;
			st[i] = 0;
		}
	}
}

int main() {
	scanf("%d", &n);
	bfs(0);
}
