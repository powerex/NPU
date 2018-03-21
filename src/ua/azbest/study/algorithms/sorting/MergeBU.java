package ua.azbest.study.algorithms.sorting;

import ua.azbest.study.algorithms.sorting.addons.Example;

/**
 * Created by AZbest on 27.09.2015.
 */
public class MergeBU extends Example {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[a.length];
        for (int  sz = 1; sz < N; sz *= 2)
            for (int lo = 0; lo < N - sz; lo += 2*sz)
                merge(a, lo, lo+sz-1, Math.min(lo+2*sz-1, N-1));
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        System.arraycopy(a, 0, aux, 0, a.length);
        /*for (int k = 0; k <= hi; k++)
            aux[k] = a[k];*/

        for (int k = 0; k <= hi; k++)
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
    }
}
