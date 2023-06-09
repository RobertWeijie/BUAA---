#include <stdio.h>
#include <math.h>
#define mm 1e-8//要求误差小于1e-8
double bsearch(double left, double right)
{
double ans, mid;//mid=x

mid = (left + right)/2;
ans = 1-mid-sin(mid);//1-x-sin(x) 二分法
if (ans <= mm && ans>= -mm) return mid;
if (ans > mm) return bsearch(mid+1e-8, right);
else return bsearch(left, mid-1e-8);//要求误差小于1e-8
} 

int main()
{
double x;
x = bsearch(-1.0, 1.0);//在(-1,1)之间的根
printf(" %.8lf\n", x);//保留8位输出解。
getchar();
return 0;
}