package com.ford.fsam.dao;

import com.ford.fsam.common.enums.PointAccountStatus;
import com.ford.fsam.pojo.entity.PointAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointAccountRepository extends JpaRepository<PointAccountEntity, Long> {
    public List<PointAccountEntity> findAllByUserId(Long userId);
    public List<PointAccountEntity> findAllByUserIdAndStatus(Long userId, PointAccountStatus status);
    public List<PointAccountEntity> findAllByStatus(PointAccountStatus status);
}
