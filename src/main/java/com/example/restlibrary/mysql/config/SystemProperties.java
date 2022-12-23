package com.example.restlibrary.mysql.config;

import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

    private static final Properties SYSTEM_PROPERTIES = new Properties();

    static {

        ClassLoader loader = SystemProperties.class.getClassLoader();
        try {
            SYSTEM_PROPERTIES.load(loader.getResourceAsStream("system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String value = null;
        if (SYSTEM_PROPERTIES.containsKey(key)) {
             value = SYSTEM_PROPERTIES.getProperty(key);
        }
        return value;
    }
}
