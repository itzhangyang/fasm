package com.ford.fsam.controller;

import com.ford.fsam.common.enums.RoleEnum;
import com.ford.fsam.common.exceptions.UserInfoFailedObtainException;
import com.ford.fsam.pojo.dto.PointAccount;
import com.ford.fsam.pojo.vo.SalesPointCountVO;
import com.ford.fsam.service.*;
import com.ford.fsam.util.auth.NeedRoles;
import com.ford.fsam.util.context.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sales")
@AllArgsConstructor
public class SalesController {
    UserService userService;
    PointAccountService pointAccountService;
    SalesService salesService;
    UserContext userContext;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String mobilePhone, @RequestParam String userName){
        if(userContext.getCurrentUserInfo()==null){
            throw new UserInfoFailedObtainException();
        }
        if(!userContext.getCurrentUserInfo().getMobilePhone().equals(mobilePhone)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't now register using other's mobile phone");
        }
        salesService.register(mobilePhone,userName);
        return ResponseEntity.ok("Successfully registered...");
    }

    @NeedRoles(value = {RoleEnum.SALES})
    @PostMapping("/{mobilePhone}/sign-in")
    public ResponseEntity<String> signIn(@PathVariable String mobilePhone){
        if(!userContext.getCurrentUserInfo().getMobilePhone().equals(mobilePhone)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't now register using other's mobile phone");
        }
        salesService.signIn(mobilePhone);
        return ResponseEntity.ok("Success");
    }
    @GetMapping("/rank")
    @NeedRoles(value = {RoleEnum.OPERATOR})
    public ResponseEntity<List<SalesPointCountVO>> getSalesRank(){
        List<PointAccount> pointAccounts = pointAccountService.getAllPointAccounts();
        List<SalesPointCountVO> result =null;
        if(pointAccounts!=null&&!pointAccounts.isEmpty()){
            result = pointAccounts.stream().map(pointAccount -> SalesPointCountVO.from(userService.getUserById(pointAccount.getUserId()),pointAccount)).collect(Collectors.toList());
        }
        result.sort(new Comparator<SalesPointCountVO>() {
            @Override
            public int compare(SalesPointCountVO o1, SalesPointCountVO o2) {
                if(o1.getPointBalance()>o2.getPointBalance()){
                    return  -1;
                }else if(o2.getPointBalance()<o2.getPointBalance()){
                    return 1;
                }
                return 0;
            }
        });
        return ResponseEntity.ok(result);
    }
}
