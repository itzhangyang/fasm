package com.ford.fsam.service;

import com.ford.fsam.pojo.dto.User;

public interface UserService {
    Long createUser(User user);
    User getUserByMobilePhone(String mobilePhone);
    User getUserById(Long userId);
}
