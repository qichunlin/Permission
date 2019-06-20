package com.legend.permission.controller;

import com.legend.permission.common.JsonData;
import com.legend.permission.dto.DeptLevelDto;
import com.legend.permission.param.DeptParam;
import com.legend.permission.service.SysDeptService;
import com.legend.permission.service.SysTreeService;
import com.legend.permission.util.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysTreeService sysTreeService;


    /**
     * 部门页面
     * @param param
     * @return
     */
    @RequestMapping("/dept.")
    @ResponseBody
    public JsonData dept(DeptParam param){
        BeanValidator.check(param);
        sysDeptService.save(param);
        return JsonData.success();
    }

    /**
     * 新增部门
     * @param param
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param){
        BeanValidator.check(param);
        sysDeptService.save(param);
        return JsonData.success();
    }


    /**
     * 生成树结构
     * @return
     */
    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateDept(DeptParam param){
        BeanValidator.check(param);
        sysDeptService.update(param);
        return JsonData.success();
    }

}
