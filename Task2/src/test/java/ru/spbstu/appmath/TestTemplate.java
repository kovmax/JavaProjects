package ru.spbstu.appmath;

import org.junit.Assert;
import java.util.Comparator;


public abstract  class TestTemplate {

    protected static final Quicksort QUICKSORT = new Quicksort();

    protected <T> void test(Sort<T> sort, T[] input, Comparator<T> comp) {
        T[] result = sort.sort(input, comp);
        Assert.assertTrue(testOrder(result, comp));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result, comp));
    }

    protected static <T> boolean testOrder(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    protected static <T> boolean hasEachElementOf(T[] input, T[] result, Comparator<T> comparator) {
        int c1, c2;
        for (T element : input) {
            c1 = 0;
            c2 = 0;
            for (int j = 0; j < result.length; j++) {
                if (comparator.compare(result[j], element) == 0)
                    c1++;
                if (comparator.compare(input[j], element) == 0)
                    c2++;
            }
            if (c1 != c2)
                return false;
        }
        return true;
    }
}
