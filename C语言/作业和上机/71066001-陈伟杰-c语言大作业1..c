#include<stdio.h>
#include<math.h>
//erf是误差函数
int main()
{
    double low=-8.66, mid, high=8.66;
    int n,i;
    double ans,a;//浮点数a
    double b[1000];//计算的次数不超过1000
    scanf("%d",&n);//表示需要计算的次数
    for(i=0;i<n;i++){
        scanf("%lf",&a);//每行一个浮点数a
        while(low<=high){
            mid=(low+high)/2.0;
            ans=0.5-0.5*(erf(mid/sqrt(2)))-a;//这个是那个方程，求解过程
            if(ans<0)high=mid-0.00000000001;
            
                else if (ans>0)low=mid+0.00000000001;
            
                else if(ans==0)
                break;
        }
        b[i]=mid;
        high=4;
        low=-4;
    }
    for(i=0;i<n;i++){
        printf("%.8f\n",b[i]);//保留8位小数
    }
    return 0;
}