package com.ford.fsam.common.enums;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public enum TimeInterval {
    FORENOON(1,"forenoon",LocalTime.of(8,0,0),LocalTime.of(11,59,59)),
    AFTERNOON(2, "afternoon",LocalTime.of(12,0,0),LocalTime.of(18,0,0));
    private int code;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    TimeInterval(int code, String name, LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeInterval of(LocalTime time){
        if(time.isAfter(FORENOON.startTime) && time.isBefore(FORENOON.endTime)){
            return FORENOON;
        }
        if(time.isAfter(AFTERNOON.startTime)&& time.isBefore(AFTERNOON.endTime)){
            return AFTERNOON;
        }
        return null;
    }
}
