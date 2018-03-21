package ua.azbest.study.algorithms.unionfind;

import java.util.Scanner;

/**
 * Created by AZbest on 18.09.2015.
 */
public class TestClass {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();

        UF qf = new QuickFind(N);
        UF qu = new QuickUnion(N);
        UF wu = new WeighedQuickUnion(N);
        UF wuc = new WeighedQuickUnionC(N);

        while (reader.hasNext()) {
            int p = reader.nextInt();
            int q = reader.nextInt();
            if (qf.connected(p, q)) continue;
            qf.union(p, q);
            qu.union(p, q);
            wu.union(p, q);
            wuc.union(p, q);
        }
        System.out.println(wu.count + " components");

        qf.printId();
        System.out.print(" Quick Find\n");
        qu.printId();
        System.out.print(" Quick Union\n");
        wu.printId();
        System.out.print(" Weighed Quick Union\n");
        wuc.printId();
        System.out.print(" Weighed Quick Union with path compression\n");
    }

}
