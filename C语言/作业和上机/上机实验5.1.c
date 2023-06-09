#include<stdio.h>
int main(){
    int a[10];//十本书
    double b;//手伸直能达到的最大高度
    int c=0;//拿到的书本数目
    for(int i=0;i<10;i++){
        scanf("%d",&a[i]);//输入十本书到地面的高度
    }

    scanf("%lf",&b);//输入把手伸直的时候能够达到的最大高度
    b=b*100+30;
    for(int n=0;n<10;n++){
        if(a[n]<=b){
            c++;
        }        
    }
    printf("%d",c);
    return 0;
}