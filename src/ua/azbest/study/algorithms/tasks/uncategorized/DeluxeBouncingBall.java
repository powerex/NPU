package ua.azbest.study.algorithms.tasks.uncategorized;

import ua.azbest.study.aditionals.StdAudio;
import ua.azbest.study.aditionals.StdDraw;

/**
 * Created by AZbest on 18.10.2015.
 */
public class DeluxeBouncingBall {
    public static void main(String[] args) {
        double rx = .480, ry = .860;     // position
        double vx = .015, vy = .023;     // velocity
        double radius = .03;             // a hack since "earth.gif" is in pixels

        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);


        // main animation loop
        while (true) {
            if (Math.abs(rx + vx) + radius > 1.0) {
                vx = -vx;
                StdAudio.play("audio/laser.wav");
            }
            if (Math.abs(ry + vy) + radius > 1.0) {
                vy = -vy;
                StdAudio.play("audio/pop.wav");
            }
            rx = rx + vx;
            ry = ry + vy;
            StdDraw.clear();
            StdDraw.picture(rx, ry, "img/earth.gif");
            StdDraw.show(30);
        }
    }
}
