package ru.spbstu.appmath.kovalev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.kovalev.calculator.Expression;
import ru.spbstu.appmath.kovalev.calculator.Parser;
import ru.spbstu.appmath.kovalev.exceptions.CalculatorAppException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;


@RunWith(Parameterized.class)
public class CalcTest {
    private static final Parser PARSER = new Parser();
    private String expression;
    private String result;
    private int variable;

    private static ArrayList<Object[]> initializeTestData() throws FileNotFoundException {
        String path = Paths.get("src", "files", "test", "tasks.txt").toString();
        Scanner f = new Scanner(new File(path));
        ArrayList<Object[]> tests = new ArrayList<Object[]>();
        int var = 0;
        while (f.hasNextLine()) {
            String line = f.nextLine();
            String[] data = line.split("\\|");
            tests.add(new Object[]{data[0], data[1], var});
            var++;
        }
        return tests;
    }

    public CalcTest(String expression, String result, int variable) {
        this.expression = expression;
        this.result = result;
        this.variable = variable;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws FileNotFoundException{
        return initializeTestData();
    }

    @Test
    public void test() {
        try {
            Expression f = PARSER.parse(expression);
            Assert.assertEquals(String.valueOf(f.calc(variable)), result);
        } catch (CalculatorAppException e) {
            Assert.assertEquals(e.getMessage(), result);
        }
    }
}

