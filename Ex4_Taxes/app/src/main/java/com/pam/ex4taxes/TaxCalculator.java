package com.pam.ex4taxes;

public class TaxCalculator {

    public static double calcTps(double val) {
        return val * 0.05;
    }

    public static double calcTvq(double val) {
        return val * 0.0975;
    }

    public static double calcTotal(double val) {
        return calcTps(val) + calcTvq(val) + val;
    }
}
