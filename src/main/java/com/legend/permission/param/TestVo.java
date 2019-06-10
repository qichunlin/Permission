package com.legend.permission.param;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 测试VO类,字段的属性校验
 */
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull(message = "id不能为空")
    @Max(value = 10,message = "id 不能大于10")
    @Min(value = 1,message = "id不能小于1")
    private Integer id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
