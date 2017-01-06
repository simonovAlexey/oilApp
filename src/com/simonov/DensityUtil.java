package com.simonov;

import com.simonov.model.Oil;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Алексей on 06.01.2017.
 * ГОСТ 3900-85
 *Petroleum and petroleum products. Methods for determination of density
 *
 */
public class DensityUtil {

    private static final Map<Pair<Double, Double>, Double> INIT_DENSITY = getInitMap();

    private static Map<Pair<Double, Double>, Double> getInitMap() {
        Map<Pair<Double, Double>, Double> map = new HashMap<>();
        String basePath = new File("").getAbsolutePath();
        List<String[]> collect = null;
        try {
            collect = Files.lines(Paths.get(basePath, "\\src\\resources\\densitymap.ini"), StandardCharsets.UTF_8).
                    map((p -> (Arrays.stream(p.split("=")).toArray(String[]::new)))).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        for (String[] strings : collect) {
            String[] splDens = strings[0].split("-");
            map.put(new Pair<>(Double.valueOf(splDens[0]), Double.valueOf(splDens[1])), Double.valueOf(strings[1]));
        }
        return map;

    }

    private double getTempCorrectionKoff(Double densityAt20) {
        for (Pair<Double, Double> pair : INIT_DENSITY.keySet()) {
            if (pair.getKey() < densityAt20 && pair.getValue() > densityAt20) return INIT_DENSITY.get(pair);
        }
        throw new IllegalArgumentException("wrong density of petroleum products");
    }

    public double getDensity(Oil oil, int temperature) {
        double tempCorrectionKoff = getTempCorrectionKoff(oil.getDensityAt20());
        if (temperature > 20) {
            return oil.getDensityAt20() - (temperature - 20) * tempCorrectionKoff;
        } else return oil.getDensityAt20() + (20 - temperature) * tempCorrectionKoff;

    }


    public static void main(String[] args) {
        DensityUtil densityUtil = new DensityUtil();
        Oil oil = new Oil(20, 100, 0.8240);
        Oil oil2 = new Oil(20, 100, 0.7520);
        System.out.println(densityUtil.getDensity(oil,23));
        System.out.println(densityUtil.getDensity(oil2,-12));

    }

}
