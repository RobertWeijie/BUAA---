#include <stdio.h>
#define MAX 100
void MatrixMutiply(int m,int n,int p,int lMatrix1[MAX][MAX],
int lMatrix2[MAX][MAX],int lMatrixResult[MAX][MAX])
{
int i,j,k;
long lSum;
/*嵌套循环计算结果矩阵（m*p）的每个元素*/
for(i=0;i<m;i++)
  for(j=0;j<p;j++){
  lSum=0; /*按照矩阵乘法的规则计算结果矩阵的i*j元素*/
   for(k=0;k<n;k++)
    lSum+=lMatrix1[i][k]*lMatrix2[k][j];
   lMatrixResult[i][j]=lSum;}
}
int main()
{
int lMatrix1[MAX][MAX],lMatrix2[MAX][MAX];
int lMatrixResult[MAX][MAX],lTemp;
int i,j,m,n,p;

scanf("%d",&m);/*输入两个矩阵的的行列数*/
scanf("%d",&n);
scanf("%d",&n);
scanf("%d",&p);
  for(i=0;i<m;i++)/*输入第一个矩阵的每个元素*/
  for(j=0;j<n;j++){
   scanf("%d",&lTemp);
   lMatrix1[i][j]=lTemp;}

for(i=0;i<n;i++)/*输入第二个矩阵的每个元素*/
  for(j=0;j<p;j++){
   scanf("%d",&lTemp);
   lMatrix2[i][j]=lTemp;
  }

MatrixMutiply(m,n,p,lMatrix1,lMatrix2,lMatrixResult);/*调用函数进行乘法运算，结果放在lMatrixResult 中*/
for(i=0;i<m;i++)/*打印输出结果矩阵*/{
  for(j=0;j<p;j++)
   printf("%d ",lMatrixResult[i][j]);
  printf("\n");
}
  return 0;
}