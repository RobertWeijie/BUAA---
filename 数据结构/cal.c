#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>


char s[1100];

struct Node
{
	char cal;//运算符名称
	struct Node *next;
}p,*plist;

struct Nodee
{
	int data;
	struct Nodee *nextt;
}q,*qlist;

int is_empty()//判断队列是否为空
{
	return plist==NULL;
}

int pri(char x)//定义符号的优先级
{
	if (x==')')
		return 0;
	if (x=='+'||x=='-') return 1;
	if (x=='*'||x=='/'||x=='%') return 2;
	if (x=='(') return 0;//左括号已在栈内，如果要比较，其优先级最低
	return x;//要return x 因为并非所有路径都能返回一个值
}

void qpush(int x)//压栈
{
	struct Nodee *q=malloc(sizeof(q));
	q->data=x;
	q->nextt=qlist;
	qlist=q;
}

double qpop()//运算结果为浮点型
{
	struct Nodee *q=malloc(sizeof(q));
	q=qlist;
	qlist=qlist->nextt;
	int x=q->data;
	return x;
}

char ppop()//出栈
{
	struct Node *p=malloc(sizeof(p));
	p=plist;
	plist=plist->next;
	char x=p->cal;
	return x;
}

void simulate(char x)
{
	if (x=='+') qpush(qpop()+qpop());
	if (x=='-')
	{
		int a=qpop(),b=qpop();
		qpush(b-a);
	}
	if (x=='*') qpush(qpop()*qpop());
	if (x=='/') 
	{
		int a=qpop(),b=qpop();
		qpush(b/a);
	}
	if (x=='%') 
	{
		int a=qpop(),b=qpop();
		qpush(b%a);
	}
}

void ppush(char x)//压栈
{
	struct Node *p=malloc(sizeof(p));
	p->cal=x;
	if (x==')')
	{
		while (plist->cal!='(')
			simulate(ppop());
		ppop();
	}
	else if (x=='(')
	{
		p->next=plist;
		plist=p;
	}
	else 
	{
		while ( (!is_empty()) && pri(x)<=pri(plist->cal) )
			simulate(ppop());
		p->next=plist;
		plist=p;
	}
}

void init()
{
	int i;
	for (i=0;i<strlen(s);i++)
	{
		if (s[i]==' ') continue;
		else if (s[i]=='=')
			while (!is_empty())
				simulate(ppop());
		else if (s[i]>='0'&&s[i]<='9')
		{
			int ans=s[i]-'0',j;
			for (j=i+1;s[j]>='0'&&s[j]<='9';j++)
			{
				ans*=10;
				ans+=s[j]-'0';
			}
			qpush(ans);
			i=j-1;
		}
		else if (s[i]=='+'||s[i]=='-'||s[i]=='*'||s[i]=='/'||s[i]=='%'||s[i]=='('||s[i]==')')
			ppush(s[i]);
	}
}

int main()
{
	scanf("%[^\n]",s);
	init();
	printf("%.2f\n",qpop());//小数点后保留两位有效数字
	return 0;
}