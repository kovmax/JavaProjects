package ru.spbstu.appmath;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*final Quicksort<PersonBean> q = new Quicksort<PersonBean>();
        final Comparator<PersonBean> personComparator = new Comparator<PersonBean>() {
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
        };*/

        final Quicksort<Double> q = new Quicksort<Double>();
        final Comparator<Double> DOUBLE_COMPARATOR_2 = new Comparator<Double>() {
            public int compare(final Double o1, final Double o2) {
                return o2.compareTo(o1);
            }
        };

        /*PersonBean arrayPersons[][] = new PersonBean[][] {
                {new PersonBean("Bob", 19), new PersonBean("Alice", 2),
                        new PersonBean("David", 7), new PersonBean("John", 15)},
                {new PersonBean("Max", 9), new PersonBean("Ryan", 9),
                        new PersonBean("Bill", 9), new PersonBean("Seth", 9)},
                {new PersonBean("Mulder", 35), new PersonBean("Scaly", 31),
                        new PersonBean("Skinner", 40), new PersonBean("Smoker", 78)}
        };


        for (int i = 0; i < 3; i++){
            arrayPersons[i] = q.sort(arrayPersons[i], personComparator);
            for (int j = 0; j < 4; j++){
                String n = arrayPersons[i][j].getName();
                System.out.println(n);
            }

        }*/

                /*System.out.println(Arrays.asList(array[0]));
        System.out.println(Arrays.asList(array[1]));
        System.out.println(Arrays.asList(array[2]));
        System.out.println(Arrays.asList(array[3]));
        System.out.println(Arrays.asList(array[4]));*/

        Random random = new Random();
        Double[] array= new Double[10];
        for (int j = 0; j < array.length; j++)
        {
            array[j] = 2000 * random.nextDouble() - 1000;
        }

        array = q.sort(array, DOUBLE_COMPARATOR_2);
        System.out.println(Arrays.asList(array));

    }
}

