package com.open.hotel.testcases;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.open.hotel.pages.Login;
import com.techx.core.context.ExecutionBinding;
import com.techx.core.context.ExecutionContext;
import com.techx.core.context.MyContainer;
import com.techx.core.utils.PropMgr;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

    public ExecutionContext context;
    @BeforeSuite
    public void beforesuite(){
        String propfilepath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
        PropMgr.init(propfilepath);
    }
    @BeforeTest
    public void beforetest(){
        Injector injector = Guice.createInjector(new ExecutionBinding());
        this.context = injector.getInstance(ExecutionContext.class);
        MyContainer.putInstance(Thread.currentThread().getId(), this.context);
        this.context = (ExecutionContext) MyContainer.getInstance(Thread.currentThread().getId());
        this.context.initContext();
    }
    @Test
    public void Login() {
        String env = PropMgr.get("env");
        String url = PropMgr.get(env + "_env_url");
        Login login = this.context.getPageObject(Login.class);
        login.launchApp(url);
        login.login("kmanubolu","pass");
    }

}