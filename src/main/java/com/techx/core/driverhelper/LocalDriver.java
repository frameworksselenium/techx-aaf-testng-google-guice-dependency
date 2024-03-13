package com.techx.core.driverhelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;

public class LocalDriver {

	private static org.apache.log4j.Logger loggers;
	private static LocalDriver instance = new LocalDriver();

	public static LocalDriver getInstance() {
		return instance;
	}

	public WebDriver createFirefoxDriver() {
		WebDriver driver = null;
		String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers";
		DesiredCapabilities cap = null;
		driver = new FirefoxDriver();
		return driver;
	}

	public WebDriver createInternetExplorerDriver() {
		WebDriver driver = null;
		String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers";
		System.setProperty("webdriver.ie.driver", driverPath + "\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		return driver;
	}

	public WebDriver createChromeDriver() {
		WebDriver driver = null;
		String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers";
		System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
}
