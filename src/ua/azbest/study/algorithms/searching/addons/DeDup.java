package ua.azbest.study.algorithms.searching.addons;

import ua.azbest.study.aditionals.StdIn;
import ua.azbest.study.aditionals.StdOut;

/**
 * Created by AZbest on 18.10.2015.
 */
public class DeDup {

    // Do not instantiate.
    private DeDup() { }

    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!set.contains(key)) {
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}
