package ru.spbstu.appmath.Kovalev;

public class Main {
    public static void main(String[] args) {
        Parser p = new Parser();
        p.var.setValue(Double.parseDouble(args[1]));

        try {
            System.out.println(p.parse(args[0]));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
