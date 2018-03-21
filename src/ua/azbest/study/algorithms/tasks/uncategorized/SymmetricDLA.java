package ua.azbest.study.algorithms.tasks.uncategorized;

/**
 * Created by AZbest on 18.10.2015.
 */
import java.awt.Color;
import java.util.Scanner;

public class SymmetricDLA {

    // test client
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();     // N-by-N grid
        int x, y;                             // current (x, y) location
        double radius = 10;                   // radius
        double dist;                          // distance squared of (x, y) from seed
        boolean[][] dla = new boolean[N][N];  // is cell (x, y) occupied
        int particles = 0;

        // create rainbow of colors
        Color[] colors = new Color[256];
        for (int i = 0; i < 256; i++)
            colors[i] = Color.getHSBColor(1.0f * i / 255, .8f, .8f);


        // create graphics canvas and set seed
        Picture pic = new Picture(N, N);

        dla[N / 2][N / 2] = true;
        pic.set(N / 2, N / 2, Color.BLUE);

        while (radius < (N / 2 - 2)) {

            // choose launching site on circle of given radius from seed
            double angle = 2.0 * Math.PI * Math.random();
            x = (int) (N / 2.0 + radius * Math.cos(angle));
            y = (int) (N / 2.0 + radius * Math.sin(angle));

            // particle takes a 2d random walk
            while (true) {
                double r = Math.random();
                if (r < 0.25) x--;
                else if (r < 0.50) x++;
                else if (r < 0.75) y++;
                else y--;

                // check if entered kill zone
                dist = Math.sqrt((N / 2 - x) * (N / 2 - x) + (N / 2 - y) * (N / 2 - y));
                if (dist >= Math.min((N - 2) / 2.0, radius + 25)) break;

                // check if neighboring site is occupied
                if (dla[x - 1][y] || dla[x + 1][y] || dla[x][y - 1] || dla[x][y + 1] ||
                        dla[x - 1][y - 1] || dla[x + 1][y + 1] || dla[x - 1][y + 1] || dla[x + 1][y - 1]) {
                    dla[x][y] = true;
                    if (dist > radius) radius = dist;
                    break;
                }
            }

            if (dla[x][y]) {
                particles++;
                pic.set(x, y, colors[(particles / 76) % 256]);
                pic.show();
            }
        }
    }
}
