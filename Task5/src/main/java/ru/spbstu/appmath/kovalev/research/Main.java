package ru.spbstu.appmath.kovalev.research;

import ru.spbstu.appmath.kovalev.Matrix;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(15, 15);
        m1.createRandom(-200, 200);
        Matrix m2 = new Matrix(15, 15);
        m2.createRandom(-100, 100);

        final Research res = new Research(m1, m2);
        Report report = res.doResearch(15 * 15);
        File file = new File("report.txt");
        try {
            report.printReport(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
