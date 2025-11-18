package org.example;

public abstract class Fraction {
    protected int numerator;
    protected int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction create(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be zero");
        } else if (numerator < denominator) {
            return new ProperFraction(numerator, denominator);
        } else {
            return new ImproperFraction(numerator, denominator);
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public boolean isProper() {
        return numerator < denominator;
    }

    public boolean isEquivalent(Fraction fraction) {
        int lcm = lcm(denominator, fraction.denominator);
        return (numerator * lcm / denominator) == (fraction.numerator * lcm / fraction.denominator);
    }

    public void simplifying() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
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
