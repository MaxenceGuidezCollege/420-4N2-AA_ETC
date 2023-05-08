package com.pam.ex4taxes;

public class TaxCalculator {

    public static double calcTps(double val) {
        return Math.round((val * 0.05) * 100.0) / 100.0;
    }

    public static double calcTvq(double val) {
        return Math.round((val * 0.0975) * 100.0) / 100.0;
    }

    public static double calcTotal(double val) {
        double total = calcTps(val) + calcTvq(val) + val;
        return Math.round(total * 100.0) / 100.0;
    }
}
