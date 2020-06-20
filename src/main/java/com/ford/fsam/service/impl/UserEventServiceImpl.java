package com.ford.fsam.service.impl;

import com.ford.fsam.common.enums.TimeInterval;
import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.common.exceptions.NoCurrentSignInTaskException;
import com.ford.fsam.dao.UserEventRepository;
import com.ford.fsam.pojo.dto.UserEvent;
import com.ford.fsam.pojo.entity.UserEventEntity;
import com.ford.fsam.service.UserEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEventServiceImpl implements UserEventService {
    UserEventRepository userEventRepository;

    @Override
    public List<UserEvent> getUserEvents(Long userId, TimeInterval timeInterval, LocalDate date, UserEventType type) {
        if(timeInterval==null){
            throw  new NoCurrentSignInTaskException();
        }
        List<UserEventEntity> entities = userEventRepository.findAllByUserIdAndDateAndTypeAndTimeBetween(userId,date,type,timeInterval.getStartTime(),timeInterval.getEndTime());
        List<UserEvent> userEvents= null;
        if(entities!=null&&!entities.isEmpty()){
            userEvents = entities.stream().map(entity->UserEvent.builder().date(entity.getDate()).id(entity.getId()).time(entity.getTime()).type(entity.getType()).userId(entity.getUserId()).build()).collect(Collectors.toList());
            return userEvents;
        }
        return null;
    }

    @Override
    public Long createUserEvent(UserEvent userEvent) {
        UserEventEntity entity = UserEventEntity.fromDTO(userEvent);
        UserEventEntity savedEntity = userEventRepository.save(entity);
        return savedEntity.getId();
    }
}
