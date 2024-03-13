package com.techx.core.context;

import com.techx.core.driverhelper.IDriver;
import com.techx.core.driverhelper.WdFace;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseWdPage {

    protected  ExecutionContext context;
    private WebDriver driver;
    static final Logger LOGGER = Logger.getLogger(BaseWdPage.class);

    protected IDriver driver(){
        return this.context.driver();
    }
    public BaseWdPage(WebDriver driver){
        this.driver = driver;
    }

    abstract public void waitForLoad();
    abstract public void pageIsValid();

    public String getTitle(){
        return driver.getTitle();
    }
    public void refresh() {
        context.driver().refresh();
    }

    public static <T extends BaseWdPage> T getPageObject(ExecutionContext context, Class<? extends BaseWdPage> pageClassToProxy){
        WebDriver d = ((WdFace)context.driver()).driver();
        T object = (T) PageFactory.initElements(d, pageClassToProxy);
        object.context = context;
        object.waitForLoad();
        object.pageIsValid();
        return object;
    }

    public void reloadPageObject(){
        WebDriver d = ((WdFace)context.driver()).driver();
        PageFactory.initElements(d, this);
    }

    public void wait(int seconds){
        this.context.waitUtils().wait(seconds);
    }

}
