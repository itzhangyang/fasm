package com.ford.fsam.service;

import com.ford.fsam.common.enums.TimeInterval;
import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.pojo.dto.UserEvent;

import java.time.LocalDate;
import java.util.List;

public interface UserEventService {
    public List<UserEvent> getUserEvents(Long userId, TimeInterval timeInterval, LocalDate date, UserEventType type);
    public Long createUserEvent(UserEvent userEvent);
}
