package ru.spbstu.appmath.kovalev;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            Matrix matrix1 = new Matrix(file);
            file = new File(args[1]);
            Matrix matrix2 = new Matrix(file);
            file = new File(args[2]);
            int nThreads = 1;
            if (args.length == 4)
                nThreads = Integer.parseInt(args[3]);
            final long startTime = System.currentTimeMillis();
            Matrix result = new MatrixMultiplying(matrix1, matrix2, nThreads).multiply();
            final long executionTime = System.currentTimeMillis() - startTime;
            result.printInFile(file);
            System.out.println("Execution time: " + executionTime + " mls");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
