#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>

struct Node
{
	int data;
	struct Node *next;
}a,*list;

int get_lenth()
{
	struct Node *p=malloc(sizeof(a));
	p=list;
	int ans=0;
	while (p!=NULL)
	{
		ans++;
		p=p->next;
	}
	return ans;
}

void ppush(int x)
{
	if (get_lenth()==100)//最大容量为100
		printf("error ");
	else
	{
		struct Node *p=malloc(sizeof(a));
		p->data=x;
		p->next=list;
		list=p;
	}
}

void popp()
{
	if (get_lenth()==0)//如果栈状态为空时进行出栈操作，或栈满时进行入栈操作，则输出字符串“error”
		printf("error ");
	else 
	{
		printf("%d ",list->data);
		struct Node *p=malloc(sizeof(a));
		p=list;
		list=list->next;
		free(p);
	}
}

int main()
{
	int switchhh;
	while (~scanf("%d",&switchhh))
	{
		if (switchhh==-1)//-1表示操作结束
			break;
		if (switchhh==1)//1表示入栈操作
		{
			int num;
			scanf("%d",&num);
			ppush(num);
		}
		if (switchhh==0)//0表示出栈操作
			popp();
	}
	return 0;
}
