#include<stdio.h>

int main()

{ 
  int n,k=0,i,a[1000];
  double aver=0,upperaver=0;

  scanf("%d",&n);//输入一个整数 n，1≤n≤1000

  for(i=0; i<n; i++)

  { 
    scanf("%d",&a[i]);//输入成绩
    aver=aver+1.0*a[i];


  }
  aver=aver/n;//平均分的计算
   for (i=0;i<n;i++)
    {
      if(a[i]>=aver)//大于等于平均分的同学的平均分
        {
        upperaver=upperaver+a[i];
        k++;
      }
    }

  printf("%.2f",upperaver/k);//成绩除了那个n数，保留两位小数

  return 0;

}