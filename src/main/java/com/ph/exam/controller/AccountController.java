package com.ph.exam.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ph.exam.entity.Account;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.service.AccountService;
import com.ph.exam.support.annotation.LoginUser;
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
    public R<Account> getAccountById(@RequestParam("id") Integer id) {
        return R.ok(accountService.getById(id));
    }

    @RequestMapping("getCurrentUser")
    public R<LoginBody> getCurrentUser(@LoginUser LoginBody loginBody) {
        return R.ok(loginBody);
    }



}
