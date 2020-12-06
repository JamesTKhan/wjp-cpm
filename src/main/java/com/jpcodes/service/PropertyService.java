package com.jpcodes.service;

import java.io.IOException;

public interface PropertyService {

    /**
     * Load props from given props file
     */
    void loadProps(String fileName) throws IOException;

    /**
     * Returns property value of given key
     *
     * @param key the key to retrieve the value of
     * @return value of given key
     */
    String getProp(String key);

}