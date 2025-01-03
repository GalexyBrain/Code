#include <stdio.h>
#include <stdlib.h>

int prec(char symbol)
{
    switch (symbol)
    {
    case '#':
        return -1;

    case '(':
    case ')':
        return 0;

    case '+':
    case '-':
        return 1;

    case '*':
    case '/':
    case '%':
        return 2;

    case '^':
    case '$':
        return 3;
    }
}

void main()
{
    char infix[30], postfix[30], stack[30];
    int top = -1;

    printf("\nEnter the valid infix expression : ");
    scanf("%s", infix);
    int postfixIndex = 0;
    char current, temp;

    stack[++top] = '#';

    for (int i = 0; infix[i] != '\0'; i++)
    {
        current = infix[i];
        switch (current)
        {
        case '(':
            stack[++top] = current;
            break;

        case ')':
            temp = stack[top--];
            while (temp != '(')
            {
                postfix[postfixIndex++] = temp;
                temp = stack[top--];
            }
            break;

        case '+':
        case '-':
        case '*':
        case '/':
        case '%':
        case '^':
        case '$':
            while (prec(stack[top]) >= prec(current))
                postfix[postfixIndex++] = stack[top--];

            stack[++top] = current;
            break;

        default:
            postfix[postfixIndex++] = current;
        }
    }
    while (stack[top] != '#')
    {
        postfix[postfixIndex++] = stack[top--];
    }
    postfix[postfixIndex] = '\0';

    printf("\nThe entered infix expression is :\n %s \n", infix);
    printf("\nThe corresponding postfix expression is :\n %s \n", postfix);
}