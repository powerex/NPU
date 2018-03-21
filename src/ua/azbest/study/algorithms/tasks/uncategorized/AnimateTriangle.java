package ua.azbest.study.algorithms.tasks.uncategorized;

import ua.azbest.study.aditionals.StdDraw;

import java.util.Scanner;

/**
 * Created by AZbest on 20.10.2015.
 */
public class AnimateTriangle {

    public static void draw(int n, double x, double y, double yp, double x1, double x2)
    {
        if (n == 0) return;

        double yt = (y + yp)/2;
        double xt1 = (x1 + x)/2;
        double xt2 = (x + x2)/2;

        StdDraw.setPenRadius(n / 1000.0);

        StdDraw.line(x, y, x2, yp);
        StdDraw.line(x, y, x1, yp);
        StdDraw.line(x1, yp, x2, yp);
        StdDraw.show(10);

        draw(n - 1, x, y, yt, xt1, xt2);
        draw(n - 1, xt1, yt, yp, x1, x);
        draw(n - 1, xt2, yt, yp, x, x2);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        draw(N, 0.5, 0.9, 0.1, 0.1, 0.9);
    }

}
