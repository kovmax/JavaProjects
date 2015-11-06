package ru.spbstu.appmath.Kovalev;

public class Const implements Expression {
    private double value = 0;

    public Const(double val) {
        this.value = val;
    }

    public double calc(double x) {
        return this.value;
    }

}