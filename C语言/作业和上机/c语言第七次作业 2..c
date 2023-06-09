#include <stdio.h>

int main()
{
	int n,i,a,t,guessrank,correct,rank[20];//有 20 人参加
	int correctguess[1000]={0};//一个整数 n(1≤n≤1000)
	scanf("%d",&n);
	for (i=0;i<20;i++)
	{
		rank[i]=i+1;
	}
	for (i=0;i<n;i++)
	{
		correct=0;
		for(a=0;a<20;a++)
		{
			scanf("%d",&guessrank);
			if (guessrank==rank[a])
			{
				correct++;
			}
			
		}
		correctguess[i]=correct;
	}
	for(i=0;i<n;i++)
	{
		for(a=i+1;a<n;a++)
		{
			if (correctguess[i]<correctguess[a])
			{
				t=correctguess[i];
				correctguess[i]=correctguess[a];
				correctguess[a]=t;
			}
		}
	}
	for(i=0;i<n;i++)
	{
		printf("%d\n",correctguess[i]);
	}
	return 0;
}
	