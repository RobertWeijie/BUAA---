#include <stdio.h> 
int main()  
{  
    int a,b,c;  
    scanf("%d%d", &a,&b);  //输入a元b角 a必须<=10000,b<=9
    c=(10*a+b)/19;
    printf("%d\n", c);
    return 0;
}