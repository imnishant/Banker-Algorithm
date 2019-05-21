import java.io.*;
import java.util.*;

public class banker
{
    static void safeSequence(int work[], boolean finish[], int need[][], int allocation[][], int n, int m)
    {
        int seq[] = new int[n];
        int ind = 0;
        boolean flag = true;
        while(flag == true)
        {
            flag = false;
            for(int i=0 ; i<n ; i++)
            {
                if(finish[i] == false)
                {
		    int j;
                    for(j=0 ; j<m ; j++)
                    {
                        if(need[i][j] > work[j])
                            break;
                    }
                    if(j == m)
                    {
                        for(j=0 ; j<m ; j++)
                            work[j] += allocation[i][j];
		                System.out.println(i+1);
                        finish[i] = true;
                        seq[ind] = i+1;
                        ind++;
                        flag = true;
                    }
                }
            }
        }
        if(ind == n)
        {
            System.out.println("Safe sequence is ");
            for(int i=0 ; i<n ; i++)
                System.out.print(seq[i] + " ");
        }
        else
            System.out.println("No safe sequence");
    }
    static void request(int p, int[] req, int need[][], int available[], int allocation[][], int m)
    {
        for(int i=0 ; i<m ; i++)
        {
            if(req[i] > need[p][i])
            {
                System.out.println("Exceeded max claim");
                return;
            }
            if(req[i] > available[i])
            {
                System.out.println("Resource not available");
                return;
            }
            need[p][i] -= req[i];
            available[i] -= req[i];
            allocation[p][i] += req[i];
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of process");
        int n = sc.nextInt();
        System.out.println("Enter no. of resources");
        int m = sc.nextInt();
        boolean finish[] = new boolean[n];
        int available[] = new int[m];
        int work[] = new int[m];
        int allocation[][] = new int[n][m];
        int max[][] = new int[n][m];
        int total[] = new int[m];
        int need[][] = new int[n][m];
        System.out.println("Enter allocation");
        for(int i=0 ; i<n ; i++)
            for(int j=0 ; j<m ; j++)
                allocation[i][j] = sc.nextInt();
        System.out.println("Enter max");
        for(int i=0 ; i<n ; i++)
        {
            for(int j=0 ; j<m ; j++)
            {
                max[i][j] = sc.nextInt();
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        System.out.println("Enter total instance of each resource");
        for(int i=0 ; i<m ; i++)
        {
            total[i] = sc.nextInt();
            int sum = 0;
            for(int j=0 ; j<n ; j++)
                sum += allocation[j][i];
            available[i] = total[i] - sum;
            work[i] = available[i];
        }
        safeSequence(work, finish, need, allocation, n, m);

        System.out.println("Enter process");
        int p = sc.nextInt();
        System.out.println("Enter resource instance");
        int req[] = new int[m];
        for(int i=0 ; i<m ; i++)
            req[i] = sc.nextInt();
        request(p, req, need, available, allocation, m);
	finish = new boolean[n];
        safeSequence(available, finish, need, allocation, n, m);

    }
}
