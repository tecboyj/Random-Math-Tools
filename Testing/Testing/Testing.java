package Testing;

import java.util.HashMap;
import java.util.Stack;

public class Testing {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String s = fractionToDecimal(1, 14);
        System.out.println(s.substring(s.indexOf("(") + 1, s.indexOf(")")));
        System.out.println(s);
    }

    public static String fractionToDecimal(double num, double den) {
        if (num == 0) return "0";
        if (den == 0) return "";

        String result = "";

        // is result is negative
        if ((num < 0) ^ (den < 0)) {
            result += "-";
        }

        // convert int to long
        //long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = (long) (num / den);
        result += String.valueOf(res);

        // if remainder is 0, return result
        long remainder = (long) ((num % den) * 10);
        if (remainder == 0) return result;

        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }

            // continue
            map.put(remainder, result.length());
            res = (long) (remainder / den);
            result += String.valueOf(res);
            remainder = (long) ((remainder % den) * 10);
        }

        return result;
    }
}
