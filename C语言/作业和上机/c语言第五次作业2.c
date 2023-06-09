#include <stdio.h>

int main(void)
{
    int a,b,c;
    scanf("%d%d%d",&a,&b,&c);//输入三条边的长度

    if ((a==b)||(a==c)||(b==c))
    printf("a triangle\nan isosceles triangle");//如果可以 组成三角形，判断是否为等腰三角形

else if (a*a+b*b==c*c||a*a+c*c==b*b||b*b+c*c==a*a)
    printf("a right triangle\n");//组成直角三角形
    
else 
    printf("not a triangle\n");//无法组成三角形
    
return 0;
}

