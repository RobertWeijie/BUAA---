#include <stdio.h>
int isprime(int n)

{
	
	if (n == 1)
		
		return 0;
	
	for (int i = 2; i <= n / 2; i++)
		
		{
			if (n%i == 0)
				return 0;
		}
	
	return 1;
}

int main()

{
	int num;
	scanf("%d",&num);
	
	while (num != -1)
		
		{
			if (isprime(num))
				printf("yes ");
			else
			printf("no ");
			scanf("%d",&num);
		}
	return 0;
}

