package ru.spbstu.appmath.kovalev;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class DiffDimTest {
    private final Matrix m1;
    private final Matrix m2;
    private final Matrix res;

    public DiffDimTest(final Matrix m1, final Matrix m2, final Matrix res) {
        this.m1 = m1;
        this.m2 = m2;
        this.res = res;
    }

    private static ArrayList<Object[]> initializeTestData() {
        ArrayList<Object[]> data = new ArrayList<>();
        Random random = new Random();
        final int nRows = random.nextInt(8) + 2;
        final int nColumns = random.nextInt(8) + 2;

        for (int i = 0; i < 15; i++) {
            final Matrix m1 = new Matrix(nRows, nRows);
            try {
                m1.createIdentity();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            final Matrix m2 = new Matrix(nRows, nColumns);
            m2.createRandom(-200, 200);

            data.add(new Object[] {m1, m2, m2});
        }

        return data;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws Exception{
        return initializeTestData();
    }

    @Test
    public void test() {
        try {
            Matrix result = new MatrixMultiply(m1, m2, 5).multiply();
            Assert.assertTrue(compare(res, result));
        } catch (Exception e) {
        }
    }

    private boolean compare(final Matrix m1, final Matrix m2) {
        if (m1.getRows() != m2.getRows() && m1.getColumns() != m2.getColumns())
            return false;
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getColumns(); j++) {
                if (!m1.get(i, j).equals(m2.get(i, j)))
                    return false;
            }
        }
        return true;
    }

}
