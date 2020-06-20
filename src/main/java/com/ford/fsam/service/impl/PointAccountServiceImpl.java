package com.ford.fsam.service.impl;

import com.ford.fsam.common.enums.PointAccountStatus;
import com.ford.fsam.common.exceptions.MultiplePointAccountException;
import com.ford.fsam.common.exceptions.PointAccountAlreadyExistException;
import com.ford.fsam.dao.PointAccountRepository;
import com.ford.fsam.pojo.dto.PointAccount;
import com.ford.fsam.pojo.entity.PointAccountEntity;
import com.ford.fsam.service.PointAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PointAccountServiceImpl implements PointAccountService {
    PointAccountRepository pointAccountRepository;

    PointAccountService self;

    @Override
    public Long createPointAccount(Long userId) {
        List<PointAccountEntity> entities = pointAccountRepository.findAllByUserIdAndStatus(userId, PointAccountStatus.VALID);
        if(entities!=null&&!entities.isEmpty()){
            throw new PointAccountAlreadyExistException();
        }
        PointAccountEntity entity = PointAccountEntity.builder().status(PointAccountStatus.VALID).balance(0).userId(userId).build();
        PointAccountEntity savedEntity = pointAccountRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public PointAccount getAccountById(Long accountId) {
         Optional<PointAccountEntity> entityOptional = pointAccountRepository.findById(accountId);
         if(entityOptional.isPresent()){
            PointAccountEntity entity = entityOptional.get();
            return PointAccount.builder().balance(entity.getBalance()).id(entity.getId()).status(entity.getStatus()).userId(entity.getUserId()).build();
         }
         return null;
    }

    @Override
    public PointAccount getAccountByUserId(Long userId) {
        List<PointAccountEntity> entities = pointAccountRepository.findAllByUserIdAndStatus(userId,PointAccountStatus.VALID);
        if(entities!=null&&!entities.isEmpty()){
            if(entities.size()>1){
                throw  new MultiplePointAccountException();
            }
            PointAccountEntity entity = entities.get(0);
            return PointAccount.builder().userId(entity.getUserId()).id(entity.getId()).balance(entity.getBalance()).status(entity.getStatus()).build();
        }
        return null;
    }

    @Override
    public void addPoint(Long accountId, Integer amount) {
        PointAccount pointAccount = self.getAccountById(accountId);
        PointAccountEntity entity = PointAccountEntity.builder().userId(pointAccount.getUserId()).balance(pointAccount.getBalance()+amount).status(pointAccount.getStatus()).id(pointAccount.getId()).build();
        pointAccountRepository.save(entity);
    }

    @Override
    public List<PointAccount> getAllPointAccounts() {
        List<PointAccountEntity> entities= pointAccountRepository.findAllByStatus(PointAccountStatus.VALID);
        List<PointAccount> pointAccounts = null;
        if(entities!=null&&!entities.isEmpty()){
            pointAccounts = entities.stream().map(PointAccount::fromEntity).collect(Collectors.toList());
        }
        return pointAccounts;
    }
}
