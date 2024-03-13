package com.techx.core.driverhelper;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.Set;

public interface IDriver {

    public <T> T getPageObject(Class<T> pageClassToProxy);
    public void openUrl(String url);
    public void closeBrowser();
    public void quitSession();
    public String getUrl();
    public String getTitle();
    public void click(String locator);
    public void click(WebElement element);
    public void click(String locator, boolean tryJsAsFallback);
    public void click(WebElement element,boolean tryJsAsFallback);
    public void jsClick(String locator);
    public void jsClick(WebElement element);
    public Object readDom(String script);
    Object executeScript(String script);
    Object performScript(String script, WebElement element);
    public void hoverOnElement(String locator);
    public void hoverOnElement(WebElement element);
    public void clear(String locator);
    public void clear(WebElement element);
    public void type(WebElement element, CharSequence text);
    public void type(String locator, CharSequence text);
    public void submit(String locator);
    public void submit(WebElement element);
    public void typePassword(WebElement element, String text);
    public void typePassword(String locator, String text);
    public void selectByValue(WebElement element, String value);
    public void selectByValue(String locator, String value);
    public void selectByVisibleTextContains(WebElement element, String subText);
    public void selectByVisibleTextContains(String locator, String subText);
    public void selectByVisibleText(WebElement element, String visibleText);
    public void selectByVisibleText(String locator, String visibleText);
    public void selectByIndex(WebElement element, int index);
    public void selectByIndex(String locator, int index);
    public boolean isDisplayed(WebElement element);
    public boolean isDisplayed(String locator);
    public void doubleClick(WebElement element);
    public void doubleClick(WebElement element, int xOffset, int yOffset);
    public void doubleClick(String locator);
    public void doubleClick(String locator, int xOffset, int yOffset);
    public void contextClick(WebElement element);
    public void contextClick(String locator);
    public String getText(WebElement element);
    public String getText(String locator);
    public void acceptAlert();
    public void dismissAlert();
    public String getAlertText();
    public int getWindowsCount();
    public void switchWindows(String nameOrHandle);
    public void switchWindowByIndex(int index);
    public void switchFrame(WebElement element);
    public void switchFrame(String idOrName);
    public void switchFrame(int index);
    public void switchToDefaultContent();
    public void switchToParentFrame();
    public String getAttribute(WebElement element, String attributeName);
    public String getCssAttribute(WebElement element, String attributeName);
    public String getAttribute(String locatorLookup, String attributeName);
    public String getCssAttribute(String locatorLookup, String attributeName);
    public void closeWindowByTitle(String title);
    public void ZoomOut();
    public void ZoomIn();
    public byte[] getScreenshotAsByte();
    public String getScreenshotAsString();
    public File getScreenshotAsFile();
    public void refresh();
    public WebDriverWait waitInstance(int timeInSeconds);
    public void clearCookies();
    public void deleteCookies(String name);
    public void setCookies(Cookie c);
    public Cookie getCookie(String name);
    public Set<Cookie> getCookie();
    public Actions getActions();
}
