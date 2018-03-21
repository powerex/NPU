package ua.azbest.study.algorithms.unionfind;

/**
 * Created by AZbest on 17.09.2015.
 */
public class UF {

    protected int[] id;

    protected int count;

    UF(int N) {
        count = N;
        id = new int[N];
        for (int i=0; i<N; i++ ) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    void union(int p, int q) {
    }

    public int find(int p) {
        return -1;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void printId() {
        for (int i: id) {
            System.out.print(id[i] + " ");
        }
    }

}
