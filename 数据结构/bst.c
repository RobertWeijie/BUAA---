#include<stdio.h>

#include<math.h>
#include<stdlib.h>
#include<string.h>


struct Node
{
	int data;
	struct Node *l,*r;
}a,*tree;

void insert(int x)//插入
{
	struct Node *now=tree;
	struct Node *p=malloc(sizeof(a));//新建一个叶结点
	p->data=x;
	p->l=NULL;
	p->r=NULL;
	if (now==NULL)
	{
		tree=p;
		return;
	}
	while (1)//根结点高度为1
	{
		if (x<now->data)//左子结点值小于根结点值
		{
			if (now->l==NULL)
			{
				now->l=p;
				break;
			}
			else now=now->l;
		}
		else if (x>=now->data)//右子结点值大于等于根结点值
		{
			if (now->r==NULL)
			{
				now->r=p;
				break;
			}
			else now=now->r;
		}
	}
}

void search(struct Node *p,int x)//二叉查找
{
	if (p->l==NULL&&p->r==NULL)
		printf("%d %d\n",p->data,x);//打印出叶结点的值和高度 
	else
	{
		if(p->l!=NULL)
			search(p->l,x+1);//遍历左子结点值
		if (p->r!=NULL)
			search(p->r,x+1);//遍历右子结点值
	}
}

int main()
{
	int n,i,x;
	scanf("%d",&n);
	for (i=1;i<=n;i++)
	{
		scanf("%d",&x);
		insert(x);
	}
	search(tree,1);
	return 0;
}