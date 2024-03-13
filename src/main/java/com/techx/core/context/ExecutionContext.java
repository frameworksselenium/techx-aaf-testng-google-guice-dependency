package com.techx.core.context;

import com.techx.core.driverhelper.IDriver;
import com.techx.core.driverhelper.WdFace;
import com.techx.core.utils.PropMgr;
import com.techx.core.utils.WaitUtils;
import org.apache.log4j.Logger;
import java.util.*;

public class ExecutionContext  implements  IExecutionContext{

    static final Logger LOGGER = Logger.getLogger(ExecutionContext.class);
    private Map<Object, Object> vars = new HashMap<>();

    public ExecutionContext(){

    }
    public IDriver driver(){
        IDriver driver = (IDriver) vars.get("DRIVER");
        if(driver == null){
            LOGGER.debug("Driver has not initialized");
        }
        return driver;
    }

    public void initContext() {
        IDriver driver = (IDriver) vars.get("DRIVER");
        if(driver == null) {
            String browser = PropMgr.get("browser");
            String executionType = PropMgr.get("execution_type");
            driver = new WdFace(browser, executionType);
            vars.put("DRIVER", driver);
        }
        vars.put("WAIT_UTILS", new WaitUtils(driver));

    }

    public void setVar(Object name, Object value){
        this.vars.put(name, value);}

    public void removeVar(Object var){vars.remove(var);}

    public Map vars(){
        return vars;
    }

    public Object var(Object name){

        if(name.toString().contains("#$")){
            return name.toString();
        }else{
            Object value = vars.get(name);
            if(value instanceof String ){
                return value.toString();
            }else{
                return vars.get(name);
            }
        }
    }

    public WaitUtils waitUtils(){
        return (WaitUtils) vars.get("WAIT_UTILS");
    }

    public <T extends BaseWdPage> T getPageObject(Class<? extends BaseWdPage> pageClassToProxy){
        return BaseWdPage.getPageObject(this, pageClassToProxy);
    }


    public void cleanUp(){
        try{
            driver().quitSession();
        }catch (Exception e){

        }
        vars.remove("WAIT_UTILS");
        vars.remove("DRIVER");
    }

}
