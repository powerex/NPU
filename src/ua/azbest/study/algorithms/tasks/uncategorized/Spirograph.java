package ua.azbest.study.algorithms.tasks.uncategorized;

import ua.azbest.study.aditionals.StdDraw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Spirograph {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        double R = sc.nextDouble();
        double r = sc.nextDouble();
        double a = sc.nextDouble();

        StdDraw.setXscale(-300, +300);
        StdDraw.setYscale(-300, +300);
        StdDraw.clear(StdDraw.BLACK);

        for (double t = 0.0; t < 100; t += 0.01) {
            //*
            double x = (R+r) * Math.cos(t) - (r+a) * Math.cos(((R+r)/r)*t);
            double y = (R+r) * Math.sin(t) - (r+a) * Math.sin(((R+r)/r)*t);
            double degrees = -Math.toDegrees((R+r)/r)*t;
            //*/
            StdDraw.picture(x, y, "img/earth.gif", degrees);
            // StdDraw.rotate(+Math.toDegrees((R+r)/r)*t);
            StdDraw.show(10);
        }

    }

}