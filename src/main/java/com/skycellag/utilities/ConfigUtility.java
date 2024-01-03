package com.skycellag.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : user
 * @created : 2.01.2024,16:25
 * @Email :aliyeidiris@gmail.com
 **/
public class ConfigUtility {
    public static String readConfig(String key){
        final String configPath="config.properties";
        Properties properties =new Properties();
        try {
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
}
