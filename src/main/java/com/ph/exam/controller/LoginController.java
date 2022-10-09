package com.ph.exam.controller;

import com.ph.exam.dto.LoginDTO;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : sunmingyao
 * @since : 2022/10/09 14:13
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("loginByPassWord")
    public LoginBody loginByPassWord(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassWord(passWord);

        return loginService.loginByPassWord(loginDTO);
    }
}
