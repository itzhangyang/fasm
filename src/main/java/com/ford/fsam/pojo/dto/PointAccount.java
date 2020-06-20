package com.ford.fsam.pojo.dto;

import com.ford.fsam.common.enums.PointAccountStatus;
import com.ford.fsam.pojo.entity.PointAccountEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointAccount {
    private Long id;
    private Long userId;
    private Integer balance;
    private PointAccountStatus status;

    public static  PointAccount fromEntity(PointAccountEntity entity){
        return PointAccount.builder().status(entity.getStatus()).balance(entity.getBalance()).userId(entity.getUserId()).id(entity.getId()).build();
    }
}
