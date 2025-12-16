package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void create() {
        Fraction properFraction = Fraction.create(6, 8);
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
    void getNumerator() {
        int numerator = 4;
        int denominator = 5;
        Fraction fraction = Fraction.create(numerator, denominator);

        assertEquals(numerator, fraction.getNumerator());
    }

    @Test
    void getDenominator() {
        int numerator = 4;
        int denominator = 5;
        Fraction fraction = Fraction.create(numerator, denominator);

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
    void compareTo() {
        Fraction lowerFraction = Fraction.create(1, 13);
        Fraction equivalentLowerFraction = Fraction.create(2, 26);
        Fraction mediumFraction = Fraction.create(3, 11);
        Fraction largeFraction = Fraction.create(7, 4);

        assertEquals(0, lowerFraction.compareTo(equivalentLowerFraction));
        assertTrue(lowerFraction.compareTo(mediumFraction) < 0);
        assertTrue(largeFraction.compareTo(mediumFraction) > 0);
    }

    @Test
    void addition() {
        Fraction addend = Fraction.create(2, 5);
        Fraction augend = Fraction.create(1, 5);
        Fraction result = addend.addition(augend);

        assertEquals(3, result.getNumerator());
        assertEquals(5, result.getDenominator());
    }

    @Test
    void subtraction() {
        Fraction minuend = Fraction.create(4, 5);
        Fraction subtrahend = Fraction.create(1, 5);
        Fraction result = minuend.subtraction(subtrahend);

        assertEquals(3, result.getNumerator());
        assertEquals(5, result.getDenominator());
    }

    @Test
    void multiplication() {
        Fraction multiplier1 = Fraction.create(2, 5);
        Fraction multiplier2 = Fraction.create(7, 3);
        Fraction result = multiplier1.multiplication(multiplier2);

        assertEquals(14, result.getNumerator());
        assertEquals(15, result.getDenominator());
    }

    @Test
    void division() {
        Fraction dividend = Fraction.create(2, 3);
        Fraction divisor = Fraction.create(4, 9);
        Fraction result = dividend.division(divisor);

        assertEquals(3, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    void getReciprocal() {
        Fraction fraction = Fraction.create(2, 5);
        Fraction reciprocal = fraction.getReciprocal();

        assertEquals(5, reciprocal.getNumerator());
        assertEquals(2, reciprocal.getDenominator());
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

    @Test
    void getPrimeDivisorsOfDenominator() {
        Fraction fraction = Fraction.create(7, 40);

        List<Integer> expected = List.of(2, 2, 2, 5);
        List<Integer> actual = fraction.getPrimeDivisorsOfDenominator();

        assertEquals(expected, actual);
    }

    @Test
    void checkPowerOfTen() {
        Fraction fraction = Fraction.create(7, 20);
        List<Integer> primeDivisors = fraction.getPrimeDivisorsOfDenominator();

        assertTrue(fraction.checkPowerOfTen(primeDivisors));

        fraction = Fraction.create(7, 22);
        primeDivisors = fraction.getPrimeDivisorsOfDenominator();

        assertFalse(fraction.checkPowerOfTen(primeDivisors));
    }

    @Test
    void convertToPowerOfTen() {
        Fraction fraction = Fraction.create(3, 4);
        List<Integer> primeDivisors = fraction.getPrimeDivisorsOfDenominator();
        fraction.convertToPowerOfTen(primeDivisors);

        assertEquals(75, fraction.getNumerator());
        assertEquals(100, fraction.getDenominator());
    }

    @Test
    void getDecimalString() {
        Fraction fraction = Fraction.create(3, 4);
        fraction.convertToPowerOfTen(fraction.getPrimeDivisorsOfDenominator());

        assertEquals("0.75", fraction.getDecimalString());

        fraction = Fraction.create(33, 4);
        fraction.convertToPowerOfTen(fraction.getPrimeDivisorsOfDenominator());

        assertEquals("8.25", fraction.getDecimalString());

        fraction = Fraction.create(3, 64);
        fraction.convertToPowerOfTen(fraction.getPrimeDivisorsOfDenominator());

        assertEquals("0.046875", fraction.getDecimalString());
    }

    @Test
    void longDivision() {
        Fraction fraction = Fraction.create(1, 3);

        assertEquals("0.333333333", fraction.longDivision());

        fraction = Fraction.create(113, 3);

        assertEquals("37.666666666", fraction.longDivision());
    }
}
