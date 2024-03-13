package com.techx.core.driverhelper;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver getDriver(String executionType, String browser){
        WebDriver driver = null;
        switch (executionType){
            case "Local":
                switch(browser){
                    case "Chrome":
                        driver = LocalDriver.getInstance().createChromeDriver();
                        break;
                    case "Firefox":
                        driver = LocalDriver.getInstance().createFirefoxDriver();
                        break;
                    case "IE":
                        driver = LocalDriver.getInstance().createInternetExplorerDriver();
                        break;
                }
                break;
            case "Remote":
                switch(browser){
                    case "Chrome":
                        driver = RemoteDriver.getInstance().createChromeDriver();
                        break;
                    case "Firefox":
                        driver = RemoteDriver.getInstance().createFirefoxDriver();
                        break;
                    case "IE":
                        driver = RemoteDriver.getInstance().createInternetExplorerDriver();
                        break;
                }
                break;
        }
        return driver;
    }

}
