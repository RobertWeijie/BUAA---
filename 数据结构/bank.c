#include<stdio.h>
#include<math.h>
#include<string.h>
#include<stdlib.h>


int nowt,now_num;

struct Node
{
	int num,tim;
	struct Node *next;
}a,*list,*rear;//rear 代表表的后端进行插入操作

int is_empty()////测试队列是否为空
{
	return list==NULL;
}

int get_min(int a,int b)
{
	return a<=b?a:b;
}

void insert(int t,int x)
{
	struct Node *p=malloc(sizeof(a));
	p->num=x;
	p->tim=t;
	p->next=NULL;
	if (is_empty())
		{
			list=p;
			rear=p;
		}
	else 
		{
			rear->next=p;
			rear=p;
		}
}

int get_win()//提供服务的窗口数
{
	if (is_empty())
		return 3;//三个对私
	int ans=rear->num-list->num+1;
	int wind=ans/7+1;//平均等待服务人数小于7人，则只增加一个窗口
	if (wind>5)
		wind=5;
	if (wind<3)
		wind=3;
	return wind;
}

int main()
{
	int n,lw=3;
	scanf("%d",&n);//时间周期数
	for (nowt=1;;nowt++)
		{
			if (is_empty()&&nowt>n) 
				break;
			if (nowt<=n)
				{
					int com_cus,i;
					scanf("%d",&com_cus);//每个时间周期中因私业务的客户数
					for (i=1;i<=com_cus;i++)
						insert(nowt,now_num+i);
					now_num+=i-1;
				}
			int w=get_win(),j;
			if (nowt>n)
				w=get_min(w,lw);
			lw=w;
			for (j=1;j<=w&&!is_empty();j++)
				{
					printf("%d : %d\n",list->num,nowt-list->tim);
					list=list->next;
					if (nowt>n&&!is_empty()&&rear->num-list->num-1==7*(w-1))
						{w-=1;lw=w;}
					if (w<3)w=3;
				}
		}
	return 0;
}
