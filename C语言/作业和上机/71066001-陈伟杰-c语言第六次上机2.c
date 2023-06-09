#include <stdio.h>
#include <string.h>
struct List
{
	char name[21];
	int mark;
}list[1000];

int main() {
	int i,j,k=0,flag,tmp,count=0;
	char t[21];
	while(scanf("%s%d",list[k].name,&list[k].mark)!=EOF){
		k++;
		if(list[k-1].mark>89)//大于90分就能入选
			count++;}
	for (i=0;i<k;k++){
		flag=1;
		for(j=1;j<k-1;j++){
			if (list[j-1].mark<list[j].mark){
				flag=0;
				strcpy(t,list[j-1].name);
				strcpy(list[j-1].name,list[j].name);
				strcpy(list[j].name,t);
				tmp=list[j].mark;
				list[j].mark=list[j-1].mark;
				list[j-1].mark=tmp;
			}else if(list[j-1].mark==list[j].mark&&strcmp(list[j-1].name,list[j].name)>0){
				flag=0;
				strcpy(t,list[j-1].name);
				strcpy(list[j-1].name,list[j].name);
				strcpy(list[j].name,t);}
		}if (flag==1)
			break;}
	printf("%d\n",count);
	for(i=0;i<count;i++){
		printf("%s %d\n",list[i].name,list[i].mark);
	} return 0;
}