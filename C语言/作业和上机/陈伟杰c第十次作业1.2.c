#include <stdio.h>
#include<string.h>
struct List
{
	char name[100];//学生名字
	int score;//成绩
}list[50];//建个结构体

int main (){
	int n,i,j,t;
	char s[100];
	scanf("%d",&n);//表示人的数量。 N<50
	for (i=0;i<n;i++){
		gets(s);
		if (s[0]=='\0'){
			i=i-1;
			continue;
		}
		strcpy(list[i].name,s);//字符串复制
		scanf("%d",&list[i].score);//输入成绩
	}
	for (i=0;i<n;i++){
		for (j=i+1;j<n;j++){
			if(list[i].score<list[j].score){//如果上一位的成绩低于下一位
				t=list[j].score;
				list[j].score=list[i].score;
				list[i].score=t;
				strcpy(s,list[i].name);
				strcpy(list[i].name,list[j].name);
				strcpy(list[j].name,s);}
		}
	}
	for (i=0;i<n;i++){
		printf("%s\n",list[i].name);
		printf("%d\n",list[i].score);}
	return 0;}
