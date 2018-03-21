package ua.azbest.study.algorithms.unionfind;

/**
 * Created by AZbest on 18.09.2015.
 */
public class QuickFind extends UF {

    QuickFind(int N) {
        super(N);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        for (int i: id) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }
}
