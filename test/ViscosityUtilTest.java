package com.simonov;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Алексей on 10.01.2017.
 */
public class ViscosityUtilTest {
    private ViscosityUtil vu = new ViscosityUtil();
    @Test
    public void getViscoAtTemp() throws Exception {
        assertEquals(25.3104,vu.getVIlow(4),0.0001);

    }

    @Test
    public void getVindex() throws Exception {

    }

}