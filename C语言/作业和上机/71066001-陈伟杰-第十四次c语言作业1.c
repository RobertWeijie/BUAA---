#include<stdio.h>
#include<stdlib.h>
#define SIZE 5
struct Student_type
{
	int num;
	char name[10];
	float score1;
	float score2;
	float score3;
	float avg;
}stud[SIZE];

void save()  {//定义函数save，向文件输入SIZE个学生的数据
	FILE *fp;
	int i;
	if((fp=fopen("stud.dat","wb"))==NULL){
		printf("can't open file!\n");
		exit(0);}
	for(i=0;i<SIZE;i++){
		if(fwrite(&stud[i],sizeof(struct Student_type),1,fp)!=1){
			printf("file write error\n");}
	}
	fclose(fp);
}
int main(){
    FILE *fp;
	int i;
    for(i=0;i<SIZE;i++) { //计算每个学生的平均分
    	printf("请输入第%d个学生的学号、姓名和三门课的成绩:",i+1); 
		scanf("%d%s%f%f%f",&stud[i].num,stud[i].name,&stud[i].score1,&stud[i].score2,&stud[i].score3);
		stud[i].avg=(stud[i].score1+stud[i].score2+stud[i].score3)/3;}
	save();
	fp=fopen("stud.dat","rb");
	for(i=0;i<SIZE;i++) { //为了验证磁盘文件stu.dat中是否已存在此数据，用for语句从stu.dat文件中读入数据，然后向屏幕上输出
        fread(&stud[i],sizeof(struct Student_type),1,fp);
        printf("第%d个学生的学号、姓名和三门课的成绩以及三门课的平均分为:",i+1);
		printf("%-4d %-10s %-10.2f %-10.2f %-10.2f %-10.2f\n",stud[i].num,stud[i].name,stud[i].score1,stud[i].score2,stud[i].score3,stud[i].avg);}
	fclose(fp);//关闭文件stud，防止它被误用 
	return 0;}

