package com.ford.fsam.util.context;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext  {
    private ServletRequestAttributes requestAttributes;

    private LoginUserInfo currentUserInfo;

    private static Map<String,LoginUserInfo> sampleLoginUsers = new HashMap<>();
    static {
        sampleLoginUsers.put("token1",LoginUserInfo.builder().mobilePhone("18964998287").token("token1").userName("Tommy").build());
        sampleLoginUsers.put("token2",LoginUserInfo.builder().mobilePhone("15000635869").token("token2").userName("Happy").build());
    }

    public UserContext(){
        this.requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }
    private String getToken(){
        return WebUtils.getCookie(requestAttributes.getRequest(),"token").getValue();
    }
    public LoginUserInfo getCurrentUserInfo(){
        if(currentUserInfo==null) {
            String token = getToken();
            currentUserInfo = getLoginUserInfoFromToke(token);
        }
        return currentUserInfo;
    }
    /**
     *  Generally this would request the auth service to get the user by token
     */
    private LoginUserInfo getLoginUserInfoFromToke(String token){
        return sampleLoginUsers.get(token);
    }
}
