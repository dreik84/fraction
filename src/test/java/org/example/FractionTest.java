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
    void getNumerator() {
    }

    @Test
    void setNumerator() {
    }

    @Test
    void getDenominator() {
    }

    @Test
    void setDenominator() {
    }

    @Test
    void isProper() {
    }
}