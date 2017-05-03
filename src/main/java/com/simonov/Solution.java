package com.simonov;

import com.simonov.model.Oil;
import com.simonov.service.MixingService;

public class Solution {
    public static void main(String[] args) {
        MixingService mixingService = new MixingService();
        Oil oilA = new Oil(320f,36);
        Oil oilB = new Oil(680f,150);
        Oil oilC = new Oil(320f,1000);
//        System.out.println(oilA);
//        System.out.println(oilB);
//        System.out.println(mixingService.getViscosityByOils(32,100,150,2343));
        System.out.println("expected 177,3 current: "+ mixingService.getVolumeByNeededOil(oilA,180,220));
        System.out.println("expected 177,3 current: "+ mixingService.getVolumeByNeededOil(oilA,200,220));
        System.out.println("expected 177,3 current: "+ mixingService.getVolumeByNeededOil(oilA,180,198));

    }
}
