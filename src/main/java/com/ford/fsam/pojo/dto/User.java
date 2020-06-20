package com.ford.fsam.pojo.dto;

import com.ford.fsam.pojo.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.swing.text.html.parser.Entity;

@Data
@Builder
public class User {
    private Long id;
    private String mobilePhone;
    private String userName;

    public static User fromEntity(UserEntity entity){
        return User.builder().userName(entity.getUserName()).mobilePhone(entity.getMobilePhone()).id(entity.getId()).build();
    }
}
