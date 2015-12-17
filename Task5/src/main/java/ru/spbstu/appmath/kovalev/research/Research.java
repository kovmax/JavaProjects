package ru.spbstu.appmath.kovalev.research;

import ru.spbstu.appmath.kovalev.Matrix;
import ru.spbstu.appmath.kovalev.MatrixMultiply;

public class Research {
    private final Matrix m1;
    private final Matrix m2;

    public Research (final Matrix m1, final Matrix m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    public Report doResearch(final int maxNumThreads) {
        Report report = new Report();
        for (int i = 1; i <= maxNumThreads; i++) {
            try {
                final long startTime = System.currentTimeMillis();
                Matrix result = new MatrixMultiply(m1, m2, i).multiply();
                final long executionTime = System.currentTimeMillis() - startTime;
                String msg = "Count of threads - " + i + " Execution time - " + executionTime + " mls";
                report.addMessage(msg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return report;
    }
}
