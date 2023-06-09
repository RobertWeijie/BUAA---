#include <stdio.h>

int main()
{
	int m1,n1;//m代表时，n代表分
	int m2,n2;
	scanf("%d %d",&m1,&n1); //输入第一次时间
	scanf("%d %d",&m2,&n2); //输入第二次时间
	
	int t1=m1*60+n1;
	int t2=m2*60+n2;
	
	int t=t2-t1;
	printf("%d %d",t/60,t%60);
	return 0;
}
