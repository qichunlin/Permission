package com.legend.permission.controller;


import com.legend.permission.common.JsonData;
import com.legend.permission.exception.ParamException;
import com.legend.permission.exception.PermissionException;
import com.legend.permission.param.TestVo;
import com.legend.permission.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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


    @RequestMapping(value = "/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo){
        log.info("validate");
        try {
            Map<String,String> map = BeanValidator.validateObject(vo);
            if (map != null && map.entrySet().size()>0){
                for (Map.Entry<String,String> entry:map.entrySet()) {
                    log.info("{}->{}",entry.getKey(),entry.getValue());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return JsonData.success("test validate");
    }


    @RequestMapping(value = "/validate2.json")
    @ResponseBody
    public JsonData validate2(TestVo vo) throws ParamException {
        log.info("validate2");
        BeanValidator.check(vo);
        return JsonData.success("test validate2");
    }
}
