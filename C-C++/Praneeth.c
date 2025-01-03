#include <stdio.h>
struct tree
{
    struct tree *left;
    int data;
    struct tree *right;
};