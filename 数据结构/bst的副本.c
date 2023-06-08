#include <stdio.h>//71066001-陈伟杰
#include <stdlib.h>

typedef int DataType;

typedef struct Node *BinTree;
typedef BinTree TreeLink;
typedef struct Node TreeNode;

int num,maxn,id;

struct Node{
	DataType data;
	TreeLink Left,Right;
	int cnt;
};

BinTree Insertn(DataType x,BinTree BST,int cnt){//插入
	if(!BST){
		BST=(TreeNode*)malloc(sizeof(TreeNode));//结点
		BST->data=x;
		BST->Left=BST->Right=NULL; 
		BST->cnt=cnt;
	}else{
		num++;
		if(x<BST->data) BST->Left=Insertn(x,BST->Left,cnt+1);//左子结点值小于根结点值
		else if(x>BST->data) BST->Right=Insertn(x,BST->Right,cnt+1);//右子结点值大于根结点值
	}
	return BST;
}

BinTree Insert(DataType x,BinTree BST,int cnt){
	if(!BST){
		BST=(TreeNode*)malloc(sizeof(TreeNode));
		BST->data=x;
		BST->Left=BST->Right=NULL; 
		BST->cnt=cnt;
	}else{
		num++;
		if(x<BST->data) BST->Left=Insert(x,BST->Left,cnt+1);//输入的整数等于BST树中某结点值时，该结点的出现次数加1
		else if(x>BST->data) BST->Right=Insert(x,BST->Right,cnt+1);
	}
	return BST;
}

void BeOrder(BinTree T){
	if(T==NULL)return;
	else{		
		BeOrder(T->Left);
		BeOrder(T->Right);
	}
	if(T->cnt>maxn) {
		maxn=T->cnt;
		id=T->data;
	}
} 

void print(BinTree T) {
	printf("%d ",T->data);
	if(T->data==id) exit(0);
	else{		
		print(T->Left);
		print(T->Right);
	}
}


int main(int argc, char *argv[]) {
	BinTree Tree=NULL;
	int n,i,m;
	scanf("%d",&m);
	for(i=0;i<m;i++){
		scanf("%d",&n);
		Tree=Insert(n,Tree,0);
	}
	BeOrder(Tree);
	printf("%d\n",num);
	print(Tree);
	return 0;
}
