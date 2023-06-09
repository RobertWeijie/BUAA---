#include <stdio.h>
#include <string.h>

int ispalindrome(char s[1000])//保证密文长度不超过 10000，判断一个数字是否是回文数字
{
	int i,j;
	for(i=0,j=strlen(s)-1;i<j;i++,j--)
	{
		if(s[i]!=s[j])
			return 0;//如果不是返回0
	}
	return 1;//如果是返回1
}

int main ()
{
	char encrypt[10000];
	int option,i;
	gets(encrypt);
	option = ispalindrome(encrypt);
	if (option==1)//当密文为回文字符串时，明文为密文所有偶数位置上的字符按顺序组成的新字符串
	{
		for (i=1;i<strlen(encrypt);i=i+2)
		{
			printf("%c",encrypt[i]);
		}
	}
	else if (option==0){//当密文不为回文字符串时， 明文为密文所有奇数位置上的字符按顺序组成的新字符串
		for (i=0;i<strlen(encrypt);i=i+2)
		{
			printf("%c",encrypt[i]);
		}
	}
	return 0;
}