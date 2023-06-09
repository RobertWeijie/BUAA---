#include <stdio.h>

int main()
{
	int x;//一个整数定义x
	double gpa;
	scanf("%d",&x);//输入x整数
	if(x>=0 && x<=60)
	{
		printf("0\n");//低于60分对应绩点0
	}
		else 
			{
				gpa=4-3.0/1600*(100-x)*(100-x);//题目给的公式
				printf("%.2f",gpa);//。2f因为是double类型
	}
	return 0;
}