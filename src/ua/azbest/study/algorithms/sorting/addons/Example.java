package ua.azbest.study.algorithms.sorting.addons;

/**
 * Created by AZbest on 18.09.2015.
 */
public class Example {
    public static void sort(Comparable[] a)
    {

    }

    /** return v < w */
    public static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    /**
     * @param a array of elements
     * @param i number of first element
     * @param j number of seond element
     */
    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a)
    {
        for (int i=0; i<a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i=0; i<a.length; i++)
            if (less(a[i],a[i-1])) return false;
        return true;
    }

}
