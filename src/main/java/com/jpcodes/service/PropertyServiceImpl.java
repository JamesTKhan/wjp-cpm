package com.jpcodes.service;

import com.jpcodes.message.ErrorMessageEnum;

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
                throw new FileNotFoundException(ErrorMessageEnum.PROP_FILE_NOT_FOUND.getDescription());
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
            throw new InvalidParameterException(ErrorMessageEnum.PROP_NOT_FOUND.getDescription(key));
        }
    }
}