package ua.azbest.study.algorithms.tasks.percolation;

import ua.azbest.study.aditionals.StdOut;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Estimate {

    // do M trials and return fraction that percolate
    public static double eval(int N, double p, int M) {
        int count = 0;
        for (int k = 0; k < M; k++) {
            boolean[][] open = Percolation.random(N, p);
            if (Percolation.percolates(open))
                count++;
        }
        return (double) count / M;
    }


    public static void main(String[] args) {
        int    N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        int    M = Integer.parseInt(args[2]);
        double q = eval(N, p, M);
        StdOut.println(q);
    }
}
