package ru.spbstu.appmath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

@RunWith(Parameterized.class)
public class ParametrizedTest extends TestTemplate {
    private static final Comparator<Double> DOUBLE_COMPARATOR_1 = new Comparator<Double>() {
        public int compare(final Double o1, final Double o2) {
            return o1.compareTo(o2);
        }
    };
    private static final Comparator<Double> DOUBLE_COMPARATOR_2 = new Comparator<Double>() {
        public int compare(final Double o1, final Double o2) {
            return o2.compareTo(o1);
        }
    };

    private Sort<Double> sort;
    private Double[] input;

    public ParametrizedTest(Sort<Double> sort, Double[] input) {
        this.sort = sort;
        this.input = input;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Random random = new Random();
        Double[][] array= new Double[5][10];
        Double tmp1 = random.nextDouble() * 10;
        Double tmp2 = random.nextDouble() * -10;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                if (i < 3) {
                   array[i][j] = 2000 * random.nextDouble() - 1000;
                }
                if (i == 3) {
                    array[i][j] = tmp1;
                }
                if (i == 4) {
                    if (j % 2 == 0) {
                        array[i][j] = tmp1;
                    } else {
                        array[i][j] = tmp2;
                    }
                }
            }
        }

        Object[][] data = {
                {QUICKSORT, array[0]},
                {QUICKSORT, array[1]},
                {QUICKSORT, array[2]},
                {QUICKSORT, array[3]},
                {QUICKSORT, array[4]},
                {QUICKSORT, new Double[]{-7.95, -6.87, -5.23, -5.21, 0.012, 1.257, 8.56, 10.25, 15.15, 17.59}},
                {QUICKSORT, new Double[]{8.92, 6.21, 2.14, 1.23, 0.01, -2.35, -4.25, -8.88, -9.32, -15.75}}
        };
        return Arrays.asList(data);
    }

    @Test
    public void test1() {
        test(sort, input, DOUBLE_COMPARATOR_1);
    }

    @Test
    public void test2() {
        test(sort, input, DOUBLE_COMPARATOR_2);
    }
}
