package ru.spbstu.appmath.kovalev;

import ru.spbstu.appmath.kovalev.calculator.Expression;
import ru.spbstu.appmath.kovalev.calculator.Parser;
import ru.spbstu.appmath.kovalev.exceptions.SyntaxException;

import java.io.*;
import java.util.*;

public class FileWork {
    public void doCalculations(final String in, final String out, final int min, final int max, final int step) {
        final File fileIn = new File(in);
        final File fileOut = new File(out);

        createIfNeeded(fileOut);
        final List<String> tasks = read(fileIn);
        final List<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();

        for (int j = 0; j < tasks.size(); j++) {
            String task = tasks.get(j);
            result.add(j, new ArrayList<Object>());
            result.get(j).add(0, task);
            final Expression f;
            final Parser p = new Parser();
            try {
                f = p.parse(task);
                int index = 1;
                for (int i = min; i <= max; i += step) {
                    try {
                        result.get(j).add(index, f.calc(i));
                    } catch (Exception e) {
                        result.get(j).add(index, e.getMessage());
                    } finally {
                        index++;
                    }
                }
            } catch (SyntaxException e) {
                System.out.println(e.getMessage() + ": " + task);
            }
        }

        write(fileOut, result);
    }

    private static void createIfNeeded(final File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Created new file: " + file.getCanonicalPath());
            } else {
                System.out.println("File already exists at " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
    }

    protected static ArrayList<String> read(final File file) {
        ArrayList<String> result = new ArrayList<>();
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

    protected static void write(final File file, final List<ArrayList<Object>> arr) {
        try (PrintWriter writer = new PrintWriter(file)){
            int len[] = maxLengthArr(arr);
            for (int i = 0; i < arr.get(0).size(); i++) {
                for (int j = 0; j < arr.size(); j++) {
                    if(arr.get(j).size() != 1) {
                        int numSpaces = len[j] - arr.get(j).get(i).toString().length();
                        for (int k = 0; k < numSpaces; k++)
                            writer.print(" ");
                        writer.print(arr.get(j).get(i));
                        if (j - 1 != arr.size())
                            writer.print("   ");
                    }
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static int[] maxLengthArr(List<ArrayList<Object>> a) {
        int max[] = new int[a.size()];
        for (int j = 0; j < a.size(); j++) {
            max[j] = a.get(j).get(0).toString().length();
            for (int i = 0; i < a.get(j).size(); i++)
                if (a.get(j).get(i).toString().length() > max[j])
                    max[j] = a.get(j).get(i).toString().length();
        }
        return max;
    }

}
