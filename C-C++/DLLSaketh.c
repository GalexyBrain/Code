#include <stdio.h>
#include <stdlib.h>

struct node
{
    int phone;
    char empid[100];
    char name[100];
    char dept[100];
    char designation[100];

    struct node *prev;
    struct node *next;
};
struct node *head, *temp, *new, *tail;

void create()
{
    int n, i;

    head = tail = NULL;

    printf("Enter number of nodes: ");
    scanf("%d", &n);

    for (i = 0; i < n; i++)
    {
        new = (struct node *)malloc(sizeof(struct node));

        printf("Enter name empid  dept designation and phone number:");
        scanf("%s %s %s %s %d", new->name, new->empid, new->dept, new->designation, &new->phone);

        new->prev = NULL;
        new->next = NULL;

        if (head == NULL)
        {
            head = new;
            tail = new;
        }

        else
        {
            tail->next = new;
            new->prev = tail;
            tail = new;
        }
    }
}

void insert_front()
{
    new = (struct node *)malloc(sizeof(struct node));
    printf("Enter name empid  dept designation and phone number:");
    scanf("%s %s %s %s %d", new->name, new->empid, new->dept, new->designation, &new->phone);

    if (head == NULL)
    {
        head = tail = new;
        return;
    }

    new->prev = NULL;
    new->next = head;
    head->prev = new;
    head = new;
}

void insert_end()
{
    new = (struct node *)malloc(sizeof(struct node));
    printf("Enter name empid  dept designation and phone number:");
    scanf("%s %s %s %s %d", new->name, new->empid, new->dept, new->designation, &new->phone);

    if (head == NULL)
    {
        head = tail = new;
        return;
    }

    new->next = NULL;
    new->prev = tail;
    tail->next = new;
    tail = new;
}

void delete_front()
{
    if (head == NULL)
    {
        printf("Empty LL");
        return;
    }
    else if (head->next == NULL)
    {
        head = tail = NULL;
        return;
    }
    temp = head;
    head = head->next;
    temp->next = NULL;
    head->prev = NULL;
}

void delete_end()
{
    if (head == NULL)
    {
        printf("Empty LL");
        return;
    }
    else if (head->next == NULL)
    {
        head = tail = NULL;
        return;
    }
    temp = tail;
    tail = tail->prev;
    temp->prev = NULL;
    tail->next = NULL;
}

void display()
{
    if (head == NULL)
    {
        printf("Empty LL");
        return;
    }
    temp = head;
    while (temp != NULL)
    {
        printf("%s %s %s %s %d\n", temp->name, temp->empid, temp->dept, temp->designation, temp->phone);
        temp = temp->next;
    }
}

void deqdemo()
{
    int ch;
    while (1)
    {
        printf("\nDemo Double Ended Queue Operation");
        printf("\n1:InsertQueueFront\n 2: DeleteQueueFront\n 3:InsertQueueRear\n 4:DeleteQueueRear\n 5:DisplayStatus\n 6: Exit \n");
        scanf("%d", &ch);

        switch (ch)
        {
        case 1:
            insert_front();
            break;
        case 2:
            delete_front();
            break;
        case 3:
            insert_end();
            break;
        case 4:
            delete_end();
            break;
        case 5:
            display();
            break;
        default:
            return;
        }
    }
}

void main()
{
    int ch;
    while (1)
    {
        printf("\n\n~~Menu");
        printf("\n1:Create DLL of Employee Nodes");
        printf("\n2:DisplayStatus");
        printf("\n3:InsertAtEnd");
        printf("\n4:DeleteAtEnd");
        printf("\n5:InsertAtFront");
        printf("\n6:DeleteAtFront");
        printf("\n7:Double Ended Queue Demo using DLL");
        printf("\n8:Exit \n");
        printf("\nPlease enter your choice: ");
        scanf("%d", &ch);

        switch (ch)
        {
        case 1:
            create();
            break;
        case 2:
            display();
            break;
        case 3:
            insert_end();
            break;
        case 4:
            delete_end();
            break;
        case 5:
            insert_front();
            break;
        case 6:
            delete_front();
            break;
        case 7:
            deqdemo();
            break;
        case 8:
            exit(0);
            break;
        default:
            printf("\nPlease Enter the valid choice");
        }
    }
}