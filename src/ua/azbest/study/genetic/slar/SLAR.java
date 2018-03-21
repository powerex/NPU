package ua.azbest.study.genetic.slar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by AZbest on 19.10.2015.
 */
public class SLAR {

    private List< List< Number > > matrix;
    private boolean isDouble;

    public SLAR() {
        this(false);
    }

    public SLAR(boolean _isDouble)
    {
        isDouble = _isDouble;
        System.out.println("N=");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        matrix = new ArrayList<List<Number>>();
        if (_isDouble){
            for (int i = 0; i < N; i++) {
                matrix.add(new ArrayList<Number>());
                for (int j = 0; j <= N; j++) {
                    Double d = sc.nextDouble();
                    matrix.get(i).add(d);
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                matrix.add(new ArrayList<Number>());
                for (int j = 0; j <= N; j++) {
                    Integer d = sc.nextInt();
                    matrix.get(i).add(d);
                }
            }
        }
    }

    public SLAR(String path) {
        this(path, false);
    }

    public SLAR(String path, boolean _isDouble)
    {
        isDouble = _isDouble;
        try{
            Scanner sc = new Scanner(new File(path));
            while(sc.hasNext()){
                int N = sc.nextInt();
                matrix = new ArrayList<List<Number>>();
                if (_isDouble) {
                    for (int i = 0; i < N; i++) {
                        matrix.add(new ArrayList<Number>());
                        for (int j = 0; j <= N; j++) {
                            Double d = sc.nextDouble();
                            matrix.get(i).add(d);
                        }
                    }
                } else {
                    for (int i = 0; i < N; i++) {
                        matrix.add(new ArrayList<Number>());
                        for (int j = 0; j <= N; j++) {
                            Integer d = sc.nextInt();
                            matrix.get(i).add(d);
                        }
                    }
                }
            }
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isDouble() {
        return isDouble;
    }

    public int rang()
    {
        return matrix.size();
    }

    public Vect calcVect(Vect v)
    {
        List<Number> list = new ArrayList<Number>();
        if (isDouble) {
            for (int i = 0; i < rang(); i++)
            {
                Double res = 0.0;
                for (int j = 0; j < rang(); j++)
                {
                    res += matrix.get(i).get(j).doubleValue() * v.koef.get(j).doubleValue();
                }
                res += matrix.get(i).get(rang()).doubleValue();
                list.add(res);
            }
        } else {
            for (int i = 0; i < rang(); i++)
            {
                Integer res = 0;
                for (int j = 0; j < rang(); j++)
                {
                    res += matrix.get(i).get(j).intValue() * v.koef.get(j).intValue();
                }
                res += matrix.get(i).get(rang()).intValue();
                list.add(res);
            }
        }

        Vect resV = new Vect(list, isDouble);
        return resV;
    }
/*
    public static void main(String[] args)
    {
        SLAR slar = new SLAR("input.txt");
        System.out.println(slar.matrix.get(0).toString());
        System.out.println(slar.matrix.get(0));
    }
//*/
}
