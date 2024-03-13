package com.techx.core.utils;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropMgr {

    static final Logger LOGGER = Logger.getLogger(PropMgr.class);

    public static void init(String propfilepath){

        loadConfigsToSystemProp(propfilepath + "config.properties");
        String env = PropMgr.get("env");
        loadConfigsToSystemProp(propfilepath + env + "_config.properties");

    }

    public static void loadConfigsToSystemProp(String file){
        try{
            Path f = Paths.get(file);
            InputStream inputStreem = Files.newInputStream(f);
            setSystemProperties(inputStreem);
        }catch (IOException e){
            LOGGER.error("");
        }
    }

    public static void setSystemProperty(String propName, String value){
        System.setProperty(propName, value);
    }

    public static void setSystemProperties(InputStream inputStream) throws IOException {
        Properties props = new Properties();
        props.load(inputStream);
        for(Object key : props.keySet()){
            if(!System.getProperties().contains(key.toString())){
                System.setProperty(key.toString(), props.get(key).toString());
            }else{
                LOGGER.debug("System property: " + key + " overriding value from config file");
            }
        }
    }

    public static String get(String key){
        String value = System.getProperty(key);
        if(value != null){
            LOGGER.debug("Env property: " + key + " " + value);
            return value;
        }
        LOGGER.debug("Property value not found for : " + key + ", returning null");
        return null;
    }

}
