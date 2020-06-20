package com.ford.fsam.util.auth;

import com.ford.fsam.common.enums.RoleEnum;
import com.ford.fsam.pojo.dto.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedRoles {
    RoleEnum[] value() default {};


}
