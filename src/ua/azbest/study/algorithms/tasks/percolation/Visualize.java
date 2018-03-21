package ua.azbest.study.algorithms.tasks.percolation;

import ua.azbest.study.aditionals.StdDraw;

import java.util.Scanner;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Visualize {
    public static void main(String[] args) {
        /*int N    = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        int M    = Integer.parseInt(args[2]);
        */

        Scanner sc = new Scanner(System.in);
        int N    = sc.nextInt();
        double p = sc.nextDouble();
        int M    = sc.nextInt();

        // repeatedly created N-by-N matrices and display them using standard draw
        for (int i = 0; i < M; i++) {
            boolean[][] open = Percolation.random(N, p);
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            Percolation.show(open, false);
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            boolean[][] full = Percolation.flow(open);
            Percolation.show(full, true);
            StdDraw.show(1000);
        }
    }
}