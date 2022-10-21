package com.ph.exam.support.annotation;


import com.ph.exam.support.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * <p> 手机号码校验注解 <br>
 *
 * @author SunMingyao
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "手机号无效";

    Class[] groups() default {};

    Class[] payload() default {};
}
