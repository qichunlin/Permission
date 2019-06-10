package com.legend.permission.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可以将一个类转为一个json对象
 */
public class JsonMapper {
    //日志对象
    private static Logger log = LoggerFactory.getLogger(JsonMapper.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    //对象转字符串
    public static <T> String obj2String(T src){
        if (src == null){
            return null;
        }
        try{
            return src instanceof String?(String)src :objectMapper.writeValueAsString(src);
        } catch (Exception e){
            log.warn("parse object to String exception,error:{}",e);
            return null;
        }
    }

    //字符串转对象
    public static <T> T string2Obj(String src, TypeReference<T> typeReference){
        if (src == null || typeReference == null){
            return null;
        }
        try{
            return (T) (typeReference.getType().equals(String.class)?src:objectMapper.readValue(src,typeReference));
        } catch (Exception e){
            log.warn("parse object to String exception,String:{},TypeReference<T> typeReference:{},error:{}",src,typeReference.getType(),e);
            return null;
        }
    }
}
