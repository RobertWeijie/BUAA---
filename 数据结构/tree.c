#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>

char g[110],k[110];

struct Node
{
	char m[110];
	int t;
	struct Node *l;//左子树
	struct Node *r;//右子树
}a,*tree;

void insert(char *c)
{
	struct Node *p=malloc(sizeof(a));
	p->l=NULL;
	p->r=NULL;
	p->t=1;
	strcpy(p->m,c);
	struct Node *now=tree;
	if (tree==NULL)
	{
		tree=p;
		return;
	}
	while (1)
	{
		if (strcmp(p->m,now->m)<0)
		{
			if (now->l==NULL)
			{
				now->l=p;
				break;
			}
			else now=now->l;
		}
		if (strcmp(p->m,now->m)>0)
		{
			if (now->r==NULL)
			{
				now->r=p;
				break;
			}
			else now=now->r;
		}
		if (strcmp(p->m,now->m)==0)
		{
			now->t++;
			break;
		}
	}
}

void find(struct Node *root)//查找
{
	if (root==NULL) return;
	find(root->l);//查找左子树
	printf("%s %d\n",root->m,root->t);
	find(root->r);//查找右子树
}

void output()
{
	printf("%s",tree->m);
	if (tree->r!=NULL) printf(" %s",tree->r->m);
	if (tree->r!=NULL&&tree->r->r!=NULL) printf(" %s",tree->r->r->m);
	printf("\n");
	find(tree);
}

int main()
{
	FILE *in=fopen("article.txt","r");//打开当前目录下文件article.txt
	while (fscanf(in,"%s",g)!=EOF)
	{
		int i,lth=strlen(g),ans=0;
		for (i=0;i<lth;i++)
		{
			if (g[i]>='A'&&g[i]<='Z')
				g[i]=g[i]-'A'+'a';
			if (g[i]>='a'&&g[i]<='z')
			{
				k[ans]=g[i];
				ans++;
			}
			else if (ans!=0)
			{
				k[ans]='\0';
				insert(k);
				ans=0;
			}
			else if (ans==0)
				continue;
		}
		if (ans!=0)
		{
			k[ans]='\0';
			insert(k);
			ans=0;
		}
	}
	output();
}