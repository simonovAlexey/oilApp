package com.simonov.util;

import static java.lang.Math.log10;


public class MathUtil {

    private MathUtil() {
    }

    public static double powPow(double s1) {
        return Math.pow(10, Math.pow(10, s1)) - 0.7;
    }

    public static double logLog(double oilAViscosity) {
        return log10(log10(oilAViscosity + 0.7));
    }
}
