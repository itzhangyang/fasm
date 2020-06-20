package com.ford.fsam.pojo.entity;

import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.pojo.dto.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
@Entity
@Table(name = "user_event")
@NoArgsConstructor
@AllArgsConstructor
public class UserEventEntity {
    @Id
    private Long id;
    private Long userId;
    private UserEventType type;
    private LocalDate date;
    private LocalTime time;

    public static  UserEventEntity fromDTO(UserEvent userEvent){
        return UserEventEntity.builder().date(userEvent.getDate()).id(userEvent.getId()).time(userEvent.getTime()).type(userEvent.getType()).userId(userEvent.getUserId()).build();
    }
}
