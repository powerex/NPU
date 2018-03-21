package ua.azbest.study.oop.snake;

import java.io.Console;

/**
 * Created by AZbest on 13.10.2015.
 */
public class Program {

    public static void main(String[] args)
    {
        int x1 = 1;
        int y1 = 3;

        char sym1 = '*';

        Draw( x1, y1, sym1 );

        int x2 = 4;
        int y2 = 5;
        char sym2 = '#';

        Draw(x2, y2, sym2);
    }

    private static void Draw(int x, int y, char sym) {
        System.out.println(String.format("%c[%d;%d]",sym,x,y));
    }

}
