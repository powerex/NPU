package ua.azbest.study.algorithms.sorting;

import ua.azbest.study.algorithms.sorting.addons.Example;

/**
 * Created by AZbest on 27.09.2015.
 */
public class MergeEx extends Example {

    private static int CUTOFF = 7;

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux,int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        if (!less(a[mid+1], a[mid])) return;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)               aux[k] = a[j++];
            else if (j > hi)                aux[k] = a[i++];
            else if (less(aux[j], aux[i]))  aux[k] = a[j++];
            else                            aux[k] = a[i++];
    }

}
