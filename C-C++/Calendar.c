#include <stdio.h>
#include <stdlib.h>

struct day
{
    char *name;
    int date;
    char *desc;
};

struct day *create()
{
    struct day *calendar = (struct day *)malloc(sizeof(struct day) * 7);
    for (int i = 0; i < 7; i++)
    {
        calendar[i].name = (char *)malloc(sizeof(char) * 25);
        calendar[i].desc = (char *)malloc(sizeof(char) * 50);
    }

    return calendar;
}

void read(struct day *calendar)
{
    for (int i = 0; i < 7; i++)
    {
        printf("Enter details for day %d\n", i + 1);
        printf("Enter the day name : ");
        scanf(" %[^\n]", calendar[i].name);
        printf("Enter the date : ");
        scanf("%d", &calendar[i].date);
        printf("Enter the description : ");
        scanf(" %[^\n]", calendar[i].desc);
    }
}

void display(struct day *calendar)
{
    printf("Day\t\tDate\tDescription\n");
    for (int i = 0; i < 7; i++)
        printf("%-11s%8d%16s\n", calendar[i].name, calendar[i].date, calendar[i].desc);
}

void main()
{
    struct day *calendar = create(calendar);
    read(calendar);
    display(calendar);

    for (int i = 0; i < 7; i++)
    {
        free(calendar[i].name);
        free(calendar[i].desc);
    }
    free(calendar);
}