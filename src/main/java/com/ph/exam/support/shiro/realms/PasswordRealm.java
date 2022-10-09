package com.ph.exam.support.shiro.realms;


import com.ph.exam.service.AccountService;
import com.ph.exam.support.shiro.token.CustomizedToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Optional;


@Slf4j
public class PasswordRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomizedToken;
    }

    /**
     * 获取授权信息
     *
     * @param principals principals
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 获取身份认证信息
     *
     * @param auth authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        CustomizedToken token = (CustomizedToken) auth;
        log.info(token.getUsername() + " - password auth start...");

        com.ph.exam.entity.Account account = Optional.ofNullable(accountService.findByAccount(token.getUsername())).orElseThrow(UnknownAccountException::new);
        if (account.getActiveFlag() != 1) {
            throw new AuthenticationException("账号已失效！");
        }

        // 1.principal：认证的实体信息，可以是手机号，也可以是数据表对应的用户的实体类对象
        // 2.credentials：密码
        Object credentials = account.getPassWord();
        // 3.realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = super.getName();
        // 4.盐,取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getSalt());
        return new SimpleAuthenticationInfo(account, credentials, credentialsSalt, realmName);

    }
}
