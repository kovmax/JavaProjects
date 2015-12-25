package ru.spbstu.appmath.kovalev.exceptions;

public class SyntaxException extends CalculatorAppException {
    public SyntaxException() {
        super("Syntax error");
    }

    public SyntaxException(String message) {
        super(message);
    }
}