package ua.azbest.study.algorithms.unionfind;

/**
 * Created by AZbest on 18.09.2015.
 */
public class QuickUnion extends UF {
    QuickUnion(int N) {
        super(N);
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count--;
    }
}
