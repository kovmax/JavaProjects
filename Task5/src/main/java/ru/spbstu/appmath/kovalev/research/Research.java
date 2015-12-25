package ru.spbstu.appmath.kovalev.research;

import ru.spbstu.appmath.kovalev.Matrix;
import ru.spbstu.appmath.kovalev.MatrixMultiplying;

import java.util.ArrayList;


public class Research {
    private final Matrix m1;
    private final Matrix m2;

    public Research (final Matrix m1, final Matrix m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    public Report doResearch(final int maxNumThreads, final int cIterations) {
        Report report = new Report();
        long[][] values = new long[maxNumThreads][cIterations];
        for (int j = 1; j <= cIterations; j++){
            for (int i = 1; i <= maxNumThreads; i++) {
                try {
                    final long startTime = System.currentTimeMillis();
                    Matrix result = new MatrixMultiplying(m1, m2, i).multiply();
                    final long executionTime = System.currentTimeMillis() - startTime;
                    values[i - 1][j - 1] = executionTime;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        double[] res = new double[maxNumThreads];
        for (int i = 0; i < maxNumThreads; i++) {
            for (int j = 0; j < cIterations; j++) {
                res[i] += values[i][j];
            }
            res[i] /= cIterations;
        }

        double min = res[0];
        for (int i = 1; i < maxNumThreads; i++) {
            if (res[i] < min)
                min = res[i];
        }

        ArrayList<Integer> threads = new ArrayList<>();
        for (int i = 0; i < maxNumThreads; i++) {
            if (res[i] == min)
                threads.add(i + 1);
        }

        report.addMessage("Dimension of result matrix: " + m1.getRows() + " * " +  m2.getColumns());
        String msg = "";
        for (int i = 0; i < threads.size(); i++) {
            msg += threads.get(i);
            if (i + 1 != threads.size())
                msg += ", ";
        }
        report.addMessage("Min time: " + min + " mls for count of threads: " + msg);
        for (int i = 0; i < maxNumThreads; i++) {
            msg = "Count of threads - " + (i + 1) + " Execution time - " + res[i] + " mls";
            report.addMessage(msg);
        }

        return report;
    }
}
