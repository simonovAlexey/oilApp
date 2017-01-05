package com.simonov;

import com.simonov.model.Oil;

import static java.lang.Math.log10;

/**
 * Created by Алексей on 05.01.2017.
 */
public class MixingUtil {

    public Oil doMixingOil(Oil oilA, Oil oilB) {
        int oilAVolume = oilA.getVolume();
        int oilBVolume = oilB.getVolume();
        double oilAViscosity = oilA.getViscosity();
        double oilBViscosity = oilB.getViscosity();

        int mixVolume = oilAVolume + oilBVolume;

        double mixViscosity = getViscosityByOils(oilAViscosity,oilAVolume, oilBViscosity,oilBVolume );
        return new Oil(mixViscosity, mixVolume);
    }

    public double getViscosityByNeededOil(Oil oilA, double neededViscosity, int neededVolume) {
        double oilAViscosity = oilA.getViscosity();
        int oilAVolume = oilA.getVolume();
        return getViscosityByParam(oilAViscosity, oilAVolume, neededViscosity, neededVolume);

    }

    public double getViscosityByParam(double oilAViscosity, int oilAVolume, double neededViscosity, double neededVolume) {
        double oilBVolume = neededVolume - oilAVolume;

        double a1 = logLog(oilAViscosity);
        double needed1 = logLog(neededViscosity);
        double s1 = (neededVolume * needed1 - oilAVolume * a1) / oilBVolume;
        return powPow(s1);
    }

    public double getVolumeByNeededOil(Oil oilA, double oilBViscosity, double neededViscosity) {
        double oilAViscosity = oilA.getViscosity();
        int oilAVolume = oilA.getVolume();
        if (neededViscosity > oilAViscosity && neededViscosity > oilBViscosity) throw new IllegalArgumentException();
        if (oilAViscosity == neededViscosity) return 0;
        double minV, maxV;
        if (oilAViscosity > oilBViscosity) {
            maxV = oilAViscosity;
            minV = oilBViscosity;
        } else {
            maxV = oilBViscosity;
            minV = oilAViscosity;
        }

        double r = oilA.getVolume() * (maxV / minV)*2;
        double med = 0,l = 0, resulViscosity=0;

        while (r-l>1) {
            med = l + (r - l) / 2;

            resulViscosity = Math.round(getViscosityByOils(oilAViscosity, oilAVolume, oilBViscosity, med)*100)/100;
            if (neededViscosity == resulViscosity) return med;
            if (resulViscosity > neededViscosity) l = med;
            else r = med;

        }
        return -1;

    }


    public double getViscosityByOils(double oilAViscosity,int oilAVolume,double oilBViscosity, double oilBVolume ) {
        //  x log log(µ +0.6) + y log log(µ'+0.6) = z log log(µ''+0.6)
        //  x + y = z

        // =10^(10^(((C8*a1)+(J8*b1))/(mixVolume)))-0,6
        // =10^(10^(s1))-0,6
        // =10^(c1)-0,6

        // =10^(10^(((+C8*(LOG(LOG(+C9+0,6))))+(+J8*(LOG(LOG(+J9+0,6)))))/(+C8+J8)))-0,6

        double mixVolume = oilAVolume + oilBVolume;
        double a1 = logLog(oilAViscosity);
        double b1 = logLog(oilBViscosity);
        double s1 = (oilAVolume * a1 + oilBVolume * b1) / mixVolume;

        return powPow(s1);
    }

    private double powPow(double s1) {
        return Math.pow(10, Math.pow(10, s1)) - 0.6;
    }

    private double logLog(double oilAViscosity) {
        return log10(log10(oilAViscosity + 0.6));
    }
}
