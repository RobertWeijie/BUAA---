#include<stdio.h>
#include<math.h>
#include<string.h>
#include<stdlib.h>

char inl[198];
int d=0,now_line=0;

struct Nodee
{
	char chen;
	int jie;
}pa[198],wrong_one;

struct Node
{
	int line;
	char type;
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


void ppop()
{
		struct Node *p=malloc(sizeof(a));
		p=list;
		list=list->next;
		free(p);
}

void solve()                                         ////创建链表并检查
{
	int i;
	for (i=1;i<=d;i++)
	{
		struct Node *p=malloc(sizeof(a));
		p->line=pa[i].jie;
		p->type=pa[i].chen;
		if (p->type=='(')
		{
			p->next=list;
			list=p;
		}
		if (p->type=='{')
		{
			if (list!=NULL&&list->type=='(')
			{
				wrong_one.chen=list->type;
				wrong_one.jie=list->line;
			}
			else
			{
				p->next=list;
				list=p;
			}
		}
		if (p->type==')')
		{
			if (list==NULL||list->type=='{')
			{
				wrong_one.jie=p->line;
				wrong_one.chen=p->type;
			}
			else ppop();
		}
		if (p->type=='}')
		{
			if (list==NULL||list->type=='(')
			{
				wrong_one.jie=p->line;
				wrong_one.chen=p->type;
			}
			else ppop();
		}
	}
	if (list!=NULL)
	{
		wrong_one.jie=list->line;
		wrong_one.chen=list->type;
	}
}

void pprintf()
{
	int i;
	if (wrong_one.jie==-1&&wrong_one.chen==1)
		for (i=1;i<=d;i++)
			printf("%c",pa[i].chen);
	else printf("without maching '%c' at line %d",wrong_one.chen,wrong_one.jie);//当出现括号不匹配时，按下面要求输出相关信息：without maching <x> at line <n>
}

int main()
{
	FILE *in=fopen("example.c","r");//打开当前目录下文件example.c
	wrong_one.jie=-1;wrong_one.chen=1;
	while (fgets(inl,198,in))
	{
		now_line++;
		int i;
		for (i=0;i<strlen(inl);i++)
		{
			if (inl[i]=='/'&&i<strlen(inl)-1&&inl[i+1]=='/')    //注释一行
				break;
				
			if (inl[i]=='/'&&i<strlen(inl)-1&&inl[i+1]=='*')   //多行注释
			{
				int same=0;
				for (i=i+2;i<strlen(inl)-1;i++)
					if (inl[i]=='*'&&inl[i+1]=='/')
						same=1;
				if (same==0)
					while (fgets(inl,198,in))
					{
						now_line++; 
						int ok=0;
						int j;
						for (j=0;j<strlen(inl);j++)
							if (inl[j]=='*'&&j<strlen(inl)-1&&inl[j+1]=='/')
							{
								ok=1;
								break;
							}
						if (ok==1) break;
					}
			}
				
			if (inl[i]==39)                                    //引号
			{
				i++;
				while(inl[i]!=39)
					i++;
				i++;
			}
				
			if (inl[i]==34)                                    //引号
			{
				i++;
				while (inl[i]!=34)
					i++;
				i++;
			}
			
			if (inl[i]=='('||inl[i]==')'||inl[i]=='{'||inl[i]=='}')
			{
				d++;
				pa[d].jie=now_line;
				pa[d].chen=inl[i];
			}
		}
	}
	
	solve();
	pprintf();
	fclose(in);
	return 0;
}