package com.cuckoo.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tanmq on 2017/2/26.
 */
public class Config {

    protected static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String splitor = "[\\s,;，；]+";

    public Config() {
    }

    public static String get(String key) {
        return System.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }

    public static void set(String key, String value) {
        System.setProperty(key, value);
    }

    public static String[] getArray(String key) {
        String value = get(key);
        return value == null?null:value.split(splitor);
    }


    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return value == null?defaultValue:Integer.parseInt(value);
    }

    public static void reload() throws IOException {
        InputStream input = Config.class.getResourceAsStream("/config.properties");
        if(input != null) {
            System.getProperties().load(input);
            log.info("Config Loaded...");
        } else {
            log.info("No /config.properties found to load...");
        }

    }
}
