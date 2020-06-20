package com.ford.fsam.service;

import com.ford.fsam.pojo.dto.PointAccount;

import java.util.List;

public interface PointAccountService {
    public Long createPointAccount(Long userId);
    public PointAccount getAccountById(Long accountId);
    public PointAccount getAccountByUserId(Long userId);
    public void addPoint(Long accountId,Integer amount);
    public List<PointAccount> getAllPointAccounts();
}
