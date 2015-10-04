package ru.spbstu.appmath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@RunWith(Parameterized.class)
public class ParametrizedTest {
    private static final Quicksort quickSort = new Quicksort();
    private static final Comparator<Double> doubleComparator = new Comparator<Double>() {
        public int compare(final Double o1, final Double o2) {
            return o1.compareTo(o2);
        }
    };

    private static final Object[][] data = {
            {quickSort, new Double[]{-1.5, -6.78, 5.95, 0.001}},
            {quickSort, new Double[]{5.0, 5.0, 5.0, -5.0}},
            {quickSort, new Double[]{92.9, 92.99, 93.31}},
     };

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(data);
    }

    private Sort<Double> sort;
    private Double[] input;

    public ParametrizedTest(Sort<Double> sort, Double[] input) {
        this.sort = sort;
        this.input = input;
    }

    @Test
    public void test() {
        Double[] result = sort.sort(input, doubleComparator);
        Assert.assertTrue(testAscendingOrder(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));
    }

    private static boolean testAscendingOrder(Double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    private static boolean hasEachElementOf(Double[] input, Double[] result) {
        for (double element : input) {
            for (int j = 0; j < result.length; j++) {
                if (result[j] == element)
                    break;
                if (j == result.length - 1)
                    return false;
            }
        }
        return true;
    }
}
