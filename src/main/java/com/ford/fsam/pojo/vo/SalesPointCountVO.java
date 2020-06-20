package com.ford.fsam.pojo.vo;

import com.ford.fsam.pojo.dto.PointAccount;
import com.ford.fsam.pojo.dto.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesPointCountVO {
    private Long userId;
    private String mobilePhone;
    private String userName;
    private Integer pointBalance;

    public static SalesPointCountVO from (User user, PointAccount pointAccount){
        return SalesPointCountVO.builder().mobilePhone(user.getMobilePhone()).pointBalance(pointAccount.getBalance()).userId(pointAccount.getUserId()).userName(user.getUserName()).build();
    }
}
