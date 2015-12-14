package ru.spbstu.appmath.kovalev.calculator;

import ru.spbstu.appmath.kovalev.exceptions.CalculationException;

public interface Expression {
    double calc(double x) throws CalculationException;
}