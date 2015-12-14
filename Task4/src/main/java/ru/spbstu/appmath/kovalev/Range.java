package ru.spbstu.appmath.kovalev;

public class Range {
    private int min;
    private int max;
    private int step;

    public Range(String s)  {
        s = s.replaceAll(" ", "");

        String min = s.substring(0, s.indexOf(':'));
        s = s.substring(s.indexOf(':') + 1);
        this.min = Integer.parseInt(min);

        int index = s.indexOf('[');
        if (index == -1) {
            this.max = Integer.parseInt(s);
            s = "";
        } else {
            String max = s.substring(0, index);
            s = s.substring(index + 1);
            this.max = Integer.parseInt(max);
        }

        if (!s.equals("")) {
            s = s.substring(s.indexOf(':') + 1, s.length());
            String step = s.substring(0, s.indexOf(']'));
            this.step = Integer.parseInt(step);
        } else {
            this.step = 1;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getStep() {
        return step;
    }
}
