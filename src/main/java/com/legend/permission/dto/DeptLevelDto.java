package com.legend.permission.dto;

import com.google.common.collect.Lists;
import com.legend.permission.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept){
        DeptLevelDto dto = new DeptLevelDto();

        //完成两个类的复制
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }

    public List<DeptLevelDto> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptLevelDto> deptList) {
        this.deptList = deptList;
    }
}
