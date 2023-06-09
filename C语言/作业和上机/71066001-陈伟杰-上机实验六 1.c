#include<stdio.h>


int count;
void shoot (int n)
{
	if(n==0)
	{
		count++;
	}
	if(n>0&&n-2>=0)
		shoot(n-2);
	if(n-3>=0)
		shoot(n-3);
	return;
}

int main()
{
	int n;
	scanf("%d",&n);
	shoot(n);
	printf("%d",count);
	return 0;
}
