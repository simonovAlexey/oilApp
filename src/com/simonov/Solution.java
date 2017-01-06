package com.simonov;

import com.simonov.model.Oil;

/**
 * Created by Алексей on 05.01.2017.
 */
public class Solution {
    public static void main(String[] args) {
        MixingUtil mixingUtil = new MixingUtil();
        Oil oilA = new Oil(320f,36);
        Oil oilB = new Oil(680f,150);
        Oil oilC = new Oil(320f,1000);
//        System.out.println(oilA);
//        System.out.println(oilB);
//        System.out.println(mixingUtil.getViscosityByOils(32,100,150,2343));
        System.out.println("expected 177,3 current: "+ mixingUtil.getVolumeByNeededOil(oilA,180,220));
        System.out.println("expected 177,3 current: "+ mixingUtil.getVolumeByNeededOil(oilA,200,220));
        System.out.println("expected 177,3 current: "+ mixingUtil.getVolumeByNeededOil(oilA,180,198));

    }
}
