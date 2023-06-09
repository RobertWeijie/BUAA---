#include<stdio.h> 
int main() 
{ 
int n,m; 
double n1,m1,o1; 
double fact(int n); 
printf("\n"); 
scanf("%d%d",&n,&m); 
n1=fact(n); 
m1=fact(m); 
o1=fact(n-m); 
printf("%0.0f\n",n1/(m1*o1)); 
} 
double fact(int n)//求阶乘 
{ 
	
    int i; 
    double sum;
	sum=1; 
	for(i=1;i<=n;i++) 
{ 
sum=sum*i; 
} 
return sum; 
}  
