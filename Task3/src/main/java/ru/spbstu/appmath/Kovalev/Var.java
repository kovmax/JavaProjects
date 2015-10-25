package ru.spbstu.appmath.Kovalev;

public class Var {
    private char name;
    private Double value;

    public Var(char name) {
        this.name = name;
        this.value = 0.0;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public char getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
