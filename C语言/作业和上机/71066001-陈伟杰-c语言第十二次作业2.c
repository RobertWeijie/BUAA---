#include <stdio.h>
int function1(char* s, int* pos) 
{
	int i, n = 0;
	for (i = 0; s[i] != '\0'; i++)
		{
		if (s[i] == 'A') {
			pos[n] = i; n++;
		}
	} return n;
}
int main() 
{
	char a[1001] = "aAAAdsfeA"; int pos[1001], n;
	int i;
	n = function1(a, pos); for (i = 0; i < n; i++) 
		{
		printf(" %d", pos[i]);
	}
	return 0;
}