#include <stdio.h>
#include <stdlib.h>
#define N 6
 struct student{
    int num;
    char name[10];
    float score[3];
    float aver;
}stu[N];
 int main(){
    FILE *fp;int i,j;struct student temp;
    if((fp=fopen("stu_sort.dat","rb"))==NULL){
        printf("cannot open the file stu_sort!\n");
        exit(0);}
    for(i=0;i<5;i++){
        fread(&stu[i],sizeof(struct student),1,fp);}
    fclose(fp);
    printf("请输入你要追加的学生信息(学号，姓名，三门课程的成绩):\n");
    scanf("%d %s %f %f %f",&stu[5].num,stu[5].name,&stu[5].score[0],&stu[5].score[1],&stu[5].score[2]);
    stu[5].aver=(stu[5].score[0]+stu[5].score[1]+stu[5].score[2])/3.0;            //到此stu内就存有6个结构了 
    for(i=0;i<5;i++)
     for(j=i+1;j<6;j++)
         if(stu[i].aver<stu[j].aver){
        temp=stu[i];
        stu[i]=stu[j];
        stu[j]=temp;}
    FILE *p1;
    if((p1=fopen("stu_new","wb"))==NULL){
        printf("cannot open the file stu_new!\n");
        exit(0);}
    for(i=0;i<N;i++){
        if((fwrite(&stu[i],sizeof(struct student),1,p1))!=1)
            printf("flie write error!\n");}
    fclose(p1);   //到此 将stu输入到stu_new内 
    FILE *p2;
    if((p2=fopen("stu_new","rb"))==NULL){
        printf("cannot open the file stu_sort!\n");
        exit(0);}
    printf("the data of stu_new:\n");
    printf("num name score1 score2 score3 average\n");
    for(i=0;i<N;i++){
        fread(&stu[i],sizeof(struct student),1,p2);
        printf("%-2d%5s%7.1f%7.1f%7.1f%7.1f\n",stu[i].num,stu[i].name,stu[i].score[0],stu[i].score[1],stu[i].score[2],stu[i].aver);}   
    fclose(p2);}