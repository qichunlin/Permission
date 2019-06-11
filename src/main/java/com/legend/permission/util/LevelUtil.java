package com.legend.permission.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 等级工具类
 */
public class LevelUtil {

    //分隔符
    public static final String SEPARATOR = ".";
    public static final String ROOT = "0";

    //计算层级
    public static String calculateLevel(String parentLevel,Integer parentId){
        if (StringUtils.isNoneBlank(parentLevel)){
            return ROOT;
        } else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }

    }


}
