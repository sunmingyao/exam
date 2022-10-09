package com.ph.exam;

import com.ph.exam.entity.Account;
import com.ph.exam.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExamApplicationTests {

    @Resource
    private AccountService accountService;

    @Test
    void contextLoads() {

        Account account = new Account();
        account.setUserName("lisi");
        account.setPassWord("123456");
        account.setNikeName("李四");
        accountService.registerAccount(account);
    }

}
