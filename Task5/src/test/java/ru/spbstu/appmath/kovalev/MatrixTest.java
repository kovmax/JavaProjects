package ru.spbstu.appmath.kovalev;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.kovalev.exceptions.MatrixException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class MatrixTest {
    private final Matrix m1;
    private final Matrix m2;
    private final Matrix res;

    public MatrixTest(final Matrix m1, final Matrix m2, final Matrix res) {
        this.m1 = m1;
        this.m2 = m2;
        this.res = res;
    }

    private static ArrayList<Object[]> initializeTestData() {
        ArrayList<Object[]> data = new ArrayList<>();
        final Random random = new Random();
        int nRows = random.nextInt(8) + 2;
        int nColumns = random.nextInt(8) + 2;

        for (int i = 0; i < 15; i++) {
            final Matrix m1 = new Matrix(nRows, nRows);
            try {
                m1.createIdentity();
            } catch (MatrixException e) {
                System.out.println(e.getMessage());
            }

            final Matrix m2 = new Matrix(nRows, nColumns);
            m2.createRandom(-200, 200);

            data.add(new Object[] {m1, m2, m2});
        }

        for (int i = 1; i < 4; i++) {
            final String path1 = Paths.get("test", "m1", String.valueOf(i) + ".txt").toString();
            final String path2 = Paths.get("test", "m2", String.valueOf(i) + ".txt").toString();
            final String path3 = Paths.get("test", "m3", String.valueOf(i) + ".txt").toString();
            try {
                final Matrix m1 = new Matrix(new File(path1));
                final Matrix m2 = new Matrix(new File(path2));
                final Matrix m3 = new Matrix(new File(path3));
                data.add(new Object[] {m1, m2, m3});
            } catch (MatrixException e) {
                System.out.println(e.getMessage());
            }
        }

        return data;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws MatrixException{
        return initializeTestData();
    }

    @Test
    public void test() {
        try {
            Matrix result = new MatrixMultiplying(m1, m2, 5).multiply();
            Assert.assertTrue(compare(res, result));
        } catch (MatrixException e) {
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
