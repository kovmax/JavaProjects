package ru.spbstu.appmath.kovalev.calculator;

public class Const implements Expression {
    private double value;

    public Const(double val) {
        this.value = val;
    }

    public double calc(double x) {
        return this.value;
    }

}