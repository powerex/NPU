package ua.azbest.study.algorithms.sorting;

import ua.azbest.study.algorithms.sorting.addons.Example;

/**
 * Created by AZbest on 18.09.2015.
 */
public class Selection extends Example {

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i=0; i<N; i++)
        {
            int min = i;
            for (int j=i+1; j<N; j++)
                if (less(a[j],a[min])) min = j;
            exch(a, i, min);
        }
    }
}
