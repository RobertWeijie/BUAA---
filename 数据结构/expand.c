#include<stdio.h>
#include<string.h>
#define N  10060;
char s [10060];

int check(char c) {
	if (c >= 'a' && c <= 'z') return 1;
	if (c >= 'A' && c <= 'Z') return 2;
	if (c >= '0' && c <= '9') return 3;
	return 0;
}

int main() {
	scanf("%s", s);
	int n = strlen(s);
	for (int i = 0; i < n; i++) {
		if (s[i] == '-') {
			if (!i) printf("%c", s[i]);
			else if (s[i + 1] <= s[i - 1]) printf("%c", s[i]);
			else if (check(s[i - 1]) == check(s[i + 1]) && check(s[i - 1])) {
				for (int j = s[i - 1] + 1; j < s[i + 1]; j++) {
					printf("%c", j);
				}
			}
			else printf("%c", s[i]);
		}
		else {
			printf("%c", s[i]);
		}
	}
}