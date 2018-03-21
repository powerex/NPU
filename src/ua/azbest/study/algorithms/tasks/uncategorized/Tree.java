package ua.azbest.study.algorithms.tasks.uncategorized;

import ua.azbest.study.aditionals.StdDraw;

import java.util.Scanner;

/**
 * Created by AZbest on 18.10.2015.
 */
public class Tree {

    public static void tree(int n, double x, double y, double a, double branchRadius) {
        double bendAngle   = Math.toRadians(5);
        double branchAngle = Math.toRadians(35);
        double branchRatio = .25;

        double cx = x + Math.cos(a) * branchRadius;
        double cy = y + Math.sin(a) * branchRadius;
        StdDraw.setPenRadius(.001 * Math.pow(n, 1.2));
        StdDraw.line(x, y, cx, cy);
        if (n == 0) return;

        tree(n-1, cx, cy, a + bendAngle - branchAngle, branchRadius * branchRatio);
        tree(n-1, cx, cy, a + bendAngle + branchAngle, branchRadius * branchRatio);
        tree(n-1, cx, cy, a + bendAngle,               branchRadius * (1 - branchRatio));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StdDraw.show(0);
        tree(N, .5, 0, Math.PI/2, .3);
        StdDraw.show(0);
    }
}
