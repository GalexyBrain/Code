#include <stdio.h>
#include <stdlib.h>

int key, n, m;
int *ht, index;
int count = 0;

void insert(int key)
{
    index = key % m;
    while (ht[index] != -1)
    {
        index = (index + 1) % m;
    }
    ht[index] = key;
    count++;
}

void display()
{
    if (count == 0)
    {
        printf("\nHash Table is empty");
        return;
    }
    printf("\nHash Table contents are:\n ");
    for (int i = 0; i < m; i++)
        printf("\n T[%d] --> %d ", i, ht[i]);
}
void main()
{
    printf("\nEnter the number of employee  records (N) :   ");
    scanf("%d", &n);

    printf("\nEnter the total number of records in the hash table (m) :   ");
    scanf("%d", &m);

    ht = (int *)malloc(m * sizeof(int));
    for (int i = 0; i < m; i++)
        ht[i] = -1;
    printf("\nEnter the four digit key values (K) for N Employee Records:\n  ");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &key);
        insert(key);
    }

    display();
}
