package com.legend.permission.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.legend.permission.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 数据校验
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    public static <T> Map<String,String> validate(T t, Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validatorResult = validator.validate(t,groups);
        if (validatorResult.isEmpty()){
            //没有错误返回的是空的map
            return Collections.emptyMap();
        } else {
            //google提供的
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validatorResult.iterator();
            while (iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }


    public static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()){
                return  Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }


    //校验一个类是否合法
    public static Map<String,String> validateObject(Object first,Object... objects){
        if (objects!=null && objects.length>0){
            return validateList(Lists.asList(first,objects));
        } else {
            return validate(first,new Class[0]);
        }
    }


    //参数校验
    public static void check(Object param) throws ParamException {
        Map<String,String> map = BeanValidator.validateObject(param);
        /*if (map!=null &&map.entrySet().size()>0){
            throw new ParamException(map.toString());
        }*/

        if (MapUtils.isNotEmpty(map)){
            throw new ParamException(map.toString());
        }
    }
}
