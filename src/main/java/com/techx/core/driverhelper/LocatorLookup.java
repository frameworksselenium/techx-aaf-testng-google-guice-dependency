package com.techx.core.driverhelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class LocatorLookup {

    static final Logger LOGGER = Logger.getLogger(LocatorLookup.class);

    public static By getLocator(String txtLocatorWithStrategy){
        int index = txtLocatorWithStrategy.indexOf("=");
        if(index == -1){
            LOGGER.info("Locator[], using default strategy [css]");
        }
        String type = txtLocatorWithStrategy.substring(0, index).trim();
        String locatorText = txtLocatorWithStrategy.substring(index + 1).trim();
        By by = null;
        switch(type.toUpperCase()){
            case "CSS":
                by = By.cssSelector(locatorText);
                break;
            case "XPATH":
                by = By.xpath(locatorText);
                break;
            case "LINKTEXT":
                by = By.linkText(locatorText);
                break;
            case "PARTIAL_LINKTEXT":
                by = By.partialLinkText(locatorText);
                break;
            case "ID":
                by = By.id(locatorText);
                break;
            case "CLASSNAME":
                by = By.className(locatorText);
                break;
            case "TAG_NAME":
                by = By.tagName(locatorText);
                break;
            case "NAME":
                by = By.name(locatorText);
                break;
            default:
                throw new RuntimeException("Invalid loating strtegy '" + type + "', refer doc");
        }
        return by;
    }
}
