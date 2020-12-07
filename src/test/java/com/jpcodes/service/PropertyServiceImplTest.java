package com.jpcodes.service;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class PropertyServiceImplTest {

    private PropertyService propertyService;

    @Before
    public void setup() {
        propertyService = new PropertyServiceImpl();
    }

    @Test
    public void testGetProperties() throws IOException {
        propertyService.loadProps("test.properties");
        assertEquals("5000", propertyService.getProp("test-prop"));
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetPropertiesNoProp() throws IOException {
        propertyService.loadProps("test.properties");
        propertyService.getProp("not-real");
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetPropertiesNotFound() throws IOException {
        propertyService.loadProps("notfound.properties");
    }
}