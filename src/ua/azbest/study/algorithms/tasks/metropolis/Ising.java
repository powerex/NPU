package ua.azbest.study.algorithms.tasks.metropolis;

/**
 * Created by AZbest on 18.10.2015.
 */
import ua.azbest.study.aditionals.StdDraw;

import java.awt.Color;
import java.util.Scanner;

public class Ising {
    private int N;                  // N-by-N grid of sites
    private double J = 1.0;         // strength of spin-spin interaction (feromagentic when J is positive)
    private double kT;              // temperature (say between 1 and 4)
    private boolean[][] spin;       // up (true) or down (false)

    // N-by-N grid, kT = temperature, p = prob of up spin
    public Ising(int N, double kT, double p) {
        this.N    = N;
        this.kT   = kT;
        this.spin = new boolean[N][N];

        // initialize at random
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                spin[i][j] = (Math.random() < p);
    }


    // total average magnetization (between -1 and 1)
    public double magnetization() {
        int M = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (spin[i][j]) M++;
                else            M--;
            }
        }
        return 1.0 * M / (N * N);
    }

    // total energy of site (i, j), using periodic boundary conditions
    // assumes 0 <= i, j < N
    private double energy(int i, int j) {
        double E = 0.0;
        if (spin[i][j] == spin[(i+1)%N][j])   E++;
        else                                  E--;
        if (spin[i][j] == spin[i][(j+1)%N])   E++;
        else                                  E--;
        if (spin[i][j] == spin[(i-1+N)%N][j]) E++;
        else                                  E--;
        if (spin[i][j] == spin[i][(j-1+N)%N]) E++;
        else                                  E--;
        return -J * E;
    }

    // total energy, using periodic boundary conditions
    public double energy() {
        double E = 0.0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                E += 0.5 * energy(i, j);  // divide by two to mitigate double-counting
        return E;
    }

    // one Monte Carlo step
    public void step(int i, int j) {
        double deltaE = -2 * energy(i, j);
        // flip if energy decreases or get lucky
        if ((deltaE <= 0) || (Math.random() <= Math.exp(-deltaE / kT)))
            spin[i][j] = !spin[i][j];
    }

    // one Monte Carlo phase - N^2 steps
    public void phase() {
        for (int steps = 0; steps < N*N; steps++) {
            int i = (int) (Math.random() * N);
            int j = (int) (Math.random() * N);
            step(i, j);
        }
    }

    // plot it
    public void draw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (spin[i][j]) StdDraw.setPenColor(StdDraw.WHITE);
                else            StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.filledSquare(i + 0.5, j + 0.5, .5);
            }
        }

        // draw lines
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++) {
            StdDraw.line(i, 0, i, N);
            StdDraw.line(0, i, N, i);
        }
    }

    // string representation
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        String s = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (spin[i][j]) s += "< ";
                else            s += "> ";
            }
            s += NEWLINE;
        }
        return s;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double kT = sc.nextDouble();
        Ising ising = new Ising(N, kT, 0.5);

        // set scale and turn on animation mode
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.show(0);

        for (int t = 0; true; t++) {
            ising.phase();
            ising.draw();
            StdDraw.show(50);
        }
    }

}