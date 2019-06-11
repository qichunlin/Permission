package com.legend.permission.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.legend.permission.dao.SysDeptMapper;
import com.legend.permission.dto.DeptLevelDto;
import com.legend.permission.model.SysDept;
import com.legend.permission.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 计算树的结构
 *
 *
 * 涉及递归算法的实现
 */
@Service
public class SysTreeService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    //部门树
    public List<DeptLevelDto> deptTree(){
        //取出最基本的数据
        List<SysDept> deptList = sysDeptMapper.getAllDept();

        //将数据封装
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept:deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    //数据核心组装
    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList){
        //判断是否为空
        if (CollectionUtils.isEmpty(deptLevelList)){
            return Lists.newArrayList();
        }

        //level-->[dept1,dept2,....]   Multimap数据机构 Map<String,List<Object>>
        Multimap<String,DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();
        for (DeptLevelDto dto: deptLevelList) {
            levelDeptMap.put(dto.getLevel(),dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }

        //按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            //自定义比较器
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        //递归生成树
        transFormDeptTree(rootList,LevelUtil.ROOT,levelDeptMap);
        return rootList;
    }

    //处理当前层级下面所有的数据
    public void transFormDeptTree(List<DeptLevelDto> deptLevelList,String level, Multimap<String,DeptLevelDto> levelDeptMap){
        //读取root里面的元素
        for (int i = 0; i < deptLevelList.size() ; i++) {
            //遍历该层元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            //处理当前层级的数据  取出下一级使用的节点
            String nextLevel = LevelUtil.calculateLevel(level,deptLevelDto.getId());
            //处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)){
                //排序
                Collections.sort(tempDeptList,deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);

                //进入下一层处理
                transFormDeptTree(tempDeptList,nextLevel,levelDeptMap);
            }
        }
    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            //根据seq的比较器
            return o1.getSeq() - o2.getSeq() ;
        }
    };
}
