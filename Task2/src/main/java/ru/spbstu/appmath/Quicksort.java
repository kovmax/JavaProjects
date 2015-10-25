package ru.spbstu.appmath;

import java.util.Arrays;
import java.util.Comparator;

public class Quicksort<T> implements Sort<T> {

    public T[] sort(T[] array, Comparator<T> comp){
        T[] result = Arrays.copyOf(array, array.length);
        quickSort(result, 0, array.length - 1, comp);
        return result;
    }

    private static <T> void quickSort(T[] array, int begin, int end, Comparator<T> comp) {
        if (begin < end) {
            int q = partition(array, begin, end, comp);
            quickSort(array, begin, q - 1, comp);
            quickSort(array, q + 1, end, comp);
        }
    }

    private static <T> int partition(T[] array, int begin, int end, Comparator<T> comp) {
        T p = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (comp.compare(array[j], p) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
