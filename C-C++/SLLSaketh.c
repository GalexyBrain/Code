#include <stdio.h>
#include <stdlib.h>

struct node
{
    char usn[100];
    char name[100];
    char branch[100];
    int sem;
    int phone;
    struct node *next;
};

struct node *head, *temp, *newnode, *tail;

void create()
{
    int n, i;

    printf("Enter number of nodes: ");
    scanf("%d", &n);

    head = NULL;

    for (i = 0; i < n; i++)
    {
        newnode = (struct node *)malloc(sizeof(struct node));

        printf("Enter usn, name, branch, sem and phone: ");
        scanf("%s %s %s %d %d", newnode->usn, newnode->name, newnode->branch, &newnode->sem, &newnode->phone);

        newnode->next = NULL;

        if (head == NULL)
        {
            head = newnode;
            tail = newnode;
        }
        else
        {
            tail->next = newnode;
            tail = newnode;
        }
    }
}

void display()
{
    temp = head;
    int count = 0;

    if (temp == NULL)
    {
        printf("List is empty\n");
        return;
    }

    while (temp != NULL)
    {
        printf("%s %s %s %d %d\n", temp->usn, temp->name, temp->branch, temp->sem, temp->phone);
        temp = temp->next;
        count++;
    }
    printf("Number of nodes: %d", count);
}

void insert_end()
{
    newnode = (struct node *)malloc(sizeof(struct node));

    printf("Enter usn, name, branch, sem and phone: ");
    scanf("%s %s %s %d %d", newnode->usn, newnode->name, newnode->branch, &newnode->sem, &newnode->phone);

    newnode->next = NULL;
    if (head == NULL)
    {
        head = newnode;
        tail = newnode;
        return;
    }
    tail->next = newnode;
    tail = newnode;
}

void delete_end()
{
    if (head == NULL)
    {
        printf("List is empty\n");
        return;
    }
    else if (head->next == NULL)
    {
        free(head);
        head = tail = NULL;
        return;
    }
    temp = head;
    while (temp->next != tail)
    {
        temp = temp->next;
    }

    tail = temp;
    temp = temp->next;
    tail->next = NULL;

    free(temp);
}

void insert_front()
{
    newnode = (struct node *)malloc(sizeof(struct node));

    printf("Enter usn, name, branch, sem and phone: ");
    scanf("%s %s %s %d %d", newnode->usn, newnode->name, newnode->branch, &newnode->sem, &newnode->phone);
    if (head == NULL)
    {
        head = newnode;
        tail = newnode;
        return;
    }

    newnode->next = head;
    head = newnode;
}

void delete_front()
{
    if (head == NULL)
    {
        printf("List is empty\n");
        return;
    }
    else if (head->next == NULL)
    {
        free(head);
        head = tail = NULL;
        return;
    }
    temp = head;
    head = head->next;
    free(temp);
}

void stackdemo()
{
    int ch;
    while (1)
    {
        printf("\n~~Stack Demo using SLL\n");
        printf("\n1:Push operation \n2: Pop operation \n3: Display \n4:Exit \n");
        printf("\nEnter your choice for stack demo");
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
            display();
            break;
        default:
            return;
        }
    }
    return;
}

int main()
{
    int choice;

    while (1)
    {
        printf("\n*MENU*\n");
        printf("1. Create\n");
        printf("2. Display\n");
        printf("3. Insert at end\n");
        printf("4. Delete from end\n");
        printf("5. Insert at front\n");
        printf("6. Delete from front\n");
        printf("7. Stack Demo using SLL(Insertion and Deletion at Front)");
        printf("8. Exit\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice)
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
            stackdemo();
            break;
        case 8:
            exit(0);
        default:
            printf("Invalid choice\n");
        }
    }

    return 0;
}