package ua.azbest.study.technology;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by AZbest on 30.09.2015.
 */
public class Lab4 {

    double powerNonNeg(double x, int n) {
        double z = 1;
        if (n > 0) {
            for (int i = 0; i < n; i++)
                z *= x;
        } else {
            System.out.println("Error");
        }
        return z;
    }

    @Test
    public void powerNonNegCalculatesCorrectValues() {
        assertEquals("2^3=8", 8, powerNonNeg(2, 3),0.0001);
        assertEquals("2^4=16", 16, powerNonNeg(2,4),0.0001);
    }

    @Test
    public void powerNonNegCalculatesAnyPowerOfOne() {
        assertEquals("1^3=1", 1, powerNonNeg(1, 3),0.0001);
        assertEquals("1^200=1", 1, powerNonNeg(1, 200),0.0001);
        assertEquals("1^1000=1", 1, powerNonNeg(1, 1000),0.0001);
    }

    @Test
    public void powerNonNegCalculatesPowerOfZero() {
        assertEquals("1000^0=1", 1, powerNonNeg(1000, 0),0.0001);
        assertEquals("23^0=1", 1, powerNonNeg(23, 0),0.0001);
        assertEquals("12^0=1", 1, powerNonNeg(12, 0),0.0001);
    }

    @Test
    public void powerNonNegCalculatesZeroOfZero() {
        assertEquals("0^0=1", 1, powerNonNeg(0, 0),0.0001);
    }

}
