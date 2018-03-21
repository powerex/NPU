package ua.azbest.study.algorithms.sorting;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AZbest on 20.10.2015.
 */
public class Sort {

    public static List<Integer> insertSort(final List<Integer> numbers)
    {
        final List<Integer> sortedList = new LinkedList<Integer>();

        originalList: for (Integer number: numbers)
        {
            for (int i = 0; i < sortedList.size(); i++)
            {
                if (number < sortedList.get(i))
                {
                    sortedList.add(i, number);
                    continue originalList;
                }
            }
            sortedList.add(sortedList.size(), number);
        }
        return sortedList;
    }

}
