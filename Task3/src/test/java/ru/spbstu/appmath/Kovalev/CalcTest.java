package ru.spbstu.appmath.Kovalev;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;



public class CalcTest {
    private Parser p = new Parser();

    private static String[] data = {
            "x",
            "(5 + x) / x",
            "x - 4.32 + 5 * (-1.9 + x)",
            "x + (x + 10.324)*x/((x) - 2.5)",
            "-5.0",
            "7.32 * 5 - (x * 10)/(x -10) ",
            "(((((((x * 5)- 7) / x) / 10) - 7) * x) + x",
            "x + (x + 10.324)*x/((x) - 7.0)"
    };

    private static Object[] answers = {
            0.0,
            6.0,
            17.18,
            82.944,
            -5.0,
            46.6
    };

   @Test
    public void test() throws Exception{
        for (int i = 0; i < 6; i++) {
            Expression f = p.parse(data[i]);
            Assert.assertEquals(f.calc(i), answers[i]);
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testException1() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Syntax error");
        Expression f = p.parse(data[6]);
        double result = f.calc(6);
        System.out.println(result);
    }


    @Test
    public void testException2() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Calculation error");
        Expression f = p.parse(data[7]);
        double result = f.calc(7);
        System.out.println(result);
    }


}

