package ua.azbest.study.algorithms.unionfind;

/**
 * Created by AZbest on 18.09.2015.
 */
public class WeighedQuickUnionC extends WeighedQuickUnion {

    WeighedQuickUnionC(int N) {
        super(N);
    }

    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

}
