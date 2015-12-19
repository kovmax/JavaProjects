package ru.spbstu.appmath.kovalev.research;

import ru.spbstu.appmath.kovalev.Matrix;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 5);
        m1.createRandom(-200, 200);
        Matrix m2 = new Matrix(5, 4);
        m2.createRandom(-100, 100);

        Research res = new Research(m1, m2);
        Report report = res.doResearch(2 * 4, 10);
        File file = new File("Report 1 - Small matrix.txt");
        try {
            report.printReport(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        m1 = new Matrix(12, 5);
        m1.createRandom(-200, 200);
        m2 = new Matrix(5, 17);
        m2.createRandom(-100, 100);

        res = new Research(m1, m2);
        report = res.doResearch(12 * 17, 10);
        file = new File("Report 2 - Middle matrix.txt");
        try {
            report.printReport(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        m1 = new Matrix(30, 5);
        m1.createRandom(-200, 200);
        m2 = new Matrix(5, 30);
        m2.createRandom(-100, 100);

        res = new Research(m1, m2);
        report = res.doResearch(30 * 30, 10);
        file = new File("Report 3 - Big matrix.txt");
        try {
            report.printReport(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
