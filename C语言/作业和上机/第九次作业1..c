#include <stdio.h>

int max(int a,int b,int c)//定义max函数
{
int d = b>c?b:c; //找到b c的最大值d
return a>d?a:d;
}

 int main()
{
int a,b,c;
scanf("%d %d %d",&a,&b,&c);
printf("%d\n",max(a,b,c));
return 0;
}