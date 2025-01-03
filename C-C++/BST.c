#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Node structure
struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
};

// Global variable for the root of the BST
struct Node *root = NULL;

// Function to create a new node
struct Node *createNode(int data)
{
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
    return newNode;
}

// Function to insert a new node in the BST
struct Node *insert(struct Node *root, int data)
{
    if (root == NULL)
        return createNode(data);
    else if (data < root->data)
        root->left = insert(root->left, data);
    else if (data > root->data)
        root->right = insert(root->right, data);
}

// Function to traverse the BST in Inorder
void inorderTraversal(struct Node *root)
{
    if (root != NULL)
    {
        inorderTraversal(root->left);
        printf("%d ", root->data);
        inorderTraversal(root->right);
    }
}

// Function to traverse the BST in Preorder
void preorderTraversal(struct Node *root)
{
    if (root != NULL)
    {
        printf("%d ", root->data);
        preorderTraversal(root->left);
        preorderTraversal(root->right);
    }
}

// Function to traverse the BST in Postorder
void postorderTraversal(struct Node *root)
{
    if (root != NULL)
    {
        postorderTraversal(root->left);
        postorderTraversal(root->right);
        printf("%d ", root->data);
    }
}

void traversal(struct Node *root)
{
    printf("\nPreorder traversal : ");
    preorderTraversal(root);
    printf("\nInorder traversal : ");
    inorderTraversal(root);
    printf("\nPostorder traversal : ");
    postorderTraversal(root);
    printf("\n");
}

// Function to search for a key in the BST
int search(struct Node *root, int key)
{
    if (root == NULL)
        return 0;
    if (root->data == key)
        return 1;
    if (key < root->data)
        search(root->left, key);
    else
        search(root->right, key);
}

int main()
{
    int choice, key, n, data;
    do
    {
        printf("\n----- MENU -----\n");
        printf("1. Create\n");
        printf("2. Traversal\n");
        printf("3. Search\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            // Input the number of nodes
            root = NULL;
            printf("Enter the number of nodes: ");
            scanf("%d", &n);

            // Input the nodes
            printf("Enter %d nodes: ", n);
            for (int i = 0; i < n; i++)
            {
                scanf("%d", &data);
                if (search(root, data))
                {
                    printf("Cannot have duplicate values\n");
                    i--;
                    continue;
                }
                root = insert(root, data);
            }
            break;
        case 2:
            printf("Traversal: ");
            traversal(root);
            printf("\n");
            break;
        case 3:
            printf("Enter the element to search: ");
            scanf("%d", &key);
            if (search(root, key))
                printf("Element found");
            else
                printf("Element not found");
            break;
        case 4:
            exit(0);
        default:
            printf("Invalid choice! Please enter a valid choice.\n");
        }
    } while (choice != 4);

    return 0;
}
