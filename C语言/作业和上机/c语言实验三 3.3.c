#include <stdio.h>

int main ()
{
	int n,i;
	long fibonacci[100000];//1≤n≤ 100000 
	scanf("%d",&n);
	fibonacci[0]=1;
	fibonacci[1]=1;
	for (i=2;i<n+1;i++)
	{
		fibonacci[i]=(fibonacci[i-1]%1000000007+fibonacci[i-2]%1000000007)%1000000007;//数列的第n项值对1000000007  取模
	}
	printf("%ld\n",fibonacci[n-1]);
	return 0;
}
 