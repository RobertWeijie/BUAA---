#include <stdio.h>
int main ()
{
	
	int a,b,c,d,e;
	scanf("%d%d%d",&a,&b,&c);
	if (a<b)
	{
		d=a;
		a=b;
		b=d;
	}
	if(a<c)
		{
			d=a;
			a=c;
			c=d;
		}
	if(b<c)
		{
			d=b;
			b=c;
			c=d;
		}
	printf("%d\n",b);
	e=a+b+c;
	printf("%d\n",e);
	return 0;
}
