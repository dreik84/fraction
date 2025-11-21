package org.example;

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
