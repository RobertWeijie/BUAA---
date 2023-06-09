#include <stdio.h>

int main()
{

 int n,i,max=0,times=0,a,b;
 scanf("%d",&n);

 for(i=0;i<n;i++){
  scanf("%d",&b);
  if (a<b) times=times+5;//如果前面那个数据小于后面那个 时间加5秒
  
    else if 
      (a>=b)times=0;
      a=b;

  if(max<times){
   max=times;
  }
 }
 printf("%d",max);
 return 0;
}