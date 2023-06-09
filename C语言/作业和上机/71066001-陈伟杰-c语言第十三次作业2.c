#include <stdio.h>
struct node{
	char s[10];
	int chinese,math,english;
};

int main (){
	int n,i;
	scanf("%d",&n);
	struct node stu[n];
	for (i=0;i<n;i++){
		scanf("%s",stu[i].s);
		scanf("%d%d%d",&stu[i].chinese,&stu[i].math,&stu[i].english);
	}
	int max=0,k;
	for(i=0;i<n;i++){
		if(max<stu[i].chinese+stu[i].math+stu[i].english){
			max=stu[i].chinese+stu[i].math+stu[i].english;
			k=i;
		}
	}
	printf("%s %d %d %d",stu[k].s,stu[k].chinese,stu[k].math,stu[k].english);
	return 0;
}