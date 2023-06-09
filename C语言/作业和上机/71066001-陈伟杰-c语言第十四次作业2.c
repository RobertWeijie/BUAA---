#include<stdio.h>
#include<stdlib.h>
#define SIZE 5
struct Student_type {
	int num;
	char name[10];
	float score1;
	float score2;
	float score3;
	float avg;
}stud[SIZE];

void save(){
	FILE *fp;
	int i;
	if((fp=fopen("stu_sort.dat","wb"))==NULL){
		printf("can't open file!\n");
		exit(0);}
	for(i=0;i<SIZE;i++){
		if(fwrite(&stud[i],sizeof(struct Student_type),1,fp)!=1){
			printf("file write error\n");}}
	fclose(fp);}
int main(){
    FILE *fp;
	int i,j;
	struct Student_type temp;
	fp=fopen("stud.dat","rb");
    for(i=0;i<SIZE;i++){
		fread(&stud[i],sizeof(struct Student_type),1,fp);}
	fclose(fp);
	for(i=0;i<SIZE-1;i++){
		for(j=i+1;j<SIZE;j++){
			if(stud[i].avg>stud[j].avg){
				temp=stud[i];
				stud[i]=stud[j];
				stud[j]=temp;}}	
	}
	save();
	fp=fopen("stu_sort.dat","rb");
	for(i=0;i<SIZE;i++){//为了验证磁盘文件stu_sort中是否已存在此数据，用for语句从stu_sort文件中读入数据到stud数组，然后向屏幕上输出
        fread(&stud[i],sizeof(struct Student_type),1,fp);
		printf("%-4d %-10s %-10.2f %-10.2f %-10.2f %-10.2f\n",stud[i].num,stud[i].name,stud[i].score1,stud[i].score2,stud[i].score3,stud[i].avg);}
	fclose(fp);
	return 0;}


