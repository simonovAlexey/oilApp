package com.simonov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class Oil {
    private double viscosityAt40;
    private int volume;
    private double densityAt20;
    private int viscIndex;

    public Oil(double viscosityAt40, int volume) {
        this(viscosityAt40,volume,1.0);
    }

    public Oil(double viscosityAt40, int volume, double densityAt20) {
        this.viscosityAt40 = viscosityAt40;
        this.volume = volume;
        this.densityAt20 = densityAt20;
    }

    @Override
    public String toString() {
        return "Oil {" +
                " viscosityAt40=" + String.format(new Locale("ru"), "%(.4f", viscosityAt40) +
                "  volume=" + volume +
                '}';
    }


}
