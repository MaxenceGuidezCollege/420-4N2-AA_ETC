package com.pam.ex4taxes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaxesUnitTest {

//    CALCUL DU TOTAL
    @Test
    public void calculTotalNormalNumber() {
        assertEquals(11.48, TaxCalculator.calcTotal(10), 0.01);
    }
    @Test
    public void calculTotalBigNumber() {
        assertEquals(1147500.00, TaxCalculator.calcTotal(1000000), 0.01);
    }
    @Test
    public void calculTotalSmallNumber() {
        assertEquals(0.01, TaxCalculator.calcTotal(0.00001), 0.01);
    }
    @Test
    public void calculTotalZero() {
        assertEquals(0.00, TaxCalculator.calcTotal(0), 0.01);
    }
    @Test
    public void calculTotalNegativeNumber() {
        assertEquals(-11.48, TaxCalculator.calcTotal(-10), 0.01);
    }

//    CALCUL TPS
    @Test
    public void calculTPSNormalNumber() {
        assertEquals(0.50, TaxCalculator.calcTps(10), 0.01);
    }
    @Test
    public void calculTPSBigNumber() {
        assertEquals(50000.00, TaxCalculator.calcTps(1000000), 0.01);
    }
    @Test
    public void calculTPSSmallNumber() {
        assertEquals(0.01, TaxCalculator.calcTps(0.00001), 0.01);
    }
    @Test
    public void calculTPSZero() {
        assertEquals(0, TaxCalculator.calcTps(0), 0.01);
    }
    @Test
    public void calculTPSNegativeNumber() {
        assertEquals(-0.50, TaxCalculator.calcTps(-10), 0.01);
    }

//    CALCUL TVQ
    @Test
    public void calculTVQNormalNumber() {
        assertEquals(0.98, TaxCalculator.calcTvq(10), 0.01);
    }
    @Test
    public void calculTVQBigNumber() {
        assertEquals(97500.00, TaxCalculator.calcTvq(1000000), 0.01);
    }
    @Test
    public void calculTVQSmallNumber() {
        assertEquals(0.01, TaxCalculator.calcTvq(0.00001), 0.01);
    }
    @Test
    public void calculTVQZero() {
        assertEquals(0, TaxCalculator.calcTvq(0), 0.01);
    }
    @Test
    public void calculTVQNegativeNumber() {
        assertEquals(-0.98, TaxCalculator.calcTvq(-10), 0.01);
    }


}
