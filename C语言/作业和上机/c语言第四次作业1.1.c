#include <stdio.h>
int main ()
{
    int hour;         //小时是整数
    float money;     //金钱是要float类型
    scanf ("%d",&hour);
    if (hour <=15)         
    {
        money=hour*0.4463;     //若在 15 小时及以 下，按每小时 0.4463 元收取
        printf("%.1f",money);
    }
    if(hour>16 && hour<=40)    
    {
        money=hour*0.4663; //若在 16~40 小时范围按每小时 0.4663 元执行
        printf("%.1f",money);
    }
    if (hour>41)
    {
        money=hour*0.5663; //41 小时及以上按 每小时 0.5663 元执行
        printf("%.1f",money);
    }
    return 0;
}


