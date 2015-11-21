package ru.spbstu.appmath.kovalev;

import java.io.*;
import java.util.*;

public class FileWork {
    public void doCalculations(final String in, final String out, final String d) {
        Range range = new Range(d);
        final int min = range.getMin();
        final int max = range.getMax();
        final int step = range.getStep();
        final int amount = range.getAmount();
        final File fileIn = new File(in);
        final File fileOut = new File(out);

        createIfNeeded(fileOut);
        final Vector<String> tasks = read(fileIn);
        final Object[][] results = new Object[amount + 1][tasks.size()];

        for (int j = 0; j < tasks.size(); j++) {
            String task = tasks.elementAt(j);
            results[0][j] = task;
            final Expression f;
            final Parser p = new Parser();
            try {
                f = p.parse(task);
                int index = 1;
                for (int i = min; i <= max; i += step) {
                    try {
                        results[index][j] = f.calc(i);
                    } catch (Exception e) {
                        results[index][j] = e.getMessage();
                    } finally {
                        index++;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + ": " + task);
                results[1][j] = "ERROR";
            }
        }

        write(fileOut, results);
    }

    private static void createIfNeeded(final File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Created new file");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
    }

    protected static Vector<String> read(final File file) {
        Vector<String> result = new Vector<String>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                StringBuilder task = new StringBuilder();
                task.append(scanner.nextLine());
                result.add(task.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return result;
    }

    protected static void write(final File file, final Object[][] arr) {
        try (PrintWriter writer = new PrintWriter(file)){
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j< arr[0].length; j++) {
                    if (arr[1][j] != "ERROR") {
                        writer.print(arr[i][j] + "    ");
                    }
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void main(String[] args) {
        FileWork f = new FileWork();
        final String path = "files\\main\\";
        f.doCalculations(path + "tasks.txt", path + "results.txt", "1 : 10");
    }
}
