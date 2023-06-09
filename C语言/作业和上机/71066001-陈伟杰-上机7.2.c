#include<stdio.h>
#include<string.h> 
#include<stdlib.h> 
struct Stu
{
char name[16]; 
int id;
int score;
}stu[100000];
int main ()
{
  FILE *fp;
  int N;
  int i,max;
  if((fp=fopen("in.txt","r"))==NULL)exit(EXIT_FAILURE);
		fscanf(fp,"%d",&N);
  for(i=0;i<N;i++){
		fscanf(fp,"%s%d%d",stu[i].name,&stu[i].id,&stu[i].score);
		if(i==0)max=i;
		else if(stu[i].score>stu[max].score)max=i;
		else if(stu[i].score==stu[max].score&&stu[i].id<stu[max].id)max=i; 
	}
	fclose(fp);
	if((fp=fopen("out.txt","w"))==NULL)exit(EXIT_FAILURE);
fprintf(fp,"%s %d %d",stu[max].name,stu[max].id,stu[max].score); 
fclose(fp);
return 0;
}