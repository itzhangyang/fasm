package com.ford.fsam.dao;

import com.ford.fsam.pojo.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    public List<UserRoleEntity> getAllByUserId(Long userId);
}
