package ua.azbest.study.algorithms.sorting;

import ua.azbest.study.aditionals.StdRandom;
import ua.azbest.study.algorithms.sorting.addons.Example;

/**
 * Created by AZbest on 01.10.2015.
 */
public class Quick extends Example {

    private static int M = 7;

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + M) { // improve (general case hi <= lo)
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo ,j);
        return j;
    }

}
