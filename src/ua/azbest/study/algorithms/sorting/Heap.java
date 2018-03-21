package ua.azbest.study.algorithms.sorting;


import ua.azbest.study.algorithms.sorting.addons.Example;

/**
 * Created by AZbest on 01.10.2015.
 */
public class Heap extends Example {

    private static void sink(Comparable[] a, int i, int k)
    {
        int N = a.length;
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k,j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1)
        {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

}
