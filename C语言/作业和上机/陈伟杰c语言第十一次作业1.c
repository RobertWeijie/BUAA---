#include <stdio.h>

int funtion1 (int *a,int*b,int*c)
{
	int tmp;//排序过程
	if(*a>*b){
		tmp=*b;
		*b=*a;
		*a=tmp;
	}
	if(*a>*c){
		tmp=*c;
		*c=*a;
		*a=tmp;
	}
	if(*b>*c){
		tmp=*c;
		*c=*b;
		*b=tmp;
	}//无返回值
}
int main ()
{
	int a,b,c;
	printf("\n");
	scanf("%d%d%d",&a,&b,&c);
	funtion1(&a,&b,&c);//调用function1
	printf("%d %d %d\n",a,b,c);//a 里存放最小的，b 里存放第二大的，c 里存放最大的
	return 0;
}