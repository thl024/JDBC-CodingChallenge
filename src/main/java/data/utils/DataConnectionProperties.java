package main.java.data.utils;

import main.java.data.exceptions.DataConfigurationException;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by timothylam on 10/26/16.
 *
 * This file handles property loading. It will find the "mitchell.properties" file
 * located in resources folder. Depending on the given prefix, it will read a different
 * set of keys. To obtain a property, call get property with the property name.
 *
 * e.g. prefixKey: "mitchell_cc.dev" + propertyKey: "url" obtains the value for key
 * "mitchell_cc.dev.url" in the properties file.
 *
 * The properties file keeps sensitive data hidden and should not be included in source
 * control.
 */
public class DataConnectionProperties {

    private static final String PROPERTIES_FILENAME = "mitchellcc.properties";
    private static final Properties PROPERTIES = new Properties();

    private String prefixKey;

    /* Instantiates the class with given prefix key */
    public DataConnectionProperties(String prefixKey) throws DataConfigurationException {

        this.prefixKey = prefixKey;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILENAME);

        if (propertiesFile == null) {
            throw new DataConfigurationException (
                    "Cannot find " + PROPERTIES_FILENAME);
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException error) {
            throw new DataConfigurationException (
                    "Cannot load properties file '" + PROPERTIES_FILENAME + "'.", error);
        }
    }

    /* Instantiates the class with given property key */
    public String getProperty(String key, boolean nullable) throws DataConfigurationException {
        String propertyKey = prefixKey + "." + key;
        String propertyValue = PROPERTIES.getProperty(propertyKey);

        if (!nullable) {
            if (propertyValue == null) {
                throw new DataConfigurationException("Property for " + propertyKey + " is null.");
            } else if (propertyValue.isEmpty()) {
                throw new DataConfigurationException("Property for " + propertyKey + " is blank.");
            }
        }

        return propertyValue;
    }

}
