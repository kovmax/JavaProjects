package ru.spbstu.appmath.kovalev.exceptions;

public class CalculationException extends CalculatorAppException {
    public CalculationException() {
        super("Calculation error");
    }

    public CalculationException(String message) {
        super(message);
    }
}
