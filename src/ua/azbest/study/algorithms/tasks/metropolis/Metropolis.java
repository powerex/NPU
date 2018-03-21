package ua.azbest.study.algorithms.tasks.metropolis;

import ua.azbest.study.aditionals.StdDraw;

import java.util.Scanner;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Metropolis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double kT = sc.nextDouble();
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        State state = new State(N, 0.5);
        while (true) {
            state.phase(kT);
            state.draw();
            StdDraw.show(50);
        }
    }
}