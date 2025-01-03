#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void selection_sort(int arr[], int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        int min = i;

        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] < arr[min])
            {
                min = j;
            }
        }
        if (min != i)
        {
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}

void random_number(int arr[], int n)
{
    for (int i = 0; i < n; i++)
    {
        arr[i] = rand() % 10000;
    }
}

void display_array(int arr[], int n)
{
    for (int i = 0; i < n; i++)
    {
        printf("%5d\n", arr[i]);
    }
}

int main()
{
    FILE *fp;
    double start, end;
    int arr[500000], ch;
    int n;
    while (1)
    {
        printf("1)plot the graph\n2)selection sort\n3)exit\n");
        printf("Enter ur choice:\n");
        scanf("%d", &ch);

        switch (ch)
        {
        case 1:
            fp = fopen("a.dat", "w");
            if (fp == NULL)
            {
                printf("error");
                return 1;
            }

            for (int i = 5000; i <= 50000; i += 1000)
            {
                random_number(arr, i);
                start = clock();
                selection_sort(arr, i);
                end = clock();
                fprintf(fp, "%d\t %fl\n", i, (double)(end - start) / CLOCKS_PER_SEC);
            }
            fclose(fp);
            break;

        case 2:
            printf("Enter the no. of elements:");
            scanf("%d", &n);

            random_number(arr, n);

            printf("\nunsorted array\n");
            display_array(arr, n);

            printf("\nsorted array\n");

            start = clock();
            selection_sort(arr, n);
            end = clock();

            display_array(arr, n);
            printf("time taken: %fl seconds", (double)(end - start) / CLOCKS_PER_SEC);
            break;

        case 3:
            return 0;
            break;
        }
    }
}