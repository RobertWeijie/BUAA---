#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>

int main(){
    char s[1000],tmp[1000];
    int flag,i,b=0,kd=0,len;
    double avg=0;
    int in[104];
    double dn[104];//数据保证所有的数字大于−105，小于105，且数字总数不超过104。
    gets(s);
    len=strlen(s);//获取长度
    while(len>0){
        flag=0;
        for(i=0;s[i]!=' '&&s[i]!='\0';i++)if(s[i]=='.')flag=1;
        if(flag){
            dn[kd++]=atof(s);
        }else if(!flag){
            in[b++]=atoi(s);
        }
        if(s[i]=='\0')break;
        strcpy(tmp,&s[i+1]);
        strcpy(s,tmp);
        len=strlen(s);}
    for(i=0;i<b;i++){
        avg=avg+(double)in[i];
    }
    printf("%d %.5f\n",b,avg/b*1.0);//第一行，一个整数与一个浮点数，中间用空格隔开，分别代表整数的个数与整数的平均值。
    avg=0;
    for(i=0;i<kd;i++){
        avg=avg+dn[i];}
    printf("%d %.5f\n",kd,avg/kd*1.0);//第二行，一个整数与一个浮点数，中间用空格隔开，分别代表小数的个数与小数的平均值,平均值保留五位小数。
    return 0;
}