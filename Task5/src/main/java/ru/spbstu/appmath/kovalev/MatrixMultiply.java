package ru.spbstu.appmath.kovalev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MatrixMultiply {
    private final Matrix matrix1;
    private final Matrix matrix2;
    private final int nThreads;

    public MatrixMultiply(final Matrix m1, final Matrix m2, final int nTh) throws IOException {
        if (m1.getColumns() != m2.getRows())
            throw new IOException("Inappropriate matrix's dimensions");
        this.matrix1 = m1;
        this.matrix2 = m2;
        this.nThreads = nTh;
    }

    public Matrix multiply() {
        Matrix result = new Matrix(matrix1.getRows(), matrix2.getColumns());
        final ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        final List<FutureResult> taskFutures = new ArrayList<>();
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix2.getColumns(); j++) {
                final Future<Double> future = executor.submit(new Task(i, j));
                taskFutures.add(new FutureResult(i, j, future));
            }
        }

        for (final FutureResult future : taskFutures) {
            try {
                result.set(future.getRow(), future.getColumn(), future.getValue());
            } catch (Exception ignore) {
            }
        }
        executor.shutdown();
        return result;
    }

    private class Task implements Callable<Double> {
        private final int iRow;
        private final int iColumn;

        public Task(final int i, final int j) {
            this.iRow = i;
            this.iColumn = j;
        }

        public Double call() {
            Double result = 0.0;
            for (int i = 0; i < matrix1.getColumns(); i++)
                result += matrix1.get(iRow, i) * matrix2.get(i,iColumn);
            return result;
        }
    }

    private class FutureResult {
        private final int iRow;
        private final int iColumn;
        private Future<Double> value;

        public FutureResult(final int i, final int j, final Future<Double> val) {
            this.iRow = i;
            this.iColumn = j;
            this.value = val;
        }

        public int getRow() {
            return iRow;
        }

        public int getColumn() {
            return iColumn;
        }

        public Double getValue() throws Exception{
            return value.get();
        }
    }
}
