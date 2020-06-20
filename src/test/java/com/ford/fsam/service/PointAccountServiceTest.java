package com.ford.fsam.service;

import com.ford.fsam.common.enums.PointAccountStatus;
import com.ford.fsam.common.exceptions.PointAccountAlreadyExistException;
import com.ford.fsam.dao.PointAccountRepository;
import com.ford.fsam.pojo.entity.PointAccountEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointAccountServiceTest {
	@MockBean
	PointAccountRepository pointAccountRepository;

	@Autowired
	PointAccountService pointAccountService;

	public Long userIdWithPointAccount;

	public Long userIdWithoutPointAccount;

	@Before
	public void init() {
		userIdWithoutPointAccount = 2L;
		userIdWithPointAccount = 1L;
		given(pointAccountRepository.findAllByUserIdAndStatus(userIdWithPointAccount, PointAccountStatus.VALID))
				.willReturn(new ArrayList<PointAccountEntity>() {
					{
						add(PointAccountEntity.builder().id(1L).balance(200).userId(userIdWithPointAccount).build());
					}
				});
		given(pointAccountRepository.findAllByUserIdAndStatus(userIdWithoutPointAccount, PointAccountStatus.VALID))
				.willReturn(null);
		given(pointAccountRepository.save(PointAccountEntity.builder().userId(userIdWithoutPointAccount)
				.status(PointAccountStatus.VALID).build()))
						.willReturn(PointAccountEntity.builder().balance(0).id(2L).userId(userIdWithoutPointAccount)
								.status(PointAccountStatus.VALID).build());
	}

	@Test
	public void testCreatePointAccount() {
		Assert.assertThrows(PointAccountAlreadyExistException.class,
				() -> pointAccountService.createPointAccount(userIdWithPointAccount));
		pointAccountService.createPointAccount(userIdWithoutPointAccount);
	}
}
