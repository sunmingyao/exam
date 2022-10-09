package com.ph.exam.support.shiro.realms;


import com.ph.exam.entity.LoginBody;
import com.ph.exam.support.config.RedisCache;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.constant.RedisKey;
import com.ph.exam.support.shiro.token.JwtToken;
import com.ph.exam.support.shiro.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


/**
 * @author : sunmingyao
 * @since : 2022/9/15 18:20
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private TokenService tokenService;

    @Resource
    private RedisCache redisCache;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //从token获取用户角色和权限信息
        String token = (String) principals.getPrimaryPrincipal();

        String loginUserKey = RedisKey.getLoginUserKey(tokenService.getJobNumber(token));
        Object cacheObject = redisCache.getCacheObject(loginUserKey);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(Collections.singletonList("base"));
        authorizationInfo.addStringPermissions(Collections.singletonList("base"));

        if (cacheObject != null) {
            LoginBody loginBody = (LoginBody) cacheObject;

            //添加角色权限
            authorizationInfo.addRoles(loginBody.getRoleSet());
            authorizationInfo.addStringPermissions(loginBody.getPermissionSet());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();


        //验证token是否过期等校验
        //不做登录校验
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token, token, "JwtRealm");


        //自定义验证token
        tokenService.verify(token, tokenService.getSalt(token), tokenService.getIssuer(token));
        Object cacheObject = redisCache.getCacheObject(RedisKey.getLoginExpire(tokenService.getJobNumber(token)));
        if (cacheObject == null) {
            throw new AuthenticationException("登录失效，请重新登录！");
        }
        //刷新登录登录失效时间
        redisCache.setCacheObject(RedisKey.getLoginExpire(tokenService.getJobNumber(token)), LocalDateTime.now().toString(), Constant.LOGIN_EXPIRE_TIME, TimeUnit.MINUTES);

        return simpleAuthenticationInfo;
    }
}
