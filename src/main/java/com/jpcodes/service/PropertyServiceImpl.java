package com.jpcodes.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;

public class PropertyServiceImpl implements PropertyService {

    Properties properties = new Properties();

    @Override
    public void loadProps(String fileName) throws IOException {
        // Try to load props file
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Properties file was not found.");
            }

            properties.load(inputStream);
        }
    }

    @Override
    public String getProp(String key) {
        String property = properties.getProperty(key);
        if (property != null) {
            return properties.getProperty(key);
        } else {
            throw new InvalidParameterException("No property found with key " + key);
        }
    }
}