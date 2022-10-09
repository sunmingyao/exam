package com.ph.exam.controller;

import com.ph.exam.entity.Account;
import com.ph.exam.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : sunmingyao
 * @since : 2022/10/09 14:13
 */

@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("getAccountById")
    public Account getAccountById(@RequestParam("id") Integer id) {
        return accountService.getById(id);
    }


}
