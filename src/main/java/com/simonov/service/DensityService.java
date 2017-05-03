package com.simonov.service;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
@Slf4j
public class DensityService {

    private static final Map<Pair<Double, Double>, Double> INIT_DENSITY = getInitMap();

    private static Map<Pair<Double, Double>, Double> getInitMap() {
        Map<Pair<Double, Double>, Double> map = new HashMap<>();
        String basePath = new File("").getAbsolutePath();
        List<String[]> collect = null;
        try {
            collect = Files.lines(Paths.get(basePath, "\\src\\main\\resources\\densitymap.ini"), StandardCharsets.UTF_8).
                    map((p -> (Arrays.stream(p.split("=")).toArray(String[]::new)))).collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Exception reading densitymap.ini: {}",e.getMessage());
        }
        for (String[] strings : collect) {
            String[] splDens = strings[0].split("-");
            try {
                map.put(new Pair<>(Double.valueOf(splDens[0]), Double.valueOf(splDens[1])), Double.valueOf(strings[1]));
            } catch (NumberFormatException e) {
                log.error("Error parsing densitymap.ini: {}",e.getMessage());
            }
        }
        return map;

    }

    private double getTempCorrectionKoff(Double densityAt20) {
        for (Pair<Double, Double> pair : INIT_DENSITY.keySet()) {
            if (pair.getKey() < densityAt20 && pair.getValue() > densityAt20) return INIT_DENSITY.get(pair);
        }
        throw new IllegalArgumentException("wrong density of petroleum products");
    }

    public double getDensity(double densityAt20, int temperature) {
        double tempCorrectionKoff = getTempCorrectionKoff(densityAt20);
        if (temperature > 20) {
            return densityAt20 - (temperature - 20) * tempCorrectionKoff;
        } else return densityAt20 + (20 - temperature) * tempCorrectionKoff;

    }


    public static void main(String[] args) {
        DensityService densityService = new DensityService();

        System.out.println(densityService.getDensity(0.8240,23));
        System.out.println(densityService.getDensity(0.7520,-12));

    }

}
