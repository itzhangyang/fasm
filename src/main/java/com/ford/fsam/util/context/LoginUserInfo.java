package com.ford.fsam.util.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserInfo {
    private String mobilePhone;
    private String userName;
    private String token;
}
