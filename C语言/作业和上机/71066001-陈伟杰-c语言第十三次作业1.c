#include <stdio.h>

struct info{
	char id[21];
	char name[21];
	double a,b,c;
}info[5];

int i;
void input()
{
	while(i<5&&scanf("%s%s%lf%lf%lf",info[i].id,info[i].name,&info[i].a,&info[i].b,&info[i].c)!=EOF)
		i++;
}
void output(){
	int cnt=0;
	while (cnt<i){
		printf("num:%s\n",info[cnt].id);
		printf("name:%s\n",info[cnt].name);
		printf("total:%.2f\n",(info[cnt].a+info[cnt].b+info[cnt].c));
		printf("average:%.2f\n",(info[cnt].a+info[cnt].b+info[cnt].c)/3);
		printf("\n");
		cnt++;
	}
}
int main ()
{
	input();
	output();
	return 0;
}