#include<stdio.h>
int main()
{
 int n,k,count,i,j;
 scanf("%d%d",&n,&k);
 for(i=1;i<=n;i++)//编号1至n
 {
  count=0;
  for(j=1;j<=k;j++)
  {
   if(i%j==0)count=count+1;
  }
  if(count%2==1)printf("%d ",i);//为满足要求%d后面有一个空格！！！！
 }
 return 0;
}
