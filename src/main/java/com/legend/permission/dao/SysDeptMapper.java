package com.legend.permission.dao;

import com.legend.permission.model.SysDept;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    //
    int countByNameAndParentId(@Param("parentId") int parentId,@Param("name") String name,@Param("id") Integer id);

    //新增方法获取所有的部门
    List<SysDept> getAllDept();

    //获取自部门
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    //批量更新
    void batchUpdateLevel(List<SysDept> sysDeptList);
}