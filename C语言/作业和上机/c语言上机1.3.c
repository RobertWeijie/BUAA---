#include <stdio.h>
#define PI 3.1415926
int main()
{
double a,b,c;
printf("请输入半径:");
scanf("%lf",&a);
if(a<=0)
	printf("输入错误!");//如果半径小于0，那就报错显示输入错误
else 
	{
b=PI*2*a;//周长公式= 2 * pi * 半径 
c=PI*a*a;//面积公式pi * 半径*半径
printf("周长=%lf 面积=%lf\n。",b,c);
}
return 0;
}