package com.legend.permission.common;

import com.legend.permission.exception.PermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SpringExceptionResolver implements HandlerExceptionResolver {

    //日志
    Logger log = LoggerFactory.getLogger(SpringExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        // 这里我们要求项目中所有请求json数据，都使用.json结尾
        if (url.endsWith(".json")){
            if (ex instanceof PermissionException){
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else {
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView",result.toMap());
            }
        } else if (url.endsWith(".page")){  // 这里我们要求项目中所有请求page页面，都使用.page结尾
            log.error("unknown page exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception",result.toMap());
        } else {
            log.error("unknow exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
