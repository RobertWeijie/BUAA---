#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

char	c[220];
int	v[220];
char	m[140] = { 0 };

struct Node
{
	char ch1;
	struct Node	*next;
} a, *list;                                     /* 链表实现 */

void delete_same()                              /* 先将密钥中的重复字符去掉 */
{
	int i, j;
	for ( i = 0; i < strlen( c ); i++ )
	{
		if ( c[i] < 32 || c[i] > 126 )  /* ASCII码编码值32-126为可见字符 */
			continue;
		v[c[i]] = 1;
		for ( j = i + 1; j < strlen( c ); j++ )
			if ( c[j] == c[i] )
			{
				int k;
				for ( k = j; k < strlen( c ); k++ )
					c[k] = c[k + 1];
				j--;
			}
	}
}


void create()
{
	scanf( "%[^\n]", c );
	delete_same();
	int		i;
	struct Node	*now = list;
	for ( i = 0; i < strlen( c ); i++ )
	{
		struct Node *p = malloc( sizeof(a) );
		p->ch1	= c[i];
		p->next = list;
		if ( list == NULL )
			list = p;
		else
			now->next = p;
		now = p;
	}
	for ( i = 32; i <= 126; i++ ) /* 连成一个由可见字符组成的环 */
		if ( !v[i] )
		{
			struct Node *p = malloc( sizeof(a) );
			p->ch1	= i;
			p->next = list;
			if ( list == NULL )
				list = p;
			else
				now->next = p;
			now = p;
		}
}


void map()
{
	struct Node *now = list;
	while ( now->next != list )
		now = now->next;
	char delet = list->ch1, delet1 = list->ch1;
	now->next	= list->next;
	list		= now;
	int i;
	for ( i = 1; i <= (int) (delet) - 1; i++ )
		now = now->next;
	m[delet] = now->next->ch1;
	while ( list->next != list )
	{
		delet		= now->next->ch1;
		now->next	= now->next->next;
		list		= now;
		for ( i = 1; i <= (int) (delet) - 1; i++ )
			now = now->next;
		m[delet] = now->next->ch1;
	}
	for ( i = 32; i <= 126; i++ )
		if ( !m[i] )
			m[i] = delet1;
} /* 设原密钥的第一个字符（即环的起始位置）作为环的开始位置标识，先从环中删除第一个字符（位置标识则移至下一个字符），再沿着环从下一个字符开始顺时针以第一个字符的ASCII码值移动该位置标识至某个字符，则该字符成为第一个字符的密文字符；然后从环中删除该字符，再从下一个字符开始顺时针以该字符的ASCII码值移动位置标识至某个字符，找到该字符的密文字符；依次按照同样方法找到其它字符的密文字符。当环中只剩一个字符时，则该剩下的最后一个字符的密文为原密钥的第一个字符。 */


int main()
{
	FILE	*in	= fopen( "in.txt", "r" ); /* 待加密文件为当前目录下的in.txt文 */
	FILE	*out	= fopen( "in_crpyt.txt", "w" ); /* 加密后生成的密文文件为当前目录下的in_crpyt.txt */
	create();
	map();
	char ch;
	while ( ~fscanf( in, "%c", &ch ) )
	{
		if ( ch >= 32 && ch <= 126 )
			fprintf( out, "%c", m[ch] );
		else fprintf( out, "%c", ch );
	}
	fclose( in );
	fclose( out );
	return(0);
}