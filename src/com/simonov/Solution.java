package com.simonov;

import com.simonov.model.Oil;

/**
 * Created by Алексей on 05.01.2017.
 */
public class Solution {
    public static void main(String[] args) {
        MixingUtil mixingUtil = new MixingUtil();
        Oil oilA = new Oil(320f,100);
        Oil oilB = new Oil(68f,150);
//        System.out.println(oilA);
//        System.out.println(oilB);
        System.out.println(mixingUtil.doMixingOil(oilA,oilB));
//        System.out.println(mixingUtil.getViscosityByOils(32,100,150,2343));
        System.out.println(mixingUtil.getVolumeByNeededOil(oilA,180,238));

    }
}
