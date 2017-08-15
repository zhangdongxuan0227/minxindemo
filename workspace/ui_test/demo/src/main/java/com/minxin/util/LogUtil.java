package com.minxin.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Created by Administrator on 2017/7/19.
 */
public class LogUtil {
    private Class<?> clazz;
    private Logger log;
    public LogUtil(Class<?> clazz){
        this.clazz = clazz;
        this.log = LogManager.getLogger(this.clazz);
    }
    public void info(String message){
        log.info(clazz.getCanonicalName() +":"+message);
    }
    public void debug(String message){
        log.debug(clazz.getCanonicalName()+":"+message);
    }
    public void error(String message){
        log.error(clazz.getCanonicalName() + ":"+ message);
    }
    public void trace(String message){
        log.trace(clazz.getCanonicalName() + ":" + message);
    }
    public void warn(String message){
        log.warn(clazz.getCanonicalName() +":"+ message);
    }
    public void fatal(String message){
        log.fatal(clazz.getCanonicalName() +":"+ message);
    }

}
