package ua.azbest.study.algorithms.unionfind;

/**
 * Created by AZbest on 18.09.2015.
 */
public class WeighedQuickUnion extends UF {

    protected int[] sz;

    WeighedQuickUnion(int N) {
        super(N);
        sz = new int[N];
        for (int i: sz)
            sz[i] = 1;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }


}
