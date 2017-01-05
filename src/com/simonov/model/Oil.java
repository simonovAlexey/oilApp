package com.simonov.model;

import java.util.Locale;

/**
 * Created by Алексей on 05.01.2017.
 */
public class Oil {
    private double viscosity;
    private int volume;

    public Oil(double viscosity, int volume) {
        this.viscosity = viscosity;
        this.volume = volume;
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
