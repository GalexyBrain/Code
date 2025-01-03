#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Structure to represent a term in the polynomial
struct Term
{
    int coefficient;
    int power_x;
    int power_y;
    int power_z;
    struct Term *next;
};

typedef struct Term Term;

// Function to create a new term
Term *createTerm(int coeff, int power_x, int power_y, int power_z)
{
    Term *newTerm = (Term *)malloc(sizeof(Term));

    newTerm->coefficient = coeff;
    newTerm->power_x = power_x;
    newTerm->power_y = power_y;
    newTerm->power_z = power_z;
    newTerm->next = NULL;
    return newTerm;
}

// Function to insert a term at the end of the polynomial
void insertTerm(Term **poly, int coeff, int power_x, int power_y, int power_z)
{
    Term *newTerm = createTerm(coeff, power_x, power_y, power_z);
    if (*poly == NULL)
    {
        *poly = newTerm;
        newTerm->next = *poly;
    }
    else
    {
        Term *temp = *poly;
        while (temp->next != *poly)
        {
            temp = temp->next;
        }
        temp->next = newTerm;
        newTerm->next = *poly;
    }
}

// Function to display the polynomial
void displayPoly(Term *poly)
{
    Term *temp = poly;
    if (temp == NULL)
    {
        printf("Polynomial is empty.\n");
        return;
    }
    do
    {
        printf("%dx^%dy^%dz^%d ", temp->coefficient, temp->power_x, temp->power_y, temp->power_z);
        if (temp->next != poly)
        {
            printf("+ ");
        }
        temp = temp->next;
    } while (temp != poly);
    printf("\n");
}

// Function to evaluate the polynomial for given values of x, y, and z
int evaluatePoly(Term *poly, int x, int y, int z)
{
    int result = 0;
    Term *temp = poly;
    if (temp == NULL)
    {
        printf("Polynomial is empty.\n");
        return result;
    }
    do
    {
        result += temp->coefficient * pow(x, temp->power_x) * pow(y, temp->power_y) * pow(z, temp->power_z);
        temp = temp->next;
    } while (temp != poly);
    return result;
}

// Function to add two polynomials and store the result in polysum
struct Term *add_polynomials(struct Term *poly1, struct Term *poly2)
{
    struct Term *result = NULL;

    if (poly1 == NULL && poly2 == NULL)
    {
        return NULL;
    }

    struct Term *temp1 = poly1;
    do
    {
        insertTerm(&result, temp1->coefficient, temp1->power_x, temp1->power_y, temp1->power_z);
        temp1 = temp1->next;
    } while (temp1 != poly1);

    struct Term *temp2 = poly2;
    do
    {
        struct Term *cur = result;
        int found = 0;
        do
        {
            if (cur->power_x == temp2->power_x && cur->power_y == temp2->power_y && cur->power_z == temp2->power_z)
            {
                cur->coefficient += temp2->coefficient;
                found = 1;
                break;
            }
            cur = cur->next;
        } while (cur != result);

        if (!found)
        {
            insertTerm(&result, temp2->coefficient, temp2->power_x, temp2->power_y, temp2->power_z);
        }
        temp2 = temp2->next;
    } while (temp2 != poly2);

    return result;
}

int main()
{
    Term *poly1 = NULL;
    Term *poly2 = NULL;
    Term *polySum = NULL;

    // Representing polynomial P(x,y,z) = 6x^2*y^2*z - 4y*z^5 + 3x^3*y*z + 2*x*y^5*z - 2*x*y*z^3
    insertTerm(&poly1, 6, 2, 2, 1);
    insertTerm(&poly1, -4, 0, 1, 5);
    insertTerm(&poly1, 3, 3, 1, 1);
    insertTerm(&poly1, 2, 1, 5, 1);
    insertTerm(&poly1, -2, 1, 1, 3);

    printf("Polynomial 1: ");
    displayPoly(poly1);

    // Evaluating polynomial P(x,y,z) for x=2, y=3, z=4
    int x = 2, y = 3, z = 4;
    printf("Value of polynomial 1 for x=%d, y=%d, z=%d: %d\n", x, y, z, evaluatePoly(poly1, x, y, z));

    // Representing polynomial POLY2(x,y,z) = x^2*y^3*z^4 + 2x^3*y^2*z^3 - xyz
    insertTerm(&poly2, 6, 2, 2, 1);
    insertTerm(&poly2, -4, 0, 1, 5);
    insertTerm(&poly2, 3, 1, 1, 1);
    insertTerm(&poly2, 2, 1, 5, 1);
    insertTerm(&poly2, -2, 1, 1, 3);

    printf("\nPolynomial 2: ");
    displayPoly(poly2);

    // Adding the polynomials
    polySum = add_polynomials(poly1, poly2);
    printf("\nSum of polynomial 1 and polynomial 2: ");
    displayPoly(polySum);

    return 0;
}
