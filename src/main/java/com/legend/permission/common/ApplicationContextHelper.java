package com.legend.permission.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring的上下文帮助类
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz){
        if (applicationContext == null){
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name,Class<T> clazz){
        if (applicationContext == null){
            return null;
        }
        return applicationContext.getBean(name,clazz);
    }
}
