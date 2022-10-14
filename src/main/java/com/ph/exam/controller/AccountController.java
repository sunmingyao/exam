package com.ph.exam.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ph.exam.entity.Account;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.entity.Role;
import com.ph.exam.service.AccountService;
import com.ph.exam.service.RoleService;
import com.ph.exam.support.annotation.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    private RoleService roleService;

    @RequestMapping("getAccountById")
    public Account getAccountById(@RequestParam("id") Integer id) {
        return accountService.getById(id);
    }

    @RequestMapping("getCurrentUser")
    public LoginBody getCurrentUser(@LoginUser LoginBody loginBody) {
        return loginBody;
    }

    @GetMapping("createRole")
    public R<Void> createRole(@RequestParam("roleCode") String roleCode, @RequestParam("roleName") String roleName){

        Role role = new Role();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        roleService.createRole(role);

        return R.ok(null);
    }

}
