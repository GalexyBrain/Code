#include <stdio.h>
#include <string.h>

void stringReplace(char *string, char *pattern, char *replace, char *new)
{
    int current = 0, changed = 0;

    for (int i = 0; i < strlen(string); i++)
    {
        int found = 1;
        for (int j = 0; j < strlen(pattern); j++)
        {
            if (pattern[j] != string[i + j])
            {
                found = 0;
                break;
            }
        }

        if (!found)
        {
            new[current++] = string[i];
            continue;
        }

        for (int j = 0; j < strlen(replace); j++)
            new[current++] = replace[j];
        i += strlen(pattern) - 1;
        changed = 1;
    }
    new[current] = '\0';

    if (!changed)
        printf("Pattern not found in the string \n");
}

void main()
{
    char str[100], pat[100], rep[100], new[100];
    printf("Enter the string : ");
    scanf(" %[^\n]", str);
    printf("Enter the pattern : ");
    scanf(" %[^\n]", pat);
    printf("Enter the replacement string : ");
    scanf(" %[^\n]", rep);

    stringReplace(str, pat, rep, new);

    printf("The new string is : %s", new);
}