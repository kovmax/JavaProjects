package ru.spbstu.appmath.kovalev;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Vector;


public class CalcTest extends FileWork {
    private Parser p = new Parser();
    static final String path = "C:\\Users\\Максим\\IdeaProjects\\Lab4\\src\\test\\java\\ru\\spbstu\\appmath\\kovalev\\files\\";
    static final File fileIn1 = new File(path + "tasks.txt");
    static final File fileIn2 = new File(path + "answers.txt");
    private static final Vector<String> data = read(fileIn1);
    private static final Vector<String> answers = read(fileIn2);

   @Test
    public void test() throws Exception{
        for (int i = 0; i < 6; i++) {
            Expression f = p.parse(data.elementAt(i));
            Assert.assertEquals(String.valueOf(f.calc(i)), answers.elementAt(i));
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testException1() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Discrepancy between the number of brackets");
        Expression f = p.parse(data.elementAt(6));
        double result = f.calc(6);
        System.out.println(result);
    }

    @Test
    public void testException2() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Division by zero");
        Expression f = p.parse(data.elementAt(7));
        double result = f.calc(7);
        System.out.println(result);
    }

    @Test
    public void testException3() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Sign error");
        Expression f = p.parse(data.elementAt(8));
        double result = f.calc(8);
        System.out.println(result);
    }

    @Test
    public void testException4() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Extraneous characters");
        Expression f = p.parse(data.elementAt(9));
        double result = f.calc(9);
        System.out.println(result);
    }


}

