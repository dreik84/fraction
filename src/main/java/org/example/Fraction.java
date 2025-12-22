package org.example;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public abstract class Fraction implements Comparable<Fraction> {
    protected int numerator;
    protected int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction create(int numerator, int denominator) {
        Fraction fraction;

        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be zero");
        } else if (numerator < denominator) {
            fraction = new ProperFraction(numerator, denominator);
        } else {
            fraction = new ImproperFraction(numerator, denominator);
        }

        return fraction.simplifying();
    }

    public boolean isProper() {
        return Math.abs(numerator) < Math.abs(denominator);
    }

    public boolean isEquivalent(Fraction fraction) {
        int lcm = lcm(denominator, fraction.denominator);
        return (numerator * lcm / denominator) == (fraction.numerator * lcm / fraction.denominator);
    }

    public Fraction simplifying() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        return this;
    }

    @Override
    public int compareTo(Fraction fraction) {
        int lcm = lcm(denominator, fraction.denominator);
        return (numerator * lcm / denominator) - (fraction.numerator * lcm / fraction.denominator);
    }

    public Fraction addition(Fraction augend) {
        int resNumerator = numerator * augend.denominator + augend.numerator * denominator;
        int resDenominator = denominator * augend.denominator;

        return create(resNumerator, resDenominator);
    }

    public Fraction subtraction(Fraction subtrahend) {
        int resNumerator = numerator * subtrahend.denominator - subtrahend.numerator * denominator;
        int resDenominator = denominator * subtrahend.denominator;

        return create(resNumerator, resDenominator);
    }

    public Fraction multiplication(Fraction multiplier) {
        int resNumerator = numerator * multiplier.numerator;
        int resDenominator = denominator * multiplier.denominator;

        return create(resNumerator, resDenominator);
    }

    public Fraction division(Fraction divisor) {
        Fraction reciprocal = divisor.getReciprocal();
        return multiplication(reciprocal);
    }

    public Fraction exponentiation(int exponent) {
        int resNumerator = numerator;
        int resDenominator = denominator;

        if (exponent < 0) {
            resNumerator = denominator;
            resDenominator = numerator;
            exponent = -exponent;
        }

        resNumerator = power(resNumerator, exponent);
        resDenominator = power(resDenominator, exponent);

        return Fraction.create(resNumerator, resDenominator);
    }

    public int power(int base, int exponent) {
        int res = 1;

        for (int i = 1; i <= exponent; i++) {
            res *= base;
        }

        return res;
    }

    public Fraction getReciprocal() {
        return create(denominator, numerator);
    }

    public String convertToDecimal() {
        StringBuilder sb = new StringBuilder();
        List<Integer> primeDivisors = getPrimeDivisorsOfDenominator();

        if (checkPowerOfTen(primeDivisors)) {
            convertToPowerOfTen(primeDivisors);
            sb.append(getDecimalString());
        } else {
            String decimalFraction = longDivision();
            Map<String, Integer> boundaries = checkPeriodic(decimalFraction);

            System.out.println(boundaries);

            if (boundaries != null) {
                sb.append(getPeriodicContinuedFraction(decimalFraction, boundaries));
            } else {
                sb.append(decimalFraction);
            }
        }

        return sb.toString();
    }

    public Map<String, Integer> checkPeriodic(String decimalFraction) {
        String fractionalPart = decimalFraction.trim().split("\\.")[1];
        int len = fractionalPart.length();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (fractionalPart.charAt(i) == fractionalPart.charAt(j)) {
                    boolean found = true;

                    for (int k = 1; k < len - j; k++) {
                        if (fractionalPart.charAt(i + k) != fractionalPart.charAt(j + k)) {
                            found = false;
                            break;
                        }
                    }

                    if (found) return Map.of("start", i, "end", j);
                }
            }
        }

        return null;
    }

    public String getPeriodicContinuedFraction(String decimalFraction, Map<String, Integer> boundaries) {
        String[] parts = decimalFraction.trim().split("\\.");
        StringBuilder sb = new StringBuilder(parts[0]).append(".");
        String fractionalPart = parts[1];

        for (int i = 0; i < boundaries.get("end"); i++) {
            if (i == boundaries.get("start")) sb.append("(");
            sb.append(fractionalPart.charAt(i));
        }

        System.out.println(decimalFraction);

        sb.append(")");

        return sb.toString();
    }

    public String longDivision() {
        StringBuilder sb = new StringBuilder();
        int num = numerator;
        int den = denominator;

        for (int i = 0; i < 20; i++) {
            int rem = num % den;
            int div = num / den;

            sb.append(div);
            if (rem == 0) {
                break;
            } else {
                num = rem * 10;

                if (i == 0) {
                    sb.append(".");
                }
            }
        }

        return sb.toString();
    }

    public String getDecimalString() {
        StringBuilder sb = new StringBuilder();

        String num = numerator + "";
        String den = denominator + "";

        int dif = den.length() - num.length();

        if (dif >= 1) {
            sb.append("0.").repeat("0", dif - 1);
            for (char ch : num.toCharArray()) {
                sb.append(ch);
            }
        } else {
            for (int i = 0; i < num.length(); i++) {
                if (den.length() + i - 1 == num.length()) {
                    sb.append(".");
                }

                sb.append(num.charAt(i));
            }
        }

        return sb.toString();
    }

    public void convertToPowerOfTen(List<Integer> primeDivisors) {
        int countOf2 = 0;
        int countOf5 = 0;

        for (int prime : primeDivisors) {
            if (prime == 2) countOf2++;
            if (prime == 5) countOf5++;
        }

        int multiplier = (countOf2 < countOf5) ? 2 : 5;
        int exponent = Math.abs(countOf2 - countOf5);

        for (int i = 0; i < exponent; i++) {
            numerator *= multiplier;
            denominator *= multiplier;
        }
    }

    public boolean checkPowerOfTen(List<Integer> primeDivisors) {

        for (int prime : primeDivisors) {
            if (prime != 2 && prime != 5) return false;
        }

        return true;
    }

    public List<Integer> getPrimeDivisorsOfDenominator() {
        List<Integer> res = new ArrayList<>();
        int primeDivisor = Prime.getFirst();
        int den = denominator;

        while (den > 1) {
            if (den % primeDivisor == 0) {
                res.add(primeDivisor);
                den /= primeDivisor;
                primeDivisor = Prime.getFirst();
            } else {
                primeDivisor = Prime.getNext(primeDivisor);
            }
        }

        return res;
    }

    // Least Common Multiple - Наименьшее общее кратное - НОК
    public static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    // Greatest Common Divisor - Наибольший общий делитель - НОД
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }

        return a;
    }

    public static class ProperFraction extends Fraction {

        private ProperFraction(int numerator, int denominator) {
            super(numerator, denominator);
        }
    }

    public static class ImproperFraction extends Fraction {

        private ImproperFraction(int numerator, int denominator) {
            super(numerator, denominator);
        }
    }
}
