package ua.azbest.study.algorithms.tasks.uncategorized;

/**
 * Created by AZbest on 18.10.2015.
 */
import java.awt.Color;
import java.util.Scanner;

public class DLA {

    // test client
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();     // N-by-N grid
        int launch = N - 10;                   // row to launch particles from
        boolean[][] dla = new boolean[N][N];   // is cell (x, y) occupied

        Picture pic = new Picture(N, N);

        int particles = 0;                     // only used to pick colors

        // create rainbow of colors
        Color[] colors = new Color[256];
        for (int i = 0; i < 256; i++)
            colors[i] = Color.getHSBColor(1.0f * i / 255, .8f, .8f);


        // set seed to be bottom row
        for (int x = 0; x < N; x++) dla[x][0] = true;


        // repeat until aggregate hits top
        boolean done = false;
        while (!done) {

            // random launching point
            int x = (int) (N * Math.random());
            int y = launch;

            // particle takes a 2d random walk
            while (x < N - 2 && x > 1 && y < N - 2 && y > 1) {
                double r = Math.random();
                if      (r < 0.25) x--;
                else if (r < 0.50) x++;
                else if (r < 0.65) y++;
                else               y--;

                // check if neighboring site is occupied
                if (dla[x-1][y]   || dla[x+1][y]   || dla[x][y-1]   || dla[x][y+1]   ||
                        dla[x-1][y-1] || dla[x+1][y+1] || dla[x-1][y+1] || dla[x+1][y-1] ) {
                    dla[x][y] = true;
                    particles++;
                    pic.set(x, N-y-1, colors[(particles / 256) % 256]);
                    pic.show();

                    // aggregate hits top, so set flag to stop outer while loop
                    if (y > launch) done = true;

                    // particle stuck, so break out of inner while loop
                    break;
                }
            }
        }
    }
}