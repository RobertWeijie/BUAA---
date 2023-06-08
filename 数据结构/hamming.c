#include<stdio.h>//71066001-陈伟杰
#include<string.h>

typedef struct node{
	char s1[100];
	char s2[100];//先定一两个字符串
	int n;
}NODE;

NODE m[400];
char ch[20][20];
int n;

void swap(NODE *a,NODE *b)//排序
{
    NODE temp;
    temp=*a;
    *a=*b;
    *b=temp;
}

void swap2(char *a,char *b)//排序
{
    char temp;
    temp=*a;
    *a=*b;
    *b=temp;
}

void quick_sort(NODE a[],int l,int r)//快速排序
{
    if(l>=r) return;
    int x=a[(l+r)/2].n;//二分查找方法
    int i=l-1;
    int j=r+1;
    while(i<j)
    {
        do{
            i++;
        }while(a[i].n>x);
        do{
            j--;
        }while(a[j].n<x);
        if(i<j) swap(&a[i],&a[j]);
    }
    quick_sort(a,l,j);
    quick_sort(a,j+1,r);
}

int main() {
	scanf("%d",&n);
	for(int i=0;i<n;i++) {
		scanf("%s",ch[i]);
	}
	for(int i=1;i<n;i++) {
		memcpy(m[i].s1,ch[0],sizeof m[i].s1);
		memcpy(m[i].s2,ch[i],sizeof m[i].s2);
		int cnt=0;
		for(int j=0;j<strlen(ch[i]);j++) {
			if(ch[0][j]!=ch[i][j]) cnt++;
		}
		m[i].n=cnt;
		//printf("%d\n",cnt);
	}
	
	quick_sort(m,1,n-1);
	for(int i=1;i<n;i++) {
		for(int j=i+1;j<n;j++) {
			if(m[i].n!=m[j].n) continue;
			if(strcmp(m[i].s2,m[j].s2)>0) swap(&m[i],&m[j]); 
		}
	}
	for(int i=1;i<n;i++) {
		printf("%s %s %d\n",m[i].s1,m[i].s2,m[i].n);
	}
	return 0;
}