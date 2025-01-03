#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct term
{
    int coef, x, y, z;

    struct term *next;
};

struct term *create(int coef, int x, int y, int z)
{
    struct term *new = (struct term *)malloc(sizeof(struct term));
    new->coef = coef;
    new->x = x;
    new->y = y;
    new->z = z;
    new->next = NULL;

    return new;
}

struct term *insert(struct term *head, int coef, int x, int y, int z)
{
    struct term *new = create(coef, x, y, z);

    if (head == NULL)
    {
        new->next = new;
        return new;
    }
    else
    {
        struct term *temp = head;
        while (temp->next != head)
            temp = temp->next;
        temp->next = new;
        new->next = head;
        return head;
    }
}

void displayPoly(struct term *poly)
{
    struct term *temp = poly;
    if (temp == NULL)
    {
        printf("Polynomial is empty.\n");
        return;
    }
    do
    {
        printf("%dx^%dy^%dz^%d ", temp->coef, temp->x, temp->y, temp->z);
        if (temp->next != poly)
        {
            printf("+ ");
        }
        temp = temp->next;
    } while (temp != poly);
    printf("\n");
}

int evaluatePoly(struct term *poly, int x, int y, int z)
{
    int result = 0;
    struct term *temp = poly;
    if (temp == NULL)
    {
        printf("Polynomial is empty.\n");
        return result;
    }

    do
    {
        result += temp->coef * pow(x, temp->x) * pow(y, temp->y) * pow(z, temp->z);
        temp = temp->next;
    } while (temp != poly);
    return result;
}

struct term *addPoly(struct term *poly1, struct term *poly2)
{
    struct term *result = NULL, *temp1 = poly1, *temp2 = poly2;

    if (poly1 == NULL && poly2 == NULL)
    {
        return NULL;
    }

    do
    {
        result = insert(result, temp1->coef, temp1->x, temp1->y, temp1->z);
        temp1 = temp1->next;
    } while (temp1 != poly1);

    do
    {
        struct term *temp = result;
        int found = 0;

        do
        {
            if (temp->x == temp2->x && temp->y == temp2->y && temp->z == temp2->z)
            {
                temp->coef += temp2->coef;
                found = 1;
                break;
            }
            temp = temp->next;
        } while (temp != result);

        if (!found)
        {
            insert(result, temp2->coef, temp2->x, temp2->y, temp2->z);
        }
        temp2 = temp2->next;
    } while (temp2 != poly2);

    return result;
}

int main()
{
    struct term *poly1 = NULL;
    struct term *poly2 = NULL;
    struct term *polySum = NULL;

    poly1 = insert(poly1, 6, 2, 2, 1);
    poly1 = insert(poly1, -4, 0, 1, 5);
    poly1 = insert(poly1, 3, 3, 1, 1);
    poly1 = insert(poly1, 2, 1, 5, 1);
    poly1 = insert(poly1, -2, 1, 1, 3);

    printf("Polynomial 1: ");
    displayPoly(poly1);

    int x = 2, y = 3, z = 4;
    printf("Value of polynomial 1 for x=%d, y=%d, z=%d: %d\n", x, y, z, evaluatePoly(poly1, x, y, z));

    poly2 = insert(poly2, 6, 2, 2, 1);
    poly2 = insert(poly2, -4, 0, 1, 5);
    poly2 = insert(poly2, 3, 3, 1, 1);
    poly2 = insert(poly2, 2, 1, 5, 1);
    poly2 = insert(poly2, -2, 1, 1, 3);

    printf("\nPolynomial 2: ");
    displayPoly(poly2);

    polySum = addPoly(poly1, poly2);
    printf("\nSum of polynomial 1 and polynomial 2: ");
    displayPoly(polySum);

    return 0;
}