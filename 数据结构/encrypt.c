#include <stdio.h>
#include <string.h>
#include<math.h>
 
int main()
{
    char secretword[50];
    char secretkey[26];
    char str[100];
    char ch;
    int i,j,flag,k=0;
    FILE *fp1=fopen("encrypt.txt", "r");
    FILE *fp2=fopen("output.txt", "w");
    gets(secretword);
//    fscanf(fp,"%s",str);
    fgets(str,100000,fp1);
    //去重
    for(i=0;i<strlen(secretword);i++)
    {
        flag=0;
        for(j=0;j<i;j++)
        {
            if(secretword[i]==secretword[j])
                flag=1;
        }
        if(flag==0)
            secretkey[k++]=secretword[i];
    }
    //补全密钥
    j=k;
    for(ch='z';ch>='a';ch--)
    {
        flag=0;
        for(i=0;i<k;i++)
        {
            if(secretkey[i]==ch)
                flag=1;
        }
        if(flag==0)
                secretkey[j++]=ch;
    }
    //加密输出
    for(i=0;i<strlen(str);i++)
    {
        if(str[i]>='a'&&str[i]<='z')
            fprintf(fp2,"%c",secretkey[str[i]-'a']);
        //            printf("%c",secretkey[str[i]-'a']);
        else
        fprintf(fp2,"%c",str[i]);
//            printf("%c",str[i]);
    }
    return 0;
}
