package ru.spbstu.appmath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@RunWith(Parameterized.class)
public class ParametrizedTest2 {
    private static final Quicksort quickSort = new Quicksort();
    private static final Comparator<PersonBean> personComparator = new Comparator<PersonBean>() {
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

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        PersonBean arrayPersons[][] = new PersonBean[][] {
                {new PersonBean(), new PersonBean(), new PersonBean(), new PersonBean()},
                {new PersonBean(), new PersonBean(), new PersonBean(), new PersonBean()},
                {new PersonBean(), new PersonBean(), new PersonBean(), new PersonBean()},
        };
        arrayPersons[0][0].setName("Bob");      arrayPersons[0][0].setAge(19);
        arrayPersons[0][1].setName("Alice");    arrayPersons[0][1].setAge(2);
        arrayPersons[0][2].setName("David");    arrayPersons[0][2].setAge(7);
        arrayPersons[0][3].setName("John");     arrayPersons[0][3].setAge(15);
        arrayPersons[1][0].setName("Max");      arrayPersons[1][0].setAge(9);
        arrayPersons[1][1].setName("Ryan");     arrayPersons[1][1].setAge(9);
        arrayPersons[1][2].setName("Bill");     arrayPersons[1][2].setAge(9);
        arrayPersons[1][3].setName("Seth");     arrayPersons[1][3].setAge(9);
        arrayPersons[2][0].setName("Mulder");   arrayPersons[2][0].setAge(35);
        arrayPersons[2][1].setName("Scaly");    arrayPersons[2][1].setAge(31);
        arrayPersons[2][2].setName("Skinner");  arrayPersons[2][2].setAge(40);
        arrayPersons[2][3].setName("Smoker");   arrayPersons[2][3].setAge(78);
        Object[][] data = {
                {quickSort, arrayPersons[0]},
                {quickSort, arrayPersons[1]},
                {quickSort, arrayPersons[2]},
        };
        return Arrays.asList(data);
    }

    private Sort<PersonBean> sort;
    private PersonBean[] input;

    public ParametrizedTest2(Sort<PersonBean> sort, PersonBean[] input) {
        this.sort = sort;
        this.input = input;
    }

    @Test
    public void test() {
        PersonBean[] result = sort.sort(input, personComparator);
        Assert.assertTrue(testAscendingOrder(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));
    }

    private static boolean testAscendingOrder(PersonBean[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (personComparator.compare(array[i], array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    private static boolean hasEachElementOf(PersonBean[] input, PersonBean[] result) {
        for (PersonBean element : input) {
            for (int j = 0; j < result.length; j++) {
                if (personComparator.compare(result[j], element) == 0)
                    break;
                if (j == result.length - 1)
                    return false;
            }
        }
        return true;
    }
}