#include<stdio.h>
int function2(int* a, int n) //编写函数 function2
{
	int temp; int m;
	m=m / 2;
	for (int i = 0; i < n; i++)
		{
		for (int j = 0; j < n - 1; j++) 
			{
			if (a[j] > a[j + 1])
				{
temp = a[j]; a[j] = a[j + 1]; a[j + 1] = temp;//排序
			}
		}
	}
	for (int i = 0; i < n; i++)
		{
		for (int j = 0; j < n - 1; j++) 
			{
			if (a[j] % 2 == 0) //判断奇偶数
				{
				temp = a[j + 1]; a[j + 1] = a[j]; a[j] = temp;//排序
			}
		}
	} 
return *a;
}
int main() {
	int a[10] = { 5,7,9,1,3,2,4,10,6,8 }; 
	function2(a, 10);
	for (int i = 0; i < 10; i++) 
		{
		printf("%d ", a[i]);
	} 
return 0;
}
