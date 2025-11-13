package org.example;

public class FractionApp {
    public static void main(String[] args) {

        Fraction properFraction = Fraction.create(3, 4);
        Fraction improperFraction = Fraction.create(4, 3);

        System.out.println(properFraction instanceof Fraction.ProperFraction);
        System.out.println(improperFraction instanceof Fraction.ImproperFraction);
    }
}
