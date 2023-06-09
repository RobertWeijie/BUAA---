#include <stdio.h>
int IsLeap(int n)//定义函数IsLeap 
{
	if (n%4==0&&n%100!=0||n%400==0)//普通年份能被4整除，且不能被100整除的，是闰年，世纪年份能被400整除的是闰年
		return 1;
	else
		return 0;
}
int main ()
{
	int n;
	scanf("%d",&n);
	if(IsLeap(n)==1)
		printf("1");//如果是闰年返回1
	else
	
		printf("0");//如果不是闰年返回0
	return 0;
		}