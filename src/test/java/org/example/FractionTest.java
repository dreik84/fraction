package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void create() {
        Fraction properFraction = Fraction.create(3, 4);
        Fraction improperFraction = Fraction.create(4, 3);

        assertEquals(3, properFraction.getNumerator());
        assertEquals(4, properFraction.getDenominator());
        assertEquals(4, improperFraction.getNumerator());
        assertEquals(3, improperFraction.getDenominator());

        assertInstanceOf(Fraction.ProperFraction.class, properFraction);
        assertInstanceOf(Fraction.ImproperFraction.class, improperFraction);

        assertThrows(IllegalArgumentException.class, () -> Fraction.create(3, 0));
    }

    @Test
    void getSetNumerator() {
        int numerator = 4;
        int denominator = 5;
        Fraction fraction = Fraction.create(numerator, denominator);

        assertEquals(numerator, fraction.getNumerator());

        numerator = 3;
        fraction.setNumerator(numerator);

        assertEquals(numerator, fraction.getNumerator());
    }

    @Test
    void getSetDenominator() {
        int numerator = 4;
        int denominator = 5;
        Fraction fraction = Fraction.create(numerator, denominator);

        assertEquals(denominator, fraction.getDenominator());

        denominator = 6;
        fraction.setDenominator(denominator);

        assertEquals(denominator, fraction.getDenominator());
    }

    @Test
    void isProper() {
        Fraction properFraction = Fraction.create(3, 4);
        Fraction improperFraction = Fraction.create(4, 3);

        assertTrue(properFraction.isProper());
        assertFalse(improperFraction.isProper());
    }

    @Test
    void isEquivalent() {
        Fraction fraction = Fraction.create(3, 4);
        Fraction equivalentFraction = Fraction.create(12, 16);
        Fraction notEquivalentFraction = Fraction.create(4, 3);

        assertTrue(fraction.isEquivalent(equivalentFraction));
        assertFalse(fraction.isEquivalent(notEquivalentFraction));
    }

    @Test
    void gcd() {
        assertEquals(1, Fraction.gcd(2, 1));
        assertEquals(5, Fraction.gcd(15, 5));
        assertEquals(8, Fraction.gcd(56, 128));
    }

    @Test
    void lcm() {
        assertEquals(6, Fraction.lcm(2, 3));
        assertEquals(15, Fraction.lcm(15, 5));
        assertEquals(896, Fraction.lcm(56, 128));
    }

    @Test
    void simplifying() {
        Fraction fraction = Fraction.create(25, 45);
        fraction.simplifying();

        assertEquals(5, fraction.getNumerator());
        assertEquals(9, fraction.getDenominator());
    }
}