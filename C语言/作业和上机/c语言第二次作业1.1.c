#include <stdio.h> 
int main( )
{ 
	int a=3,b=5,c,j,k;
	j=3; k=++j; 
	printf("j is %d and k is %d\n",j,k); 
	k=j++; 
	printf("j is %d and k is %d\n",j,k); 
	printf("%d \n",++j); 
	printf("%d \n",j++);
	return 0;
}