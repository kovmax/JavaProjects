public class Quicksort {
    private static void printArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i + 1 != array.length) {
                System.out.print(array[i] + " ");
            } else {
                System.out.println(array[i]);
            }
        }
    }

    private static void convert(String[] arr1, double[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = Double.parseDouble(arr1[i]);
        }
    }

    private static void swap(double[] array, int i, int j) {
        double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int partition(double[] array, int begin, int end) {
        double p = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j] <= p) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static void sort(double[] array, int begin, int end) {
        if (begin < end) {
            int q = partition(array, begin, end);
            sort(array, begin, q - 1);
            sort(array, q + 1, end);
        }
    }

    public static void main(String[] args) {
        double[] array;
        array = new double[args.length];
        convert(args, array);
        sort(array, 0, array.length - 1);
        printArray(array);
    }
}