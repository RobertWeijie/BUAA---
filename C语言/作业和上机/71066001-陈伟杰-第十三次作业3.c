#include <stdio.h>
int main ()
{
	int a[100]={0},b[100]={0};
	int i,j,N,t,flag,cnt=0;
	scanf("%d",&N);
	for(i=0;i<N;i++) scanf("%d",&a[i]);//我的马的能力指数
	for(i=0;i<N;i++) scanf("%d",&b[i]);//对手的马的能力指数
	for(i=0;i<N;i++) {
		flag=1;
		for(j=0;j<N-1;j++) {
			if(a[j-1]<a[j]){
				flag=0;
				t=a[j-1];
				a[j-1]=a[j];
				a[j]=t;
			}
			if(b[j-1]<b[j]){
			flag=0;
			t=b[j-1];
			b[j-1]=b[j];
			b[j]=t;
			}
		}if (flag==1) break;
	}
	for (i=0;i<N;i++){
		for(j=0;j<N;j++){
			if(a[j]>b[i]&&a[j]>=0){//你的马能力值大于对方的才算赢
				cnt++;
				a[j]=-1;
			}
		}
	}
	printf("%d",cnt);
	return 0;
}