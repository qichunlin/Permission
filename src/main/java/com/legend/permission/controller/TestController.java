package com.legend.permission.controller;


import com.legend.permission.common.JsonData;
import com.legend.permission.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
//@Slf4j
public class TestController {

    Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        log.info("hello");
        return "hello perssion";
    }

    @RequestMapping(value = "/hello.json")
    @ResponseBody
    public JsonData testJson(){
        log.info("hello");
        throw new PermissionException("test exception");
        //return JsonData.success("hello permission");
    }
}
