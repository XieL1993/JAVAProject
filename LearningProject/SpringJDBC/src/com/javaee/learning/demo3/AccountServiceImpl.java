package com.javaee.learning.demo3;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String from, String to, Double money) {
        accountDao.outMoney(from, money);
//                int a = 10 / 0;
        accountDao.inMoney(to, money);
    }
}
