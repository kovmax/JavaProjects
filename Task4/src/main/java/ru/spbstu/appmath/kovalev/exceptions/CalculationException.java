package ru.spbstu.appmath.kovalev.exceptions;

public class CalculationException extends Exception {
    public CalculationException() {
        super("Calculation error");
    }

    public CalculationException(String message) {
        super(message);
    }
}
