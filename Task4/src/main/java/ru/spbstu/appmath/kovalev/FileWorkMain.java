package ru.spbstu.appmath.kovalev;


import java.nio.file.Paths;

public class FileWorkMain {
    public static void main(String[] args) {
        final Range range = new Range(args[2]);
        final int min = range.getMin();
        final int max = range.getMax();
        final int step = range.getStep();
        final FileWork f = new FileWork();
        final String PATH_1 = Paths.get("src", "files", "main", args[0]).toString();
        final String PATH_2 = Paths.get("src", "files", "main", args[1]).toString();
        f.doCalculations(PATH_1, PATH_2, min, max, step);
    }
}
