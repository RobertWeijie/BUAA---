#include <stdio.h>

void zuidagongyueshu(int a,int b)
{
	int t;
	while (b!=0)// 辗转相除法，逐次用后一个数去除前一个余数，直到余数是0为止。那么，最后一个除数就是所求的最大公约数。
	{
		t=a%b;
		a=b;
		b=t;
	}
	printf("%d",a);//最大公约数
}
int main ()
{
	int a=0,b=0,c;
	int i,j;
	for (i=0;i<4;i++)
	{
		scanf("%d",&c);//输入四个数字
		if (c>a)a=c;//寻找最大那个
	}
	for (j=0;j<4;j++)
	{
		scanf("%d",&c);
		if(c>b)b=c;//寻找最大那个
	}
	zuidagongyueshu(a, b);
	return 0;
}