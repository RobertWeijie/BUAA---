#include <stdio.h>
void swap(int *a, int *b)/*交换两个数的值*/{
    int t = *a;
    *a = *b;
    *b = t;
}
void sort(int *a, int n)/*这里采用冒泡排序*/{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - 1; j++) {
            if (a[j] > a[j + 1])
                swap(&a[j], &a[j + 1]);//如果当前数组元素大于后面那一个 则换位置，达到一个排序效果
        }
}
void output(int *a,int n) {
    for (int i = 0; i < n;i++)
    {
        if(a[i]==a[i+1]||a[i]==a[i-1]) // 如果当前数组元素和前或后一个元素相同则说明重复
        {
            printf("%d ", a[i]);// 输出当前重复的数组元素
        }
    }
    printf("\n");
}
int main() {
    int n;
    scanf("%d", &n);// 读入数组大小
    int a[n];
    for (int i = 0; i < n;i++) //读入数组
        scanf("%d", &a[i]);
    sort(a, n);//调用sort函数
    output(a, n);//调用output函数
    return 0;
}