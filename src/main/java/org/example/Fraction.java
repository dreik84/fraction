package org.example;

public abstract class Fraction {
    protected int numerator;
    protected int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction create(int numerator, int denominator) {
        if (numerator < denominator) {
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
