package com.legend.permission.util;

import com.google.common.collect.Maps;

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
    public static <T> Map<String,String> validator(T t, Class... groups){
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
        Pre
    }
}
