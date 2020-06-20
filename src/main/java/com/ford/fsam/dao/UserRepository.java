package com.ford.fsam.dao;

import com.ford.fsam.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByMobilePhone(String mobilePhone);
    public List<UserEntity> findAllByMobilePhone(String mobilePhone);
}
