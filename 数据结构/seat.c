#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>

int n;
struct Node //
{
	int num;
	char name[18];
	int loc;
	struct Node *next;
}a,*list,*tail,stu[99];

void insert(int xuehao,char *xingming,int zuowei)
{
	struct Node *p=malloc(sizeof(a));
	p->loc=zuowei;//位置等于座位
	p->num=xuehao;//数字等于学号
	p->next=NULL;
	strcpy(p->name,xingming);
	if (list==NULL)
	{
		list=p;
		tail=p;
		return;
	}
	if (p->loc < list->loc)
	{
		p->next=list;
		list=p;
		return;
	}
	if ( (p->loc > tail->loc) || (p->loc==tail->loc&&p->num > tail->num ) )
	{
		tail->next=p;
		tail=p;
		return;
	}
	
	struct Node *now=list;
	struct Node *lnow=list;
	while (1)
	{
		if (now->loc==p->loc)
		{
			if (p->num > now->num)
			{
				p->next=now->next;
				now->next=p;
				break;
			}
			if (p->num < now->num)
			{
				p->next=now;
				lnow->next=p;
				break;
			}
		}
		if (now->loc > p->loc)
		{
			p->next=now;
			lnow->next=p;
			break;
		}
		lnow=now;
		now=now->next;
		if  (now==NULL) 
			break;
	}
}

void solve()
{
	int maxn=1;//从1号开始检查
	if (list->loc!=1)
	{
		struct Node *last2=list;
		while (last2->next!=tail)
			last2=last2->next;
		tail->loc=1;
		tail->next=list;
		list=tail;
		tail=last2;
		tail->next=NULL;
		maxn=2;
	}
	struct Node *now=list;
	struct Node *lnow=list;
	while (now!=NULL)
	{
		if (now->loc > maxn)
		{
			struct Node *last2=list;
			while (last2->next!=tail)
				last2=last2->next;
			tail->loc=maxn;
			tail->next=now;
			lnow->next=tail;
			tail=last2;
			tail->next=NULL;
			maxn++;
		}
		maxn=now->loc+1;
		lnow=now;
		now=now->next;
	}
	
	now=list->next;
	lnow=list;
	while (now!=NULL)
	{
		if (now->loc==lnow->loc)
		{
			int maxx=tail->loc+1;
			now->loc=maxx;
			lnow->next=now->next;
			tail->next=now;
			now->next=NULL;
			tail=now;
			now=lnow->next;
		}
		now=now->next;
		lnow=lnow->next;
	}
}

int cmp(const void *a,const void *b)
{
	struct Node *c=(struct Node *)a;
	struct Node *d=(struct Node *)b;
	return c->num-d->num ;
}

void output(FILE *o)
{
	int i=1;
	while (list!=NULL)
	{
		stu[i].loc=list->loc;
		stu[i].num=list->num;
		strcpy(stu[i].name,list->name);
		list=list->next;
		i++;
	}
	qsort(stu,n+1,sizeof(a),cmp);
	for (i=1;i<=n;i++)
		fprintf(o,"%d %s %d\n",stu[i].num,stu[i].name,stu[i].loc);
}

int main()
{
	FILE *in=fopen("in.txt","r");//初始考试座位安排表存储在当前目录下的in.txt文件中
	FILE *out=fopen("out.txt","w");//修正后的考试座位安排表输出到当前目录下的out.txt文件中
	int i;
	scanf("%d",&n);
	for (i=1;i<=n;i++)
	{
		int rnum,rloc;
		char rname[18];
		fscanf(in,"%d",&rnum);
		fscanf(in,"%s",rname);
		fscanf(in,"%d",&rloc);
		insert(rnum,rname,rloc);
	}
	
	solve();
	
	output(out);
	
	fclose(in);
	fclose(out);
	return 0;
}