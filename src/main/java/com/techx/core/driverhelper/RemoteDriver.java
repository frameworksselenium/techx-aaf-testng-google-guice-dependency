package com.techx.core.driverhelper;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class RemoteDriver {

	private static RemoteDriver instance = new RemoteDriver();
	public static RemoteDriver getInstance() {
		return instance;
	}

	public WebDriver createChromeDriver() {
		RemoteWebDriver driver = null;
		DesiredCapabilities cap = null;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("no-sandbox");
		options.addArguments("start-maximized");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setBrowserName("chrome");
		try {
			driver = new RemoteWebDriver(new URL(""), cap);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return driver;
	}

	public WebDriver createFirefoxDriver() {
		RemoteWebDriver driver = null;
		DesiredCapabilities cap = null;
		cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.WINDOWS);
		try {
			driver = new RemoteWebDriver(new URL(""), cap);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return driver;
	}

	public WebDriver createInternetExplorerDriver() {
		RemoteWebDriver driver = null;
		DesiredCapabilities cap = null;
		cap = DesiredCapabilities.internetExplorer();
		cap.setBrowserName("iexplore");
		cap.setPlatform(Platform.WINDOWS);
		try {
			driver = new RemoteWebDriver(new URL(""), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
