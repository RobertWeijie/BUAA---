#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int n;

struct Node
{
	char	name[20];       /* 姓名由不超过20个英文小写字母组成 */
	char	tel[11];        /* 电话号码由11位数字字符组成 */
	int	num;
} a[99];                        /* 输入的姓名和电话号码项不超过100个。 */

void init()
{
	scanf( "%d", &n );
	int i;
	for ( i = 1; i <= n; i++ )
	{
		scanf( "%s", a[i].name );       /* 输入名字 */
		scanf( "%s", a[i].tel );        /* 输入电话号码 */
		a[i].num = 1;
		int j;
		for ( j = 1; j < i; j++ )
			if ( a[j].num > 0 && strcmp( a[i].name, a[j].name ) == 0 )
			{
				if ( strcmp( a[i].tel, a[j].tel ) == 0 )
				{
					a[i].num = 0; break;
				}else a[i].num++;
			}
	}
	for ( i = 1; i <= n; i++ )
		if ( a[i].num > 1 ) /* 第一次重复的姓名后面加英文下划线字符_和数字1 */
		{
			int lth = strlen( a[i].name );
			(a[i].name)[lth]	= '_';
			(a[i].name)[lth + 1]	= a[i].num - 1 + '0';
			(a[i].name)[lth + 2]	= '\0';
		}
}


void choose_sort()
{
	int i, j;
	for ( i = 1; i <= n; i++ )
		for ( j = 1; j < i; j++ )
			if ( a[i].num > 0 && a[j].num > 0 )
			{
				if ( strcmp( a[i].name, a[j].name ) < 0 )
				{
					struct Node tmp = a[i];
					a[i]	= a[j];
					a[j]	= tmp;
				}
			}
}


void stddout()
{
	int i;
	for ( i = 1; i <= n; i++ )
		if ( a[i].num > 0 )
			printf( "%s %s\n", a[i].name, a[i].tel );
	/* 从小到大排序，并输出排序后的电话号码簿 */
}


int main()
{
	init();
	choose_sort();
	stddout();
	return(0);
}