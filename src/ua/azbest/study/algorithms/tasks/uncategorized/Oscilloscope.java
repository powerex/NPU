package ua.azbest.study.algorithms.tasks.uncategorized;

import ua.azbest.study.aditionals.StdDraw;

import java.util.Scanner;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Oscilloscope {

    public static void main(String[] args) {
        /*
        double A    = Double.parseDouble(args[0]);    // amplitudes
        double B    = Double.parseDouble(args[1]);
        double wX   = Double.parseDouble(args[2]);    // angular frequencies
        double wY   = Double.parseDouble(args[3]);
        double phiX = Double.parseDouble(args[4]);    // phase factors
        double phiY = Double.parseDouble(args[5]);
        //*/

        Scanner sc = new Scanner(System.in);
        double A    = sc.nextDouble();    // amplitudes
        double B    = sc.nextDouble();
        double wX   = sc.nextDouble();
        double wY   = sc.nextDouble();
        double phiX = sc.nextDouble();
        double phiY = sc.nextDouble();

        StdDraw.setXscale(-1, +1);
        StdDraw.setYscale(-1, +1);

        // convert from degrees to radians
        phiX= Math.toRadians(phiX);
        phiY = Math.toRadians(phiY);


        for (double t = 0.0; t < 100; t += 0.01) {
            double x = A * Math.sin(wX * t + phiX);
            double y = B * Math.sin(wY * t + phiY);
            StdDraw.point(x, y);
            StdDraw.show(1);
        }
    }

}
