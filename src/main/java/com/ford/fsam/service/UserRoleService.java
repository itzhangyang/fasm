package com.ford.fsam.service;

import com.ford.fsam.pojo.dto.Role;
import com.ford.fsam.pojo.dto.User;

import java.util.List;

public interface UserRoleService {
    public void addUser(User user, Role role);
    public List<Role> getUserRoles(User user);
}
