package com.ford.fsam.dao;

import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.pojo.entity.UserEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface UserEventRepository extends JpaRepository<UserEventEntity, Long> {
    public List<UserEventEntity> findAllByUserIdAndDateAndTypeAndTimeBetween(Long userId, LocalDate date, UserEventType type, LocalTime startTime, LocalTime endTime);
}
