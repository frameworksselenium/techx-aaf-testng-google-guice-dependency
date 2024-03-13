package com.techx.core.driverhelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WdFace implements IDriver {

    private WebDriver driver;
    static final Logger LOGGER = Logger.getLogger(WdFace.class);

    public WdFace(WebDriver driver) {

        this.driver = driver;
    }

    public WdFace(String browser, String executionType) {
        this.driver = DriverFactory.getDriver(executionType, browser);
        try {
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            LOGGER.warn("Ignoring exception while maximizing, exception:" + e.getMessage());
        }
    }

    public WebDriver driver() {
        return driver;
    }

    @Override
    public <T> T getPageObject(Class<T> pageClassTpProxy) {
        return PageFactory.initElements(driver(), pageClassTpProxy);
    }

    @Override
    public void openUrl(String url) {
        driver().get(url);
        LOGGER.info("Opening url: '" + url + "'");
    }

    public void closeBrowser() {
        driver().close();
        LOGGER.info("Closing browser...");
    }

    public void quitSession() {
        driver().quit();
        LOGGER.info("Quiting browser...");
    }

    public String getUrl() {
        String url = driver().getCurrentUrl();
        LOGGER.info("Page url '" + url + "'");
        return url;
    }

    public String getTitle() {
        String title = driver().getTitle();
        LOGGER.info("Page url '" + title + "'");
        return title;
    }

    public void click(String locator) {
        try {
            getElement(locator).click();
            LOGGER.info("Clicking on element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed Clicking on element: '" + locator + "'", ex);
            throw ex;
        }
    }

    public void click(WebElement element) {
        try {
            element.click();
            LOGGER.info("Clicking on element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed Clicking on element: '" + element + "'", ex);
            throw ex;
        }
    }

    public void click(String locator, boolean tryJsAsFallback) {
        try {
            getElement(locator).click();
            LOGGER.info("Done Clicking on element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Skipping WebDriver Clicking on element: '" + locator + "', Retrying with JS Click", ex);
            jsClick(getElement(locator));
        }
    }

    public void click(WebElement element, boolean tryJsAsFallback) {
        try {
            element.click();
            LOGGER.info("Done Clicking on element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Skipping WebDriver Clicking on element: '" + element + "', Retrying with JS Click", ex);
            jsClick(element);
        }
    }

    public void jsClick(String locator) {
        try {
            WebElement element = getElement(locator);
            performScript("arguments[0].click()", element);
            LOGGER.info("Done JS Click on element t: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to execute Java Script on element: '" + locator + "',  error '" + ex.getMessage(), ex);
            throw ex;
        }
    }

    public void jsClick(WebElement element) {
        try {
            performScript("arguments[0].click()", element);
            LOGGER.info("Done JS Click on element t: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to execute Java Script on element: '" + element + "',  error '" + ex.getMessage(), ex);
            throw ex;
        }
    }

    public Object readDom(String script) {
        try {
            if (!script.toLowerCase().startsWith("return "))
                script = "return " + script;
            Object retValue = executeScript(script);
            LOGGER.info("Done reading value : '" + retValue + "' from DOM '" + retValue + "'");
            return retValue;
        } catch (Exception ex) {
            LOGGER.info("Failed to read from DOM, error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public Object executeScript(String script) {
        try {
            Object retValue = ((JavascriptExecutor) driver()).executeScript(script);
            LOGGER.info("Done script execution , result: '" + retValue + "' from JS '" + script + "'");
            return retValue;
        } catch (Exception ex) {
            LOGGER.info("Failed to execute JS, error '" + script + ", error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public Object performScript(String script, WebElement element) {
        try {
            Object retValue = ((JavascriptExecutor) driver()).executeScript(script, element);
            LOGGER.info("Done script execution '" + script + "' result ':" + retValue + "' on element '" + element + "'");
            return retValue;
        } catch (Exception ex) {
            LOGGER.error("Failed to executing JS '" + script + "' on element '" + element + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void hoverOnElement(WebElement element) {
        try {
            Actions action = new Actions(driver());
            action.moveToElement(element).build().perform();
            LOGGER.info("Done mouse hovering on element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to mouse hovering on element: '" + element + "', error '" + ex.getMessage() + ";", ex);
            throw ex;
        }
    }

    public void hoverOnElement(String locator) {
        try {
            Actions action = new Actions(driver());
            action.moveToElement(getElement(locator)).build().perform();
            LOGGER.info("Done mouse hovering on element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to mouse hovering on element: '" + locator + "', error '" + ex.getMessage() + ";", ex);
            throw ex;
        }
    }

    public void clear(String locator) {
        try {
            getElement(locator).clear();
            LOGGER.info("Done clearing element '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to clear' in element: '" + locator + "' error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void clear(WebElement element) {
        try {
            element.clear();
            LOGGER.info("Done clearing element '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to clear' in element: '" + element + "' error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void type(WebElement element, CharSequence text) {
        try {
            element.sendKeys(text);
            LOGGER.info("entering value: '" + text + "' in element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to enter value: '" + text + "' in element: '" + element + "'", ex);
            throw ex;
        }
    }

    public void type(String locator, CharSequence text) {
        try {
            type(getElement(locator), text);
            LOGGER.info("entering value: '" + text + "' in element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to enter value: '" + text + "' in element: '" + locator + "'", ex);
            throw ex;
        }
    }

    private WebElement getElement(String locator) {

        return driver().findElement(LocatorLookup.getLocator(locator));
    }

    public void submit(String locator) {
        try {
            getElement(locator).submit();
            LOGGER.info("Done submitting from element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed submitting on element: '" + locator + "'", ex);
            throw ex;
        }
    }

    public void submit(WebElement element) {
        try {
            element.submit();
            LOGGER.info("Done submitting from element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed submitting on element: '" + element + "'", ex);
            throw ex;
        }
    }

    public void typePassword(WebElement element, String text) {
        try {
           // element.sendKeys(EncryptDecrypt.decryptPassword(text));
            LOGGER.info("Done entering value: '******' in element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to enter value in element: '" + element + "'", ex);
            throw ex;
        }
    }

    public void typePassword(String locator, String text) {
        try {
            //getElement(locator).sendKeys(EncryptDecrypt.decryptPassword(text));
            LOGGER.info("Done entering value: '******' in element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to enter value in element: '" + locator + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void selectByValue(WebElement element, String value) {
        try {
            new Select(element).selectByValue(value);
            LOGGER.info("Done selecting option by value '" + value + "' in element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed selecting option by value '" + value + "' in element: '" + element + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void selectByValue(String locator, String value) {
        try {
            new Select(getElement(locator)).selectByValue(value);
            LOGGER.info("Done selecting option by value '" + value + "' in element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed selecting option by value '" + value + "' in element: '" + locator + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void selectByVisibleTextContains(String locator, String subText) {
        selectByVisibleTextContains(getElement(locator), subText);
    }

    public void selectByVisibleTextContains(WebElement element, String subText) {
        Select s = new Select(element);
        List<WebElement> options = s.getOptions();
        List<String> actualVisibleText = new ArrayList<>();
        for (WebElement option : options) {
            if (option.getText().contains(subText)) {
                actualVisibleText.add(option.getText());
            }
        }

        if (actualVisibleText.size() > 2) {
            LOGGER.info("Multiple matching option found '" + actualVisibleText);
            LOGGER.info("Selecting first option");
        }
        s.selectByVisibleText(actualVisibleText.get(0));
        LOGGER.info("Done Selecting option '" + actualVisibleText.get(0) + "' from list element '" + element + "'");

    }

    public void selectByVisibleText(String locator, String visibleText) {
        selectByVisibleText(getElement(locator), visibleText);
    }

    public void selectByVisibleText(WebElement element, String visibleText) {

        try {
            new Select(element).selectByVisibleText(visibleText);
            LOGGER.info("Done Selecting option '" + visibleText + "' from list element '" + element + "'");
        } catch (Exception ex) {
            LOGGER.info("Failed Selecting option '" + visibleText + "' from list element '" + element + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public void selectByIndex(String locator, int index) {
        selectByVisibleText(getElement(locator), index);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public void selectByVisibleText(WebElement element, int index) {

        try {
            new Select(element).selectByIndex(index);
            LOGGER.info("Done Selecting option by Index '" + index + "' from list element '" + element + "'");
        } catch (Exception ex) {
            LOGGER.info("Failed Selecting option by Index '" + index + "' from list element '" + element + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
    }

    public boolean isDisplayed(String locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }


    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void doubleClick(WebElement element) {
        try {
            Actions action = new Actions(driver());
            action.doubleClick(element).build().perform();
            LOGGER.info("Done double clicking on element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to double clicking on element: '" + element + "', error '" + ex.getMessage() + ";", ex);
            throw ex;
        }
    }

    public void doubleClick(WebElement element, int xOffset, int yOffset) {
        try {
            Actions action = new Actions(driver());
            action.moveToElement(element, xOffset, yOffset).doubleClick().build().perform();
            LOGGER.info("Done double clicking on element: '" + element + "' with xoffset '" + xOffset + "' and yOffset '" + yOffset + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed double clicking on element: '" + element + "' with xoffset '" + xOffset + "' and yOffset '" + yOffset + "'", ex);
            throw ex;
        }
    }

    public void doubleClick(String locator) {
        try {
            doubleClick(getElement(locator));
            LOGGER.info("Done double clicking on element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed to double clicking on element: '" + locator + "', error '" + ex.getMessage() + ";", ex);
            throw ex;
        }
    }

    public void doubleClick(String locator, int xOffset, int yOffset) {
        try {
            doubleClick(getElement(locator), xOffset, yOffset);
            LOGGER.info("Done double clicking on element: '" + locator + "' with xoffset '" + xOffset + "' and yOffset '" + yOffset + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed double clicking on element: '" + locator + "' with xoffset '" + xOffset + "' and yOffset '" + yOffset + "'", ex);
            throw ex;
        }
    }

    public void contextClick(WebElement element) {
        try {
            Actions action = new Actions(driver());
            action.contextClick(element).build().perform();
            LOGGER.info("Done context clicking on element: '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed context clicking on element: '" + element + "', error '" + ex.getMessage() + ";", ex);
            throw ex;
        }
    }

    public void contextClick(String locator) {
        try {
            contextClick(getElement(locator));
            LOGGER.info("Done context clicking on element: '" + locator + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed context clicking on element: '" + locator + "'", ex);
            throw ex;
        }
    }

    public String getText(WebElement element) {
        String text = null;
        try {
            if(element.getTagName().equalsIgnoreCase("input") && element.getAttribute("type").equalsIgnoreCase("text")){
                text = element.getAttribute("value");
            }else {
                text = element.getText();
            }
            LOGGER.info("Done reading text '" + text + "' from element '" + element + "'");
        } catch (Exception ex) {
            LOGGER.error("Failed reading text from element '" + element + "', error '" + ex.getMessage() + "'", ex);
            throw ex;
        }
        return text;
    }

    public String getText(String locatoe) {
        String text = null;
        try{
            text = getText(getElement(locatoe));
        }catch (Exception ex){
            LOGGER.error("Failed reading text from element '" + locatoe + "', error '" + ex.getMessage() + "'", ex);
        }
        return text;
    }

    public void acceptAlert(){
        LOGGER.info("Done Accepting alert Message");
        driver().switchTo().alert().accept();
    }

    public void dismissAlert(){
        LOGGER.info("Done Accepting alert Message");
        driver().switchTo().alert().dismiss();
    }

    public String getAlertText(){
        LOGGER.info("Done Reading  alert Message");
        return driver().switchTo().alert().getText();
    }

    public int getWindowsCount(){
        return driver().getWindowHandles().size();
    }

    public void switchWindows(String nameOrHandle){
        LOGGER.info("Switching windows by name or handle id '" + nameOrHandle + "'");
        driver().switchTo().window(nameOrHandle);
    }

    public void switchWindowByIndex(int index){
        LOGGER.info("Switching windows by index '" + index + "'");
        Set<String> handles = driver().getWindowHandles();
        String hanle = new ArrayList<String>(handles).get(index);
        driver().switchTo().window(hanle);
    }

    public void switchFrame(WebElement element){
        driver().switchTo().frame(element);
    }

    public void switchFrame(String idORName){
        LOGGER.info("Switching frame by name or id '" + idORName + "'");
        driver().switchTo().frame(idORName);
    }

    public void switchFrame(int index){
        LOGGER.info("Switching frame by index '" + index + "'");
        driver().switchTo().frame(index);
    }

    public void switchToDefaultContent(){
        LOGGER.info("Switching to default window");
        driver().switchTo().defaultContent();
    }

    public void switchToParentFrame(){
        LOGGER.info("Switching parent frame");
        driver().switchTo().parentFrame();
    }

    public String getAttribute(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public String getCssAttribute(WebElement element, String attributeName){
        return element.getCssValue(attributeName);
    }

    public String getAttribute(String locatorLookup, String attributeName){
        return getAttribute(getElement(locatorLookup), attributeName);
    }

    public String getCssAttribute(String locatorLookup, String attributeName){
        return getCssAttribute(getElement(locatorLookup), attributeName);
    }

    public void closeWindowByTitle(String title){
        throw new RuntimeException("");
    }

    public void ZoomOut(){
        ((JavascriptExecutor)driver()).executeAsyncScript("document.body.style.zoom = '0.5'");
    }

    public void ZoomIn(){
        ((JavascriptExecutor)driver()).executeAsyncScript("document.body.style.zoom = '1'");
    }

    public byte[] getScreenshotAsByte() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public String getScreenshotAsString() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public File getScreenshotAsFile() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void refresh() {
        LOGGER.info("Browser getting refresh");
        driver().navigate().refresh();
    }

    public WebDriverWait waitInstance(int timeInSeconds){
        return new WebDriverWait(driver(), timeInSeconds);
    }

    public void clearCookies(){
        driver().manage().deleteAllCookies();
    }

    public void deleteCookies(String name){
        driver().manage().deleteCookieNamed(name);
    }

    public void setCookies(Cookie cookie){
        driver().manage().addCookie(cookie);
    }

    @Override
    public Cookie getCookie(String name){
        return driver().manage().getCookieNamed(name);
    }

    public Set<Cookie> getCookie(){
        return driver().manage().getCookies();
    }

    @Override
    public Actions getActions(){
        return new Actions(this.driver);
    }

}
