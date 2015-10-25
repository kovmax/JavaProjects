package ru.spbstu.appmath.Kovalev;

public class Parser
{
    public Var var = new Var('x');

    public double parse(String s) throws Exception
    {
        while(s.contains(" ")) {
            s = s.replace(" ", "");
        }

        Result result = plusMinus(s);
        if (!result.rest.isEmpty()) {
            throw new Exception("Syntax error");
        }
        return result.acc;
    }

    private Result plusMinus(String s) throws Exception
    {
        Result current = mulDiv(s);
        double acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
                break;
            }

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = mulDiv(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
        }
        return new Result(acc, current.rest);
    }

    private Result mulDiv(String s) throws Exception
    {
        Result current = bracket(s);

        double acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }

            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/'))
                return current;

            String next = current.rest.substring(1);
            Result right = bracket(next);

            if (sign == '*') {
                acc *= right.acc;
            } else {
                if (right.acc != 0) {
                    acc /= right.acc;
                } else {
                    throw new Exception("Calculation error");
                }
            }

            current = new Result(acc, right.rest);
        }
    }

    private Result bracket(String s) throws Exception
    {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                throw new Exception("Syntax error");
            }
            return r;
        }
        return variable(s);
    }

    private Result variable(String s) throws Exception
    {
        if (s.charAt(0) == var.getName()) {
                return new Result(var.getValue(), s.substring(1));
        }
        return number(s);
    }

    private Result number(String s) throws Exception
    {
        int i = 0;
        int cDot = 0;
        boolean negative = false;

        if(s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        }

        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            if (s.charAt(i) == '.' && ++cDot > 1) {
                throw new Exception("Syntax error");
            }
            i++;
        }
        if (i == 0) {
            throw new Exception("Syntax error");
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if (negative)
            dPart = -dPart;
        String restPart = s.substring(i);

        return new Result(dPart, restPart);
    }
} 