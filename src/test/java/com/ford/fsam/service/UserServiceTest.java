package com.ford.fsam.service;


import com.ford.fsam.common.exceptions.MultipleUserException;
import com.ford.fsam.common.exceptions.UserAlreadyExistException;
import com.ford.fsam.dao.UserRepository;
import com.ford.fsam.pojo.dto.User;
import com.ford.fsam.pojo.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser(){
        User newUser = User.builder().userName("Tommy").mobilePhone("18964998287").build();
        User existUser = User.builder().userName("Happy").mobilePhone("15000635869").build();
        UserEntity existUserEntity = UserEntity.builder().userName(existUser.getUserName()).id(1L).mobilePhone(existUser.getMobilePhone()).build();
        UserEntity newUserEntity = UserEntity.builder().userName(newUser.getUserName()).mobilePhone(newUser.getMobilePhone()).id(2L).build();
        List<UserEntity> existEntities = new ArrayList<>();
        existEntities.add(existUserEntity);
        given(userRepository.findAllByMobilePhone(newUser.getMobilePhone())).willReturn(null);
        given(userRepository.findAllByMobilePhone(existUser.getMobilePhone())).willReturn(existEntities);
        given(userRepository.save(newUserEntity)).willReturn(newUserEntity);
        userService.createUser(newUser);
        Assert.assertThrows(UserAlreadyExistException.class ,()->userService.createUser(existUser));
    }

    @Test
    public void getUserByMobilePhone(){
        String nonExistMobilePhone = "18964998287";
        String uniqueMobilePhone = "15000279880";
        String nonUniqueMobilePhone = "15000635869";

        given(userRepository.findAllByMobilePhone(nonExistMobilePhone)).willReturn(null);
        given(userRepository.findAllByMobilePhone(uniqueMobilePhone)).willReturn(new ArrayList<UserEntity>(){{
            add(UserEntity.builder().mobilePhone(uniqueMobilePhone).userName("Tommy").id(1L).build());
        }});
        given(userRepository.findAllByMobilePhone(nonUniqueMobilePhone)).willReturn(
                new ArrayList<UserEntity>(){{
                    add(UserEntity.builder().mobilePhone(nonExistMobilePhone).userName("Happy").id(2L).build());
                    add(UserEntity.builder().mobilePhone(nonExistMobilePhone).userName("Quan").id(2L).build());
                }}
        );
        Assert.assertEquals(userService.getUserByMobilePhone(nonExistMobilePhone),null);
        Assert.assertEquals(userService.getUserByMobilePhone(uniqueMobilePhone),User.builder().mobilePhone(uniqueMobilePhone).userName("Tommy").id(1L).build());
        Assert.assertThrows(MultipleUserException.class,()->userService.getUserByMobilePhone(nonUniqueMobilePhone));
    }
}
