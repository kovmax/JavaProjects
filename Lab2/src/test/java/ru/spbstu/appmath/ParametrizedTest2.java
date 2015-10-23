package ru.spbstu.appmath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@RunWith(Parameterized.class)
public class ParametrizedTest2 extends TestTemplate{
    private static final Quicksort QUICKSORT = new Quicksort();
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
    private Sort<PersonBean> sort;
    private PersonBean[] input;

    public ParametrizedTest2(Sort<PersonBean> sort, PersonBean[] input) {
        this.sort = sort;
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = {
                {QUICKSORT, new PersonBean[]{new PersonBean("Bob", 19), new PersonBean("Alice", 2),
                                             new PersonBean("David", 7), new PersonBean("John", 15)}},
                {QUICKSORT, new PersonBean[]{new PersonBean("Max", 9), new PersonBean("Ryan", 9),
                                             new PersonBean("Bill", 9), new PersonBean("Seth", 9)}},
                {QUICKSORT, new PersonBean[]{new PersonBean("Mulder", 35), new PersonBean("Scaly", 31),
                                             new PersonBean("Skinner", 40), new PersonBean("Smoker", 78)}},
        };
        return Arrays.asList(data);
    }


    @Test
    public void test1() {
        PersonBean[] result = sort.sort(input, PERSON_BEAN_COMPARATOR_1);
        Assert.assertTrue(testOrder(result, PERSON_BEAN_COMPARATOR_1));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result, PERSON_BEAN_COMPARATOR_1));
    }

    @Test
    public void test2() {
        PersonBean[] result = sort.sort(input, PERSON_BEAN_COMPARATOR_2);
        Assert.assertTrue(testOrder(result, PERSON_BEAN_COMPARATOR_2));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result, PERSON_BEAN_COMPARATOR_2));
    }

}