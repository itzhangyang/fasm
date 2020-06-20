package com.ford.fsam.service;

import com.ford.fsam.common.enums.TimeInterval;
import com.ford.fsam.common.enums.UserEventType;
import com.ford.fsam.common.exceptions.NoCurrentSignInTaskException;
import com.ford.fsam.dao.UserEventRepository;
import com.ford.fsam.pojo.dto.UserEvent;
import com.ford.fsam.pojo.entity.UserEventEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEventServiceTest {
    @MockBean
    UserEventRepository userEventRepository;

    @Autowired
    UserEventService userEventService;

    private Long userHaveEvents ;
    private Long userHaveNoEvent;
    private LocalDate date;
    private LocalTime time;
    @Before
    public void init(){
        userHaveEvents = 1L;
        userHaveNoEvent = 2L;
        date = LocalDate.of(2020,6,20);
        time = LocalTime.of(9,0,05);
        given(userEventRepository.findAllByUserIdAndDateAndTypeAndTimeBetween(
                userHaveEvents,date, UserEventType.SIGN_IN,
                TimeInterval.FORENOON.getStartTime(),TimeInterval.FORENOON.getEndTime()))
                .willReturn(new ArrayList<UserEventEntity>(){{
                    add(UserEventEntity.builder().id(1L).date(date).time(time).userId(userHaveEvents).type(UserEventType.SIGN_IN).build());
                }});
        given(userEventRepository.findAllByUserIdAndDateAndTypeAndTimeBetween(
                userHaveNoEvent,date, UserEventType.SIGN_IN,
                TimeInterval.FORENOON.getStartTime(),TimeInterval.FORENOON.getEndTime()))
                .willReturn(null);
    }

    @Test
    public void testGetEvents(){
        Assert.assertThrows(NoCurrentSignInTaskException.class,()->userEventService.getUserEvents(userHaveNoEvent,null,date,UserEventType.SIGN_IN));
        Assert.assertEquals(userEventService.getUserEvents(userHaveEvents,TimeInterval.FORENOON,date,UserEventType.SIGN_IN),new ArrayList<UserEvent>(){{
            add(UserEvent.builder().date(date).time(time).userId(userHaveEvents).type(UserEventType.SIGN_IN).id(1L).build());
        }});
        Assert.assertNull(userEventService.getUserEvents(userHaveNoEvent, TimeInterval.FORENOON, date, UserEventType.SIGN_IN));
    }


}
