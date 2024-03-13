package com.techx.core.utils;

import com.techx.core.context.ExecutionContext;
import com.techx.core.driverhelper.IDriver;
import com.techx.core.driverhelper.LocatorLookup;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;


public class WaitUtils {
    private IDriver driver;
    static final Logger LOGGER = Logger.getLogger(ExecutionContext.class);
    public WaitUtils(IDriver driver){
        this.driver = driver;
    }

    public static void wait(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch (Exception e){
            LOGGER.error("Exception while calling thread.sleep()", e);
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element, int timeoutInSce){
        return driver.waitInstance(timeoutInSce).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeVisible(String locatorStrategy, int timeoutInSce){
        return waitForElementToBeVisible(LocatorLookup.getLocator(locatorStrategy));
    }

    public WebElement waitForElementToBeVisible(By locator, int timeoutInSce){
        return driver.waitInstance(timeoutInSce).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



    public WebElement waitForElementPresence(String locatorStrategy, int timeoutInSce){
        return waitForElementPresence(LocatorLookup.getLocator(locatorStrategy));
    }

    public WebElement waitForElementPresence(By locator, int timeoutInSce){
        return driver.waitInstance(timeoutInSce).until(ExpectedConditions.presenceOfElementLocated(locator));
    }




    public WebElement waitForElementToBeClickable(WebElement element, int timeoutInSce){
        return driver.waitInstance(timeoutInSce).until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitForElementToBeClickable(String locatorStrategy, int timeoutInSce){
        return waitForElementToBeClickable(LocatorLookup.getLocator(locatorStrategy));
    }
    public WebElement waitForElementToBeClickable(By locator, int timeoutInSce){
        return driver.waitInstance(timeoutInSce).until(ExpectedConditions.elementToBeClickable(locator));
    }


    //default time out
    public WebElement waitForElementToBeVisible(WebElement element){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeVisible(String locatorStrategy){
        return waitForElementToBeVisible(LocatorLookup.getLocator(locatorStrategy));
    }

    public WebElement waitForElementToBeVisible(By locator){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(String locatorStrategy){
        return waitForElementPresence(LocatorLookup.getLocator(locatorStrategy));
    }

    public WebElement waitForElementPresence(By locator){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public WebElement waitForElementToBeClickable(WebElement element){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeClickable(String locatorStrategy){
        return waitForElementToBeClickable(LocatorLookup.getLocator(locatorStrategy));
    }

    public WebElement waitForElementToBeClickable(By locator){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.elementToBeClickable(locator));
    }



    //wait for text box to contain given value
    public boolean waitForElementTextValueToContainText(WebElement element, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElementValue(element, expectedText));
    }

    public boolean waitForElementValueToContainText(String locatorStrategy, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElementValue(LocatorLookup.getLocator(locatorStrategy), expectedText));
    }

    public boolean waitForElementValueToContainText(By locator, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElementValue(locator, expectedText));
    }

    //wait for element to contain given value
    public boolean waitForElementTextToContainText(WebElement element, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public boolean waitForElementTextToContainText(String locatorStrategy, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElementLocated(LocatorLookup.getLocator(locatorStrategy), expectedText));
    }

    public boolean waitForElementTextToContainText(By locator, String expectedText){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }





    //wait for element to contain given value
    public boolean waitForElementTextMatches(WebElement element, String regex){
        return driver.waitInstance(getExplicitTimeOut()).until(textMatches(element, Pattern.compile(regex)));
    }

    public boolean waitForElementTextMatches(String locatorStrategy, String regex){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textMatches(LocatorLookup.getLocator(locatorStrategy), Pattern.compile(regex)));
    }

    public boolean waitForElementTextMatches(By locator, String regex){
        return driver.waitInstance(getExplicitTimeOut()).until(ExpectedConditions.textMatches(locator, Pattern.compile(regex)));
    }

    private int getExplicitTimeOut(){
        Object value = PropMgr.get("DEFAULT_EXPLICIT_WAIT_TIMEOUT");
        if(value == null){
            throw new RuntimeException("Property file '" + "DEFAULT_EXPLICIT_WAIT_TIMEOUT" + "' did not set in ");
        }
        return Integer.parseInt("DEFAULT_EXPLICIT_WAIT_TIMEOUT");
    }

    private static ExpectedCondition<Boolean> textMatches(final WebElement element, final Pattern pattern){
        return new ExpectedCondition<Boolean>(){
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver){
                try{
                    currentValue = element.getTagName();
                    return pattern.matcher(currentValue).find();
                }catch (Exception ex){
                    return false;
                }
            }


            @Override
            public String toString(){
                return String.format("text found by %s to match pattern \"%s\". Current text: \"%s\"", element, pattern.pattern(), currentValue);
            }
        };
    }
}
