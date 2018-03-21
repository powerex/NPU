package ua.azbest.study.genetic.slar;

import com.sun.java.util.jar.pack.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AZbest on 19.10.2015.
 */
public class Vect {

    /**
     * Coordinates
     */
    public List<Number> koef;
    private boolean isDouble;
    /**
     *
     * @param _koef ArrayList of coordinates
     */
    public Vect(List<Number> _koef)
    {
        this(_koef, false);
    }

    public Vect(List<Number> _koef, boolean _isDouble) {
        koef = _koef;
        isDouble = _isDouble;
    }

    /**
     * Calc norm of vector, as sum of square all coordinates
     * @return number of norm of Vector
     */
    public Number calcD()
    {
        if (isDouble) {
            Double s = koef.get(0).doubleValue() * koef.get(0).doubleValue();
            for (int i = 1; i < koef.size(); i++) {
                s += koef.get(i).doubleValue() * koef.get(i).doubleValue();

            }
            return s;
        } else {
            Integer s = koef.get(0).intValue() * koef.get(0).intValue();
            for (int i = 1; i < koef.size(); i++) {
                s += koef.get(i).intValue() * koef.get(i).intValue();

            }
            return s;
        }
    }

    /**
     *  Coordinates separated gap
     * @return Vect in String format
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < koef.size()-1; i++)
            s += koef.get(i) + " ";
        s += String.valueOf(koef.get(koef.size() - 1));
        return s;
    }

    /*
    public int compareTo(Vect v, SLAR slar)
    {
        Number self = (slar.calcVect(this)).calcD();
        Number that = (slar.calcVect(v)).calcD();
        if (self < that) return -1;
        else if (self > that) return 1;
        else return 0;
    }
    //*/
}
