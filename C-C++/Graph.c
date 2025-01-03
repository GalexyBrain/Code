#include <stdio.h>
#include <stdlib.h>

int a[50][50], n, visited[50];
int q[50], front = -1, rear = -1;

void bfs(int v)
{
    int cur;
    visited[v] = 1;
    q[++rear] = v;
    while (front != rear)
    {
        cur = q[++front];
        for (int i = 1; i <= n; i++)
        {
            if ((visited[i] == 0) && (a[cur][i] == 1))
            {
                q[++rear] = i;
                visited[i] = 1;
                printf("%d ", i);
            }
        }
    }
}

void dfs(int v)
{
    visited[v] = 1;
    for (int i = 1; i <= n; i++)
    {
        if (visited[i] == 0 && a[v][i] == 1)
        {
            printf("%d ", i);
            dfs(i);
        }
    }
}

int main()
{
    int start;
    printf("\nEnter the number of vertices in graph:  ");
    scanf("%d", &n);
    printf("\nEnter the adjacency matrix:\n");
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
            scanf("%d", &a[i][j]);
    }

    for (int i = 1; i <= n; i++)
        visited[i] = 0;

    printf("\nEnter the starting vertex: ");
    scanf("%d", &start);

    printf("\nNodes reachable from starting vertex %d using BFS: ", start);
    bfs(start);

    for (int i = 1; i <= n; i++)
        visited[i] = 0;

    printf("\nNodes reachable from starting vertex %d using DFS: ", start);
    dfs(start);
}