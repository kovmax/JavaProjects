package ru.spbstu.appmath.kovalev.research;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Report {
    private ArrayList<String> messages;

    public Report() {
        messages = new ArrayList<>();
    }

    public void addMessage(final String msg) {
        messages.add(msg);
    }

    public void printReport(final File file) throws IOException {
        createIfNeeded(file);
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < messages.size(); i++)
            printWriter.println(messages.get(i));
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
