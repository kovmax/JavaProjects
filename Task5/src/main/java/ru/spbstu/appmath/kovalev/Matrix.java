package ru.spbstu.appmath.kovalev;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Matrix {
    private final int nRows;
    private final int nColumns;
    private final double[][] data;

    public Matrix(final int nRows, final int nColumns) {
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.data = new double[nRows][nColumns];
    }

    public Matrix(File file)throws IOException {
        int nRows = 0;
        int nColumns = 0;
        List<String []> data = new ArrayList<>();
        Scanner input = new Scanner(file);
        while(input.hasNextLine()) {
            String[] values = input.nextLine().split(" ");
            if (nRows == 0)
                nColumns = values.length;
            if (nRows > 0 && nColumns != values.length)
                throw new IOException("Invalid matrix: " + file.getName());
            nRows++;
            data.add(values);
        }
        input.close();

        this.nRows = nRows;
        this.nColumns = nColumns;
        this.data = new double[nRows][nColumns];
        for(int i = 0; i < this.nRows; i++) {
            for(int j = 0; j < this.nColumns; j++) {
                this.data[i][j] = Double.parseDouble(data.get(i)[j]);
            }
        }
    }

    public int getRows(){
        return this.nRows;
    }

    public int getColumns(){
        return this.nColumns;
    }

    public Double get(final int i, final int j) {
        return this.data[i][j];
    }

    public void set(final int i, final int j, final double value) {
        this.data[i][j] = value;
    }

    public void createIdentity() throws IOException {
        if (nRows != nColumns)
            throw new IOException("Rows count doesn't match with columns count");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                if (i != j)
                    data[i][j] = 0;
                else
                    data[i][j] = 1;
            }
        }
    }

    public void createRandom(final double leftBound, final double rightBound) {
        Random random = new Random();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                data[i][j] = (rightBound - leftBound) * random.nextDouble() + leftBound;
            }
        }
    }

    public void print() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                System.out.print(data[i][j]);
                if (j - 1 != nColumns)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void printInFile(File file) throws IOException {
        createIfNeeded(file);
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                printWriter.print(data[i][j]);
                if (j - 1 != nColumns)
                    printWriter.print(" ");
            }
            printWriter.println();
        }
        printWriter.close();
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

}
