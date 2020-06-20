package com.ford.fsam.service.impl;
import com.ford.fsam.common.exceptions.UserAlreadyHasRoeException;
import com.ford.fsam.dao.UserRoleRepository;
import com.ford.fsam.pojo.dto.Role;
import com.ford.fsam.pojo.dto.User;
import com.ford.fsam.pojo.entity.UserRoleEntity;
import com.ford.fsam.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    UserRoleRepository userRoleRepository;
    @Override
    public void addUser(User user, Role role) {
        List<Role> roles = getUserRoles(user);
        if(roles!=null&&!roles.isEmpty()){
            for(Role roleInList: roles){
                if(roleInList.equals(role)){
                    throw  new UserAlreadyHasRoeException();
                }
            }
        }
        UserRoleEntity entity = UserRoleEntity.builder().roleId(role.getId()).roleName(role.getName()).userId(user.getId()).build();
        userRoleRepository.save(entity);
    }

    @Override
    public List<Role> getUserRoles(User user) {
        List<UserRoleEntity> entities = userRoleRepository.getAllByUserId(user.getId());
        List<Role> result =  null;
        if(entities!=null&&!entities.isEmpty()){
            result = entities.stream().map(entity ->Role.builder().id(entity.getRoleId()).name(entity.getRoleName()).build()).collect(Collectors.toList());
        }
        return result;
    }
}
