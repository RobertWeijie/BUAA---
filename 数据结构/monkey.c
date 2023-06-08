#include<stdio.h>
#include <stdlib.h>


int n,m,q,monkey;//定义

struct Node
{
	int num;
	struct Node *next;
}
monk,*list;

void create()
{
	scanf("%d %d %d",&n,&m,&q);//输入三个整数n，m，q，各整数间以一个空格分隔
	int i;
	struct Node *now=list;
	for (i=1;i<=n;i++)
	{
		struct Node *p;
		p=malloc(sizeof(monk));
		p->num=i;
		p->next=list;
		if (list==NULL)
			list=p;
		else
			now->next=p;
		   now=p;
	}
}

int solve()
{
	struct Node *now=list;
	int i;
	for (i=1;i<q;i++)
		now=now->next;
	while (now->next!=now)
	{
		for (i=1;i<m-1;i++)
			now=now->next;
		now->next=now->next->next;
		now=now->next;
	}
	return now->num;
}

int main()
{
	create();
	printf("%d",solve());
	return 0;
}