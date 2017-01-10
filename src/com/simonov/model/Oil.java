package com.simonov.model;

import java.util.Locale;

/**
 * Created by Алексей on 05.01.2017.
 */
public class Oil {
    private double viscosity;
    private int volume;
    private double densityAt20;
    int viscIndex;

    public Oil(double viscosity, int volume) {
        this(viscosity,volume,1.0);
    }

    public Oil(double viscosity, int volume, double densityAt20) {
        this.viscosity = viscosity;
        this.volume = volume;
        this.densityAt20 = densityAt20;
    }

    public int getViscIndex() {
        return viscIndex;
    }

    public void setViscIndex(int viscIndex) {
        this.viscIndex = viscIndex;
    }

    public double getDensityAt20() {
        return densityAt20;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getViscosity() {
        return viscosity;
    }

    public void setViscosity(float viscosity) {
        this.viscosity = viscosity;
    }

    @Override
    public String toString() {
        return "Oil{" +
                "viscosity=" + String.format(new Locale("ru"), "%(.4f", viscosity) +
                "  volume=" + volume +
                '}';
    }


}
