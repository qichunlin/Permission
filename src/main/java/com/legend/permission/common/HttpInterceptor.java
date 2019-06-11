package com.legend.permission.common;

import com.legend.permission.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Http请求前后监听
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    //日志
    Logger log = LoggerFactory.getLogger(HttpInterceptor.class);

    //开始时间
    private static final String START_TIME = "requestStartTime";

    //处理之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        log.info("request start. url:{},params:{}",url, JsonMapper.obj2String(map));

        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    //处理完之后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();

        long start = (Long)request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        //log.info("request finish. url:{},params:{}",url, JsonMapper.obj2String(map));
        log.info("request finish. url:{},cost:{}ms",url, end - start);
    }

    //处理完之后,在任何情况下都会被调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        //log.info("request exception. url:{},params:{}",url, JsonMapper.obj2String(map));

        long start = (Long)request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request finish. url:{},cost:{}ms",url, end - start);

    }
}
