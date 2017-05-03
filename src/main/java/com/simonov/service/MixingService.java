package com.simonov.service;


import com.simonov.model.Oil;
import org.springframework.stereotype.Component;

import static com.simonov.util.MathUtil.logLog;
import static com.simonov.util.MathUtil.powPow;

/**
 * Created by Алексей on 05.01.2017.
 * ASTM D7152 − 11
 * Standard Practice for
 * Calculating Viscosity of a Blend of Petroleum Products
 * <p>
 * x log log(µ +0.7) + y log log(µ'+0.7) = z log log(µ''+0.7)
 * x + y = z
 */
@Component
public class MixingService {

    public Oil doMixingOil(Oil oilA, Oil oilB) {
        int oilAVolume = oilA.getVolume();
        int oilBVolume = oilB.getVolume();
        double oilAViscosity = oilA.getViscosityAt40();
        double oilBViscosity = oilB.getViscosityAt40();

        int mixVolume = oilAVolume + oilBVolume;

        double mixViscosity = getViscosityByOils(oilA, oilB);
        return new Oil(mixViscosity, mixVolume);
    }


   /* public double getViscosityByNeededOil(Oil oilA, double neededViscosity, int neededVolume) {
        double oilAViscosity = oilA.getViscosityAt40();
        int oilAVolume = oilA.getVolume();
        return getViscosityByParam(oilAViscosity, oilAVolume, neededViscosity, neededVolume);

    }
*/

    public double getVolumeByNeededOil(Oil oilA, double oilBViscosity, double neededViscosity) {

        double oilAViscosity = oilA.getViscosityAt40();
        double minV, maxV;
        if (oilAViscosity > oilBViscosity) {
            maxV = oilAViscosity;
            minV = oilBViscosity;
        } else {
            maxV = oilBViscosity;
            minV = oilAViscosity;
        }
        if (oilAViscosity == neededViscosity) return 0;
        if (neededViscosity < minV || neededViscosity > maxV)
            throw new IllegalArgumentException("needed Viscosity must be between current");

        double k1 = logLog(oilAViscosity);
        double k2 = logLog(oilBViscosity);
        double k3 = logLog(neededViscosity);

        return (k1 - k3) * oilA.getVolume() / (k3 - k2);

    }


    public double getViscosityByOils(Oil oilA, Oil oilB) {

        double a1 = logLog(oilA.getViscosityAt40());
        double b1 = logLog(oilB.getViscosityAt40());
        int oilBVolume1 = oilB.getVolume();
        int oilAVolume1 = oilA.getVolume();
        double s1 = (oilAVolume1 * a1 + oilBVolume1 * b1) / (oilAVolume1 + oilBVolume1);
        return powPow(s1);
    }

    private double getViscosityByParam(double oilAViscosity, int oilAVolume, double neededViscosity, double neededVolume) {
        double oilBVolume = neededVolume - oilAVolume;

        double a1 = logLog(oilAViscosity);
        double a2 = logLog(neededViscosity);
        return powPow((neededVolume * a2 - oilAVolume * a1) / oilBVolume);
    }

}
