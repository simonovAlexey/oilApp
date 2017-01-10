package com.simonov;

/**
 * Created by Алексей on 06.01.2017.
 * VI - ГОСТ 25371 (ISO 2909)
 */
public class ViscosityUtil {
    public double getViscoAtTemp(double highTemp, double highVisco, double lowTemp, double lowVisco, double temperature) {
        double kVisco = Math.log10(Math.log10(highVisco + 0.7) / Math.log10(lowVisco + 0.7));
        double kTemp = Math.log10(((lowTemp + 273.15) / (highTemp + 273.15)));
        double k5 = kVisco / kTemp;
        double k4 = MathUtil.logLog(lowVisco) + k5 * Math.log10(lowTemp + 273.15);
        double k = k4 - k5 * Math.log10(temperature + 273.15);
        return MathUtil.powPow(k);
    }

    public double getVindex(double highTemp, double highVisco, double lowTemp, double lowVisco) {
        double v40 = (lowTemp == 40) ? lowVisco : getViscoAtTemp(highTemp, highVisco, lowTemp,
                lowVisco, 40);
        double v100 = (highTemp == 100) ? highVisco : getViscoAtTemp(highTemp, highVisco, lowTemp,
                lowVisco, 100);
        if (v100 < 2)
            throw new IllegalArgumentException("viscosity at 100C must be >2.0 sSt, current :" + Math.round(v100 * 100) / 100);

        double vIlow = getVIlow(v100);
        double vIhigh = getVIhigh(v100);
        double n = (Math.log10(vIhigh) - Math.log10(v40)) / Math.log10(v100);
        double v1 = ((vIlow - v40) / (vIlow - vIhigh)) * 100;
        double v2 = (Math.pow(10, n) - 1) / 0.00715 + 100;

        return v40>=vIhigh ? v1:v2;
    }

    private double getVIlow(double v100) {
        double vIlow = 0D;

        if (v100 >= 2 && v100 < 3.8) vIlow = 1.14673 * v100 * v100 + 1.7576 * v100 - 0.109;
        if (v100 >= 3.8 && v100 < 4.4) vIlow = 3.38095 * v100 * v100 - 15.4952 * v100 + 33.196;
        if (v100 >= 4.4 && v100 < 5) vIlow = 2.5 * v100 * v100 - 7.2143 * v100 + 13.812;
        if (v100 >= 5 && v100 < 6.4) vIlow = 0.101 * v100 * v100 + 16.635 * v100 - 45.469;
        if (v100 >= 6.4 && v100 < 7) vIlow = 3.35714 * v100 * v100 - 23.5643 * v100 + 78.466;
        if (v100 >= 7 && v100 < 7.7) vIlow = 0.01191 * v100 * v100 + 21.475 * v100 - 72.87;
        if (v100 >= 7.7 && v100 < 9) vIlow = 0.41858 * v100 * v100 + 16.1558 * v100 - 56.04;
        if (v100 >= 9 && v100 < 12) vIlow = 0.88779 * v100 * v100 + 7.5527 * v100 - 16.6;
        if (v100 >= 12 && v100 < 15) vIlow = 0.7672 * v100 * v100 + 10.7972 * v100 - 38.18;
        if (v100 >= 15 && v100 < 18) vIlow = 0.97305 * v100 * v100 + 5.3135 * v100 - 2.2;
        if (v100 >= 18 && v100 < 22) vIlow = 0.97256 * v100 * v100 + 5.25 * v100 - 0.98;
        if (v100 >= 22 && v100 < 28) vIlow = 0.91413 * v100 * v100 + 7.4759 * v100 - 21.82;
        if (v100 >= 28 && v100 < 40) vIlow = 0.87031 * v100 * v100 + 9.7157 * v100 - 50.77;
        if (v100 >= 40 && v100 < 55) vIlow = 0.84703 * v100 * v100 + 12.6752 * v100 - 133.31;
        if (v100 >= 55 && v100 < 70) vIlow = 0.85921 * v100 * v100 + 11.1009 * v100 - 83.19;
        if (v100 >= 70) vIlow = 0.83531 * v100 * v100 + 14.6731 * v100 - 216.246;
        return vIlow;
    }

    private double getVIhigh(double v100) {
        double vIhigh = 0D;
        if (v100 > 2 && v100 < 3.8) vIhigh = 0.84155 * v100 * v100 + 1.5521 * v100 - 0.077;
        if (v100 >= 3.8 && v100 < 4.4) vIhigh = 0.78571 * v100 * v100 + 1.7929 * v100 - 0.183;
        if (v100 >= 12 && v100 < 15) vIhigh = 0.20073 * v100 * v100 + 8.4658 * v100 - 22.49;
        if (v100 >= 4.4 && v100 < 5) vIhigh = 0.82143 * v100 * v100 + 1.5679 * v100 + 0.119;
        if (v100 >= 5 && v100 < 6.4) vIhigh = 0.04985 * v100 * v100 + 9.1613 * v100 - 18.577;
        if (v100 >= 6.4 && v100 < 7) vIhigh = 0.22619 * v100 * v100 + 7.7369 * v100 - 16.656;
        if (v100 >= 7 && v100 < 7.7) vIhigh = 0.79762 * v100 * v100 - 0.7321 * v100 + 14.61;
        if (v100 >= 7.7 && v100 < 9) vIhigh = 0.05794 * v100 * v100 + 10.5156 * v100 - 28.24;
        if (v100 >= 9 && v100 < 12) vIhigh = 0.26665 * v100 * v100 + 6.7015 * v100 - 10.81;
        if (v100 >= 15 && v100 < 18) vIhigh = 0.28889 * v100 * v100 + 5.9741 * v100 - 4.93;
        if (v100 >= 18 && v100 < 22) vIhigh = 0.24504 * v100 * v100 + 7.416 * v100 - 16.73;
        if (v100 >= 22 && v100 < 28) vIhigh = 0.20323 * v100 * v100 + 9.1267 * v100 - 34.23;
        if (v100 >= 28 && v100 < 40) vIhigh = 0.18411 * v100 * v100 + 10.1015 * v100 - 46.75;
        if (v100 >= 40 && v100 < 55) vIhigh = 0.17029 * v100 * v100 + 11.4866 * v100 - 80.62;
        if (v100 >= 55 && v100 < 70) vIhigh = 0.1713 * v100 * v100 + 11.368 * v100 - 76.94;
        if (v100 >= 70) vIhigh = 0.16841 * v100 * v100 + 11.8493 * v100 - 96.947;
        return vIhigh;
    }

    public static void main(String[] args) {
       /* System.out.println(new ViscosityUtil().getViscoAtTemp(100, 4, 40, 20, 0));
        System.out.println(new ViscosityUtil().getViscoAtTemp(100, 4, 40, 20, 50));
        System.out.println(new ViscosityUtil().getViscoAtTemp(100, 4, 40, 20, 40));*/
        System.out.println(new ViscosityUtil().getVindex(100, 6, 40, 40));
    }
}
