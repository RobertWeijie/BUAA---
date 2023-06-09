#include <stdio.h>
void StepFunction(int x, int y);//定义StepFunction函数
int main (void)
{
	int x,y;
	printf("");
	scanf("%d",&x);
	if (x<0)//根据题目公式
		y=-1;
	else 
		if (x==0)y=0;
	else y=1;
	printf("%d\n",y);
	return 0;
}