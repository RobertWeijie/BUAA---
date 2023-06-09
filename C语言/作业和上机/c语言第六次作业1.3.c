#include<stdio.h>int count(int num,int x){ int count=0;  while(num!=0){     if(num%10==x) count++;//求余观察个位数是否为x 
      num=num/10;//一位数一位数的算
     } return count;}int main(){ int a,b,x,i; int con=0; scanf("%d %d %d",&a,&b,&x); for(i=a;i<=b;i++){  con+=count(i,x); }  printf("%d",con); return 0;}
