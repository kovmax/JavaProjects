package ru.spbstu.appmath.kovalev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.kovalev.calculator.Expression;
import ru.spbstu.appmath.kovalev.calculator.Parser;
import ru.spbstu.appmath.kovalev.exceptions.CalculationException;
import ru.spbstu.appmath.kovalev.exceptions.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;


@RunWith(Parameterized.class)
public class CalcTest {
    private static final Parser p = new Parser();
    private String expression;
    private String result;
    private int variable;

    private static ArrayList<Object[]> initializeTestData() throws FileNotFoundException {
        final String PATH = Paths.get("src", "files", "test", "tasks.txt").toString();
        Scanner f = new Scanner(new File(PATH));
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
    public static Collection<Object[]> testData() throws Exception{
        return initializeTestData();
    }

    @Test
    public void test() {
        try {
            Expression f = p.parse(expression);
            Assert.assertEquals(String.valueOf(f.calc(variable)), result);
        } catch (SyntaxException e) {
            Assert.assertEquals(e.getMessage(), result);
        } catch (CalculationException e) {
            Assert.assertEquals(e.getMessage(), result);
        }
    }
}

