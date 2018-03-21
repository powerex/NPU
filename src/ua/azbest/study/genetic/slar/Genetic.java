package ua.azbest.study.genetic.slar;

import java.util.*;

/**
 * Created by AZbest on 19.10.2015.
 */
public class Genetic {

    public ArrayList<Vect> population;
    int N;
    int populationSize;
    private SLAR slar;
    private double EPS = 1e-4;
    private boolean isDouble;

    public Genetic(int count) {
        this(count, false);
    }

    public Genetic(int count, boolean _isDouble)
    {
        isDouble = _isDouble;
        slar = new SLAR("inputZ.txt", isDouble);
        isDouble = slar.isDouble();
        populationSize = count;
        population = new ArrayList<Vect>();
        N = slar.rang();
        Random rnd = new Random();

        if (isDouble) {
            for (int i = 0; i < count; i++) {
                List<Number> k = new ArrayList<Number>();
                for (int j = 0; j < N; j++) {
                    k.add( rnd.nextDouble() % 10 );
                }
                Vect x = new Vect(k, isDouble);
                population.add(x);
            }
        } else {
            for (int i = 0; i < count; i++) {
                List<Number> k = new ArrayList<Number>();
                for (int j = 0; j < N; j++) {
                    k.add( rnd.nextInt() % 10 );
                }
                Vect x = new Vect(k, isDouble);
                population.add(x);
            }
        }
    }

    public ArrayList<Vect> nextGeneration()
    {
        Random rnd = new Random();
        List<Vect> children = new ArrayList<Vect>();
        for (Vect v: population) children.add(v);
        for (int i = 0; i < population.size()-1; i++)
        {
            if (isDouble) {
                for (int j = i+1; j < population.size(); j++) {
                    List<Number> koef = new ArrayList<Number>();
                    for (int k = 0; k < N/2; k++) {
                        Double t = population.get(i).koef.get(k).doubleValue();
                        if ( rnd.nextGaussian() > 0.50 )
                            t += rnd.nextGaussian();
                        koef.add( t );
                    }
                    for (int k = N/2; k < N; k++) {
                        Double t = population.get(j).koef.get(k).doubleValue();
                        if ( rnd.nextGaussian() > 0.50 )
                            t += rnd.nextGaussian();
                        koef.add( t );
                    }
                    Vect v = new Vect(koef, isDouble);
                    children.add(v);
                }
            } else {
                for (int j = i+1; j < population.size(); j++) {
                    List<Number> koef = new ArrayList<Number>();
                    for (int k = 0; k < N/2; k++) {
                        Integer t = population.get(i).koef.get(k).intValue();
                        if ( rnd.nextGaussian() > 0.50 )
                            t += rnd.nextInt()%2;
                        koef.add( t );
                    }
                    for (int k = N/2; k < N; k++) {
                        Integer t = population.get(j).koef.get(k).intValue();
                        if ( rnd.nextGaussian() > 0.50 )
                            t += rnd.nextInt()%2;
                        koef.add( t );
                    }
                    Vect v = new Vect(koef, isDouble);
                    children.add(v);
                }
            }
        }
        return (ArrayList<Vect>) children;
    }

    public int go(int age)
    {
        int i;
        for ( i = 0; i < age; i++)
        {
            TreeMap< Number, Vect > tree = new TreeMap< Number, Vect >();
            List<Vect> children = nextGeneration();
            for (int j = 0; j < children.size(); j++)
            {
                tree.put(slar.calcVect(children.get(j)).calcD(), children.get(j));
            }

            population.clear();
            for ( int j = 0; j < populationSize; j++ )
            {
                population.add(tree.remove(tree.firstKey()));
            }
            /*
            System.out.print(population.toString());
            System.out.println("\t\t" +  slar.calcVect(population.get(0)).calcD());
            //*/
            if (slar.calcVect(population.get(0)).calcD().doubleValue() <= EPS) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args)
    {

        Genetic genetic = new Genetic(10, true);
        int steps = genetic.go(10000);
        System.out.println("Answer \t [" + genetic.population.get(0) + "] ~ after " + steps + " steps. ~: " +
                genetic.slar.calcVect(genetic.population.get(0)).calcD());
    }

}