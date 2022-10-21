package com.ph.exam.support.utils;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author : sunmingyao
 * 校验工具
 */
public class ValidateUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validate(Object o, Class<?>... groups){
        Set<ConstraintViolation<Object>> resultSet=validator.validate(o, groups);
        if(!CollectionUtils.isEmpty(resultSet)){
            throw new IllegalArgumentException(resultSet.toString());
        }
    }


}
