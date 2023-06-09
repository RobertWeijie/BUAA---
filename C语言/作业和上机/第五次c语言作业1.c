#include <stdio.h>

int main() 
{
	unsigned long long fibonacci[50008];// 0≤n≤50000 
	int n,i;
	unsigned long long MOD=1000000007;//质数 1000000007
	unsigned long long ans=0,a=1,b=1,c;
	scanf("%d",&n);
	
	if (n==0||n==1 ){
		printf("1");
	}
	else{
		n=n-2;
		while(n-->0)//使用循环语句
		{
			c=a+b;
			if(c>=MOD)
			{
				c=c%MOD;//对质数 1000000007 取模的结果
			}
			if(b>=MOD)
				{
					b=b%MOD;//对质数 1000000007 取模的结果
				}
			if(a>=MOD)
				{
					a=a%MOD;//对质数 1000000007 取模的结果
				}
			a=b;
			b=c;
		}
		ans=(a+b)%MOD;
		printf("%llu",ans);
	}
	return 0;
}
