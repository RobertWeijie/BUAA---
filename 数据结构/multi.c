#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

struct Node
{
	int		coef, expp;//指数和系数
	struct Node	*next;//指针域
} a, *lista, *listb, *listc;//多项式

void create( int x )
{
	int		coe, ex;
	char		ch;
	struct Node	*now;
	if ( x == 1 )
		now = lista;
	if ( x == 2 )
		now = listb;
	while ( 1 )//当前节点不为空
	{
		scanf( "%d%d%c", &coe, &ex, &ch );//输入项的系数和指数
		struct Node *p = malloc( sizeof(a) );//申请一个新结点
		p->coef = coe;
		p->expp = ex;//更新节点数据
		p->next = NULL;//插入节点
		if ( x == 1 )
		{
			if ( lista == NULL )
				lista = p;
			else 
				now->next = p;
			now = p;
		}
		if ( x == 2 )
		{
			if ( listb == NULL )
				listb = p;
			else 
				now->next = p;
			now = p;
		}
		if ( ch == '\n' )
			return;
	}
}


void multi() /* 相乘 */
{
	struct Node *now = listc, *now1 = lista, *now2 = listb;
	while ( now1 != NULL )
	{
		while ( now2 != NULL )
		{
			struct Node *p = malloc( sizeof(a) );//申请一个新结点
			p->coef = now1->coef * now2->coef;//系数相乘得到当前系数
			p->expp = now1->expp + now2->expp;//指数相乘得到当前指数
			p->next = NULL;
			if ( listc == NULL )
				listc = p;
			else
				now->next = p;
			now	= p;
			now2	= now2->next;
		}
		now2	= listb;
		now1	= now1->next;
	}
}


void comb()
{
	struct Node *now = listc;
	while ( now != NULL )
	{
		struct Node *bi = now;
		while ( bi->next != NULL )
		{
			if ( bi->next->expp == now->expp )
			{
				now->coef	+= bi->next->coef;
				bi->next	= bi->next->next;
			}      else
				bi = bi->next;
		}
		now = now->next;
	}
}


void sort()
{
	struct Node *now = listc;
	while ( now != NULL )
	{
		struct Node *bi = now->next;
		while ( bi != NULL )
		{
			if ( bi->expp > now->expp )
			{
				int t = bi->coef;
				bi->coef	= now->coef;
				now->coef	= t;
				t		= bi->expp;
				bi->expp	= now->expp;
				now->expp	= t;
			}else
				
				bi = bi->next;
		}
		now = now->next;
	}
}


void printff()
{
	struct Node *now = listc;
	while ( now != NULL )
		if ( now->coef != 0 )
		{
			printf( "%d %d ", now->coef, now->expp );
			now = now->next;
		}
}


int main()
{
	create( 1 );
	create( 2 );
	multi();
	comb();
	sort();
	printff();
	return(0);
}


