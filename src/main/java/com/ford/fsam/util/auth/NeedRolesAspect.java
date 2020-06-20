package com.ford.fsam.util.auth;

import com.ford.fsam.common.enums.RoleEnum;
import com.ford.fsam.common.exceptions.PermissionDeniedException;
import com.ford.fsam.common.exceptions.UserInFoEmptyException;
import com.ford.fsam.common.exceptions.UserInfoFailedObtainException;
import com.ford.fsam.pojo.dto.Role;
import com.ford.fsam.pojo.dto.User;
import com.ford.fsam.service.UserRoleService;
import com.ford.fsam.service.UserService;
import com.ford.fsam.util.context.LoginUserInfo;
import com.ford.fsam.util.context.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class NeedRolesAspect {
    UserService userService;
    UserRoleService roleService;
    UserContext userContext;
    @Before(value = "@annotation(NeedRoles)")
    public void checkRoles(JoinPoint joinPoint){
        LoginUserInfo loginUserInfo = userContext.getCurrentUserInfo();
        if(loginUserInfo==null){
            throw  new UserInfoFailedObtainException();
        }
        User user = userService.getUserByMobilePhone(loginUserInfo.getMobilePhone());
        List<Role> userRoles = roleService.getUserRoles(user);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        NeedRoles annotation = method.getAnnotation(NeedRoles.class);
        List<RoleEnum> roleEnums = Stream.of(annotation.value()).collect(Collectors.toList());
        for( RoleEnum roleEnum:roleEnums){
            Role neededRole = Role.of(roleEnum);
            if(!userRoles.contains(neededRole)){
                //Todo Throw Exception
                throw new PermissionDeniedException();
            }
        }

    }
}
