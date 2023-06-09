#include <stdio.h>
#include <stdlib.h>

int main()
{
   char ch;
   printf("Please enter a char:");
   while((ch=getchar())!=EOF)
   {
      if(ch>='0'&&ch<='9')
   {
       printf("%c是数字字符：",ch);
   }
   else if(ch>='a'&&ch<='z')
   {
       printf("%c是小写字母:",ch);
   }
   else if(ch>='A'&&ch<='Z')
   {
       printf("%c是大写字母:",ch);
   }
   else if(ch==' ')
   {
       printf("%c是空格:",ch);
   }
   else
   {
       printf("%c是其它字符:",ch);
   }
   }
   return 0;
}