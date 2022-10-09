package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ph.exam.dto.LoginDTO;
import com.ph.exam.entity.Account;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.mapper.AccountMapper;
import com.ph.exam.support.config.RedisCache;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.constant.RedisKey;
import com.ph.exam.support.exception.ExamException;
import com.ph.exam.support.shiro.token.CustomizedToken;
import com.ph.exam.support.shiro.token.TokenService;
import com.ph.exam.support.utils.CommonsUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author : sunmingyao
 * @since : 2022/10/9 15:26
 */

@Service
public class LoginService {


    @Resource
    private AccountMapper accountMapper;

    @Resource
    private TokenService tokenService;

    @Resource
    private RedisCache redisCache;

    /**
     * 用户名密码登录
     *
     * @author SunMingyao
     * @since 2022/10/9 15:27
     */
    public LoginBody loginByPassWord(LoginDTO loginDTO) {


        Account account = Optional.ofNullable(accountMapper.selectOne(Wrappers.lambdaQuery(Account.class).eq(Account::getUserName, loginDTO.getUserName()))).orElseThrow(() -> new ExamException(String.format("用户[%s]不存在", loginDTO.getUserName())));
        if (account.getActiveFlag() != 1) {
            throw new ExamException("用户已失效");
        }

        //执行登录
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(loginDTO.getUserName(), loginDTO.getPassWord(), "Password");
        subject.login(token);

        String issuer = CommonsUtils.uuid();

        //返回登录实体
        return createLoginBody(account, tokenService.createToken(account.getUserName(), account.getSalt(), issuer), issuer);

    }

    /**
     * 生成登录实体
     *
     * @param account 账号
     * @param token   token
     * @param issuer  签发机构
     * @author SunMingyao
     * @since 2022/10/9 15:27
     */
    private LoginBody createLoginBody(Account account, String token, String issuer) {

        LoginBody loginBody = new LoginBody();

        //放入账号
        loginBody.setUserName(account.getUserName());
        loginBody.setNikeName(account.getNikeName());
        loginBody.setToken(token);
        loginBody.setIssuer(issuer);


        //放入缓存
        redisCache.setCacheObject(RedisKey.getLoginUserKey(account.getUserName()), loginBody, Constant.LOGIN_BODY_CACHE_EXPIRE_TIME, TimeUnit.HOURS);
        redisCache.setCacheObject(RedisKey.getLoginExpire(tokenService.getJobNumber(token)), LocalDateTime.now().toString(), Constant.LOGIN_EXPIRE_TIME, TimeUnit.MINUTES);

        return loginBody;
    }

}
