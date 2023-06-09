#include<stdio.h>
#include<string.h> 
#include<stdlib.h> 
struct Stu
{
char name[16]; 
int id;
int score; 
}stu[100000],t; 
int main()
{
    FILE *fp;
	int N;
	int i,j,flag;
	if((fp=fopen("in.txt","r"))==NULL)exit(EXIT_FAILURE);
    fscanf(fp,"%d",&N);
    for(i=0;i<N;i++){
   fscanf(fp,"%s%d%d",stu[i].name,&stu[i].id,&stu[i].score);
}

   fclose(fp);
   for(i=0;i<N;i++){
    flag=1;
    for(j=1;j<N-i;j++){
    if(stu[j-1].score<stu[j].score){
		flag=0;
    t=stu[j-1]; 
	stu[j-1]=stu[j];
    stu[j]=t;
}
	else if(stu[j-1].score==stu[j].score&&stu[j-1].id>stu[j].id){flag=0;
	t=stu[j-1]; stu[j-1]=stu[j];
    stu[j]=t;
}
}
    if(flag)break;
	}
    if((fp=fopen("out.txt","w"))==NULL)exit(EXIT_FAILURE);
     for(i=0;i<N;i++){
	fprintf(fp,"%s %d %d\n",stu[i].name,stu[i].id,stu[i].score);
}
fclose(fp);
return 0;
}