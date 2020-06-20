package com.ford.fsam.pojo.dto;

import com.ford.fsam.common.enums.UserEventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class UserEvent {
    private Long id;
    private Long userId;
    private UserEventType type;
    private LocalDate date;
    private LocalTime time;
}
