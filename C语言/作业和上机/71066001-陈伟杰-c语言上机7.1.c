#include<stdio.h>#include<string.h>struct Stu{
char name[16];
int id;
int score;
}stu[100000];

int main()
{
int N;
int i,max;
scanf("%d",&N);for(i=0;i<N;i++){
scanf("%s%d%d",stu[i].name,&stu[i].id,&stu[i].score);
if(i==0)max=i;
else if(stu[i].score>stu[max].score)max=i;
else if(stu[i].score==stu[max].score&&stu[i].id<stu[max].id)max=i;
	}
printf("%s %d %d",stu[max].name,stu[max].id,stu[max].score);
return 0;
}
