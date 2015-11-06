package ru.spbstu.appmath.Kovalev;

public class Parser {
    public Parser() {
    }

    public Expression parse(String s) throws Exception{
        /** Избавились от пробелов */
        String trimmed = s.trim();

        /** Ищем плюс или минус вне скобок */
        int addSignPos = findPosOperator(trimmed, '+');
        int subSignPos = findPosOperator(trimmed, '-');
        if (addSignPos != -1 && (subSignPos == -1 || addSignPos <  subSignPos)) {
            return new Complex(parse(trimmed.substring(0, addSignPos)), parse(trimmed.substring(addSignPos + 1)), '+');
        } else if (subSignPos != -1) {
            return new Complex(parse(trimmed.substring(0, subSignPos)), parse(trimmed.substring(subSignPos + 1)), '-');
        }

        /** Ищем умножить вне скобок */
        int mulSignPos = findPosOperator(trimmed, '*');
        if (mulSignPos != -1) {
            return new Complex(parse(trimmed.substring(0, mulSignPos)), parse(trimmed.substring(mulSignPos + 1)), '*');
        }

        /** Ищем делить вне скобок */
        int divSignPos = findPosOperator(trimmed, '/');
        if (divSignPos != -1) {
            return new Complex(parse(trimmed.substring(0, divSignPos)), parse(trimmed.substring(divSignPos + 1)), '/');
        }

        /** Ищем содержимое в скобках и рекурсивно парсим */
        final int openBracketPos = trimmed.indexOf('(');
        final int closeBracketPos = trimmed.lastIndexOf(')');
        if (openBracketPos != -1 && closeBracketPos != -1 && openBracketPos < closeBracketPos) {
            return parse(trimmed.substring(openBracketPos + 1, closeBracketPos));
        }

        /** Ищем константу или переменную в случае отсутствия скобок */
        if (openBracketPos == -1 && closeBracketPos == -1) {
            if (isNumber(trimmed)) {
                if (trimmed.equals(""))
                    return new Const(0);
                return new Const(Double.parseDouble(trimmed));
            }
            if ("x".equals(trimmed)) {
                return new Var();
            }
            throw new Exception("Syntax error!");
        }
        throw new Exception("Syntax error!");
    }

    private static boolean isNumber(String s) {
        if (s.equals(""))
            return true;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c) && c != '.')
                return false;
        }
        return true;
    }

    private int findPosOperator(String trimmed, char op) throws Exception{
        int index = 0;
        int pos;
        do {
            pos = trimmed.indexOf(op, index);
            index = getIndexLastClose(trimmed, pos);
        } while (inBrackets(trimmed, pos));
        return pos;
    }

    private static boolean inBrackets(String s, int i) {
        if (i != -1)
        {
            int cOpen = 0;
            int cClose = 0;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '(')
                    cOpen++;
                if (s.charAt(j) == ')')
                    cClose++;
            }
            return cOpen != cClose;
        } else {
            return false;
        }
    }

    private static int getIndexLastClose(String s, int i) throws Exception{
        if (i != -1)
        {
            int cOpen = 0;
            int cClose = 0;
            for (int j = 0; j < i ; j++) {
                if (s.charAt(j) == '(')
                    cOpen++;
                if (s.charAt(j) == ')')
                    cClose++;
            }
            int index = i;
            while (cOpen != cClose) {
                index = s.indexOf(')', index + 1);
                if (index != -1)
                    cClose++;
                else
                    throw new Exception("Syntax error");
            }
            return index + 1;
        }
        else
            return 0;
    }
}
