package ru.spbstu.appmath;

import java.util.Collection;
import java.util.Comparator;

public abstract  class TestTemplate {

    public static <T> boolean testOrder(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static <T> boolean hasEachElementOf(T[] input, T[] result, Comparator<T> comparator) {
        for (T element : input) {
            for (int j = 0; j < result.length; j++) {
                if (comparator.compare(result[j], element) == 0)
                    break;
                if (j == result.length - 1)
                    return false;
            }
        }
        return true;
    }
}
