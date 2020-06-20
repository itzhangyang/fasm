package com.ford.fsam.service.impl;

import com.ford.fsam.common.exceptions.MultipleUserException;
import com.ford.fsam.common.exceptions.UserAlreadyExistException;
import com.ford.fsam.dao.UserRepository;
import com.ford.fsam.pojo.dto.User;
import com.ford.fsam.pojo.entity.UserEntity;
import com.ford.fsam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public Long createUser(User user) {
        List<UserEntity> existEntities = userRepository.findAllByMobilePhone(user.getMobilePhone());
        if(existEntities!=null&&existEntities.size()>0){
            throw new UserAlreadyExistException();
        }
        UserEntity entity = UserEntity.builder().mobilePhone(user.getMobilePhone()).userName(user.getUserName()).build();
        UserEntity savedEntity = userRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public User getUserByMobilePhone(String mobilePhone) {
        List<UserEntity> entities =  userRepository.findAllByMobilePhone(mobilePhone);
        if(entities==null||entities.size()==0){
            return null;
        }
        if(entities.size()>1){
            throw new MultipleUserException();
        }
        UserEntity entity = entities.get(0);
        return User.builder().id(entity.getId()).mobilePhone(entity.getMobilePhone()).userName(entity.getUserName()).build();
    }

    @Override
    public User getUserById(Long userId) {
        Optional<UserEntity> entityOptional =  userRepository.findById(userId);
        if(entityOptional.isPresent()){
            return User.fromEntity(entityOptional.get());
        }
        return null;
    }
}
