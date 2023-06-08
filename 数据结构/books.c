#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

struct node{//节点的类型
    char name[100];
    char author[30];
    char publish[50];
    char date[20];
    struct node *next;//指向下一个节点的指针
};
//typedef : 数据类型重命名
typedef struct node node;
typedef struct node *node_list;

//int list_insert_pre_node_temp(node_list *head,node_list *p,int element);//指定结点的前插O(n)
int list_insert_pre_node(node_list p,char name[],char author[],char publish[],char date[]);//指定结点的前插O(1)
int list_insert_next_node(node_list p,char name[],char author[],char publish[],char date[]);//指定结点的后插

int list_delete_node_normal(node_list *head,node *p);//O(n)删除一个指定结点

void list_print(node_list temp);

node_list pubbleSort(node_list head);
int find_inf(char name[],char inf[]);

char name[100];
char author[30];
char publish[50];
char date[20];
char inf[100];

int main()
{
    FILE *sin,*sout;
    //sin=fopen("books.txt", "r");
    sin=fopen("books.txt", "r");
    sout=fopen("ordered.txt", "w");
    node_list head=(node *)malloc(sizeof(node));
    head->next=NULL;
    node *now,*tail=head;
    while((fscanf(sin,"%s%s%s%s",name,author,publish,date))!=EOF){
        //printf("%s %s %s %s\n",name,author,publish,date);
        now=(node *)malloc(sizeof(node));
        strcpy(now->name,name);
        strcpy(now->author,author);
        strcpy(now->publish,publish);
        strcpy(now->date,date);
        tail->next=now;
        tail=now;
    }
    tail->next=NULL;
    fclose(sin);
    //list_print(head);
    if(NULL!=head->next){
        head=pubbleSort(head);
        //list_print(head);
    }

    int op;
    while(1){
        scanf("%d",&op);
        if(op==1){//insert
            scanf("%s%s%s%s",name,author,publish,date);
            if(NULL==head->next){
                now=(node *)malloc(sizeof(node));
                strcpy(now->name,name);
                strcpy(now->author,author);
                strcpy(now->publish,publish);
                strcpy(now->date,date);
                head->next=now;
                tail=now;
                tail->next=NULL;
            }
            else{
                node *i=head->next;
                for(i;NULL!=i;i=i->next){
                    if(strcmp(i->name,name)>0){
                        list_insert_pre_node(i,name,author,publish,date);
                        //list_print(head);
                        break;
                    }
                    if(NULL==i->next){
                        list_insert_next_node(i,name,author,publish,date);
                        break;
                    }
                }
            }
        }
        else if(op==2){//find
            scanf("%s",inf);
            //printf("%s\n",inf);
            node *i=head->next;
            for(i;NULL!=i;i=i->next){
                if(find_inf(i->name,inf)){
                    printf("%-50s%-20s%-30s%s\n",i->name,i->author,i->publish,i->date);
                }
            }
        }
        else if(op==3){//delete
            scanf("%s",inf);
            node *i=head->next;
            for(i;NULL!=i;i=i->next){
                if(find_inf(i->name,inf)){
                    list_delete_node_normal(head,i);
                }
            }
        }
        else if(op==0){
            node_list temp=head;
            temp=temp->next;
            while(NULL!=temp){
                fprintf(sout,"%-50s%-20s%-30s%s\n",temp->name,temp->author,temp->publish,temp->date);
                temp=temp->next;
            }
            fclose(sout);
            break;
        }
    }
    return 0;
}

int find_inf(char name[],char inf[])
{
    int len_name=strlen(name);
    int len_inf=strlen(inf);
    for(int i=0;i<len_name;i++){
        if(name[i]==inf[0]){
            int look=0;
            while(look<len_inf){
                if(name[i+look]!=inf[look]){
                    break;
                }
                if(look==len_inf-1){
                    return 1;
                }
                look++;
            }
        }
    }
    return 0;
}

node_list pubbleSort(node_list head)
{
    node *i=head->next;
    for(i;NULL!=i;i=i->next){
        for(node *j=head->next;NULL!=j;j=j->next){
            if(strcmp(i->name,j->name)<0){
                node *temp=(node *)malloc(sizeof(node));
                strcpy(temp->name,i->name);
                strcpy(temp->author,i->author);
                strcpy(temp->publish,i->publish);
                strcpy(temp->date,i->date);

                strcpy(i->name,j->name);
                strcpy(i->author,j->author);
                strcpy(i->publish,j->publish);
                strcpy(i->date,j->date);

                strcpy(j->name,temp->name);
                strcpy(j->author,temp->author);
                strcpy(j->publish,temp->publish);
                strcpy(j->date,temp->date);
            }
        }
    }
    return head;
}

int list_insert_pre_node(node_list p,char name[],char author[],char publish[],char date[])//指定结点的前插O(1)
//偷天换日
{
    if(NULL==p) return 0;
    node *new_node=(node *)malloc(sizeof(node));
    if(NULL==new_node) return 0;
    new_node->next=p->next;
    p->next=new_node;
    strcpy(new_node->name,p->name);
    strcpy(new_node->author,p->author);
    strcpy(new_node->publish,p->publish);
    strcpy(new_node->date,p->date);
    strcpy(p->name,name);
    strcpy(p->author,author);
    strcpy(p->publish,publish);
    strcpy(p->date,date);
    return 1;
}

int list_insert_next_node(node_list p,char name[],char author[],char publish[],char date[])//指定结点的后插
{
    if(NULL==p) return 0;
    node *new_node=(node *)malloc(sizeof(node));
    if(NULL==new_node) return 0;

    strcpy(new_node->name,name);
    strcpy(new_node->author,author);
    strcpy(new_node->publish,publish);
    strcpy(new_node->date,date);
    new_node->next=p->next;
    p->next=new_node;
    return 1;
}

int list_delete_node_normal(node_list *head,node *p)//O(n)删除一个指定结点
{
    if(NULL==p) return 0;
    node_list pre;
    pre=head;
    while(pre->next!=p&&pre->next!=NULL){
        pre=pre->next;
    }
    if(pre->next!=NULL){
        pre->next=p->next;
        free(p);
    }
    return 1;
}

void list_print(node_list temp)
{
    temp=temp->next;
    while(temp!=NULL){
        printf("%s %s %s %s\n",temp->name,temp->author,temp->publish,temp->date);
        temp=temp->next;
    }
}
