package com.ford.fsam.service.impl;

import com.ford.fsam.common.enums.RoleEnum;
import com.ford.fsam.common.enums.TimeInterval;
import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.common.exceptions.AlreadySignedInException;
import com.ford.fsam.common.exceptions.UserNotExistException;
import com.ford.fsam.pojo.dto.PointAccount;
import com.ford.fsam.pojo.dto.Role;
import com.ford.fsam.pojo.dto.User;
import com.ford.fsam.pojo.dto.UserEvent;
import com.ford.fsam.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SalesServiceImpl implements SalesService {
    UserService userService;
    PointAccountService pointAccountService;
    UserEventService userEventService;
    UserRoleService userRoleService;
    @Override
    public void register(String mobilePhone, String userName) {
        User user = User.builder().mobilePhone(mobilePhone).userName(userName).build();
        Long userId = userService.createUser(user);
        user.setId(userId);
        userRoleService.addUser(user, Role.of(RoleEnum.SALES));
        UserEvent userEvent = UserEvent.builder().userId(userId).time(LocalTime.now()).date(LocalDate.now()).type(UserEventType.REGISTER).build();
        userEventService.createUserEvent(userEvent);
        Long pointAccountId = pointAccountService.createPointAccount(userId);
        pointAccountService.addPoint(pointAccountId,1000);
    }

    @Override
    public void signIn(String mobilePhone) {
        User user = userService.getUserByMobilePhone(mobilePhone);
        if(user==null){
            throw  new UserNotExistException();
        }
        TimeInterval timeInterval = TimeInterval.of(LocalTime.now());
        List<UserEvent> signInEvents = userEventService.getUserEvents(user.getId(),timeInterval,LocalDate.now(),UserEventType.SIGN_IN);
        if(signInEvents!=null&&!signInEvents.isEmpty()){
            throw  new AlreadySignedInException();
        }
        UserEvent signInEvent = UserEvent.builder().date(LocalDate.now()).time(LocalTime.now()).type(UserEventType.SIGN_IN).userId(user.getId()).build();
        userEventService.createUserEvent(signInEvent);
        PointAccount pointAccount = pointAccountService.getAccountByUserId(user.getId());
        if(pointAccount!=null){
            pointAccountService.addPoint(pointAccount.getId(),10);
        }

    }
}
