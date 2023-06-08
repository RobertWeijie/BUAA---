#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

char	c[1100], d[1100];
int	now = 1;
struct Node
{
	char	w[30];
	int	ti;
} a[10003];

void solve()
{
	int i;
	for ( i = 0; i < strlen( c ); i++ )
	{
		if ( c[i] >= 'A' && c[i] <= 'Z' )
			c[i] = c[i] - 'A' + 'a';
		if ( c[i] < 'a' || c[i] > 'z' )
			continue;
		int j, k = 0;
		for ( j = i; j < strlen( c ); j++ )
		{
			if ( c[j] >= 'A' && c[j] <= 'Z' )
				c[j] = c[j] - 'A' + 'a';
			if ( c[j] >= 'a' && c[j] <= 'z' )
			{
				d[k] = c[j];
				k++;
				d[k] = '\0';
			}else break;
		}
		i = j;

		int t;
		for ( t = 1; t < now; t++ )
			if ( strcmp( a[t].w, d ) == 0 )
			{
				a[t].ti++;
				break;
			}
		if ( t == now )
		{
			a[now].ti = 1;
			int p;
			for ( p = 0; p < strlen( d ); p++ )
				a[now].w[p] = d[p];
			now++;
		}
	}
}


void sortt()
{
	int i, j;
	for ( i = 1; i < now; i++ )//如出现这个单词则加1
		for ( j = 1; j < now; j++ )
			if ( i != j )
				if ( strcmp( a[i].w, a[j].w ) < 0 )
				{
					char tmp[1100];
					strcpy( tmp, a[i].w );//字符串复制
					strcpy( a[i].w, a[j].w );
					strcpy( a[j].w, tmp );
					int tmpt = a[i].ti;
					a[i].ti = a[j].ti;
					a[j].ti = tmpt;
				}
}


void printff()
{
	int i;
	for ( i = 1; i < now; i++ )
		printf( "%s %d\n", a[i].w, a[i].ti );
}


int main()
{
	FILE *in = fopen( "article.txt", "r" );//打开当前目录下文件“article.txt”
	while ( ~fscanf( in, "%s", c ) )
		solve();
	sortt();
	printff();
	fclose( in );//结束此文件阅读
	return(0);
}