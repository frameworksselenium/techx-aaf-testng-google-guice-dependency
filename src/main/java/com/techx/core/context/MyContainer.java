package com.techx.core.context;

import java.util.Hashtable;
import java.util.Map;

public class MyContainer {

    private static Map<Long,Object> containerMap = new Hashtable<Long,Object>();

    public static void putInstance(long l, Object obj){
        containerMap.put(l,obj);
    }

    public static void deleteInstance(long l){
        containerMap.remove(l);
    }

    public static Object getInstance(long l){
        Object obj = null;
        obj = containerMap.get(l);
        return obj;
    }

}
