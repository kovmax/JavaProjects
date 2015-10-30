package ru.spbstu.appmath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class SortTest<T> {

    /** Поля и конструктор */
    private Sort<T> sort;
    private T[] elements;
    private Comparator<T> comp;
    public SortTest (Sort<T> sort, T[] input, Comparator<T> comp) {
        this.sort = sort;
        this.elements = input;
        this.comp = comp;
    }


    /** Сортировщик */
    public static final Quicksort QUICKSORT = new Quicksort();


    /** Компараторы */
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
    private static final Comparator<PersonBean> PERSON_BEAN_COMPARATOR_1 = new Comparator<PersonBean>() {
        public int compare(final PersonBean o1, final PersonBean o2) {
            Integer ob1 = o1.getAge();
            Integer ob2 = o2.getAge();
            if (ob1.compareTo(ob2) != 0) {
                return ob1.compareTo(ob2);
            } else {
                String n1 = o1.getName();
                String n2 = o2.getName();
                return n1.compareTo(n2);
            }
        }
    };
    private static final Comparator<PersonBean> PERSON_BEAN_COMPARATOR_2 = new Comparator<PersonBean>() {
        public int compare(final PersonBean o1, final PersonBean o2) {
            String ob1 = o1.getName();
            String ob2 = o2.getName();
            if (ob1.compareTo(ob2) != 0) {
                return ob1.compareTo(ob2);
            } else {
                Integer n1 = o1.getAge();
                Integer n2 = o2.getAge();
                return n1.compareTo(n2);
            }
        }
    };

    /** Данные для теста */
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Random random = new Random();
        Double[][] array= new Double[7][10];
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
        array[5] = new Double[]{-7.95, -6.87, -5.23, -5.21, 0.012, 1.257, 8.56, 10.25, 15.15, 17.59};
        array[6] = new Double[]{8.92, 6.21, 2.14, 1.23, 0.01, -2.35, -4.25, -8.88, -9.32, -15.75};

        PersonBean[][] people = new PersonBean[][] {
            new PersonBean[]{new PersonBean("Bob", 19), new PersonBean("Alice", 2),
                    new PersonBean("David", 7), new PersonBean("John", 15)},
            new PersonBean[]{new PersonBean("Max", 9), new PersonBean("Ryan", 9),
                        new PersonBean("Bill", 9), new PersonBean("Seth", 9)},
            new PersonBean[]{new PersonBean("Mulder", 35), new PersonBean("Scaly", 31),
                new PersonBean("Skinner", 40), new PersonBean("Smoker", 78)},
            new PersonBean[]{new PersonBean("Killer", 35), new PersonBean("Killer", 35),
                new PersonBean("Killer", 35), new PersonBean("Killer", 35)},
            new PersonBean[]{new PersonBean("Killer", 35), new PersonBean("Muller", 55),
                new PersonBean("Killer", 35), new PersonBean("Muller", 55)},
            new PersonBean[]{new PersonBean("Ann", 2), new PersonBean("Sara", 11),
                new PersonBean("Tanya", 11), new PersonBean("Sam", 12)},
            new PersonBean[]{new PersonBean("Bruce", 45), new PersonBean("Sanders", 40),
                new PersonBean("Anya", 40), new PersonBean("Samuel", 12)},
            new PersonBean[]{new PersonBean("Bob", 19), new PersonBean("Alice", 2),
                new PersonBean("Anya", 40), new PersonBean("Samuel", 12)}
        };

        Object[][] data = {
                {QUICKSORT, array[0], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[1], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[2], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[3], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[4], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[5], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[6], DOUBLE_COMPARATOR_1},
                {QUICKSORT, array[0], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[1], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[2], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[3], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[4], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[5], DOUBLE_COMPARATOR_2},
                {QUICKSORT, array[6], DOUBLE_COMPARATOR_2},
                {QUICKSORT, people[0],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[1],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[2],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[3],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[4],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[5],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[6],  PERSON_BEAN_COMPARATOR_1},
                {QUICKSORT, people[0],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[1],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[2],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[3],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[4],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[5],  PERSON_BEAN_COMPARATOR_2},
                {QUICKSORT, people[6],  PERSON_BEAN_COMPARATOR_2}
        };
        return Arrays.asList(data);
    }


    @Test
    public void test() {
        T[] result = sort.sort(elements, comp);
        Assert.assertTrue(testOrder(result, comp));
        Assert.assertEquals("Result array length should be equal to original", elements.length, result.length);
        Assert.assertTrue(hasEachElementOf(elements, result, comp));
    }


    public static <T> boolean testOrder(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static <T> boolean hasEachElementOf(T[] input, T[] result, Comparator<T> comparator) {
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
