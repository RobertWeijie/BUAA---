#include<stdio.h>

int main(){
    int n,i,j;
    scanf("%d",&n);//表示 n，0<n<30，
    
    int h[i];//苹果的高度 
    
    int p;//手伸直能达到的最大高度
    int c=0;//摘到的苹果个数 
    int m;//厘米高的板凳
    for(int i=0;i<10;i++)
        {
        scanf("%d",&h[i]);//输入苹果高度
    }
    scanf("%d",&p);//输入手伸直能达到的最大高度
    scanf("%d",&m);//输入多少厘米高的板凳
    for(int n=0;n<10;n++){
        if(h[n]<=(p+m)){
            c++;
        }        
    }
    printf("%d",c);//输出能够摘到的苹果 的数目。
    return 0;
}
