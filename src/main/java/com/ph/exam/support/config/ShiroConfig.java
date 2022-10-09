package com.ph.exam.support.config;


import com.ph.exam.support.shiro.filter.JwtFilter;
import com.ph.exam.support.shiro.realms.JwtRealm;
import com.ph.exam.support.shiro.realms.PasswordRealm;
import com.ph.exam.support.shiro.realms.UserModularRealmAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : sunmingyao
 * @since : 2022/10/09 16:11
 */
@Configuration
public class ShiroConfig {

    /**
     * 开启shiro权限注解
     *
     * @return DefaultAdvisorAutoProxyCreator
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator creator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager 安全管理器
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * jwtRealm
     *
     * @return JwtRealm
     */
    @Bean
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }


    @Bean
    public UserModularRealmAuthenticator userModularRealmAuthenticator() {
        // 自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/login/**", "anon");


        Map<String, Filter> filter = new LinkedHashMap<>(1);
        filter.put("jwt", new JwtFilter());
        bean.setFilters(filter);
        filterMap.put("/**", "jwt");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
     */
    @Bean
    public SecurityManager securityManager(@Qualifier("jwtRealm") JwtRealm jwtRealm, @Qualifier("passwordRealm") PasswordRealm passwordRealm, @Qualifier("userModularRealmAuthenticator") UserModularRealmAuthenticator userModularRealmAuthenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setAuthenticator(userModularRealmAuthenticator);
        List<Realm> realms = new ArrayList<>();
        // 添加多个realm
        realms.add(passwordRealm);
        realms.add(jwtRealm);
        securityManager.setRealms(realms);

        /*
         * 关闭shiro自带的session，详情见文档
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置哈希算法名称
        matcher.setHashAlgorithmName("SHA-256");
        // 设置哈希迭代次数
        matcher.setHashIterations(1024);
        // 设置存储凭证十六进制编码
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    /**
     * 密码登录Realm
     *
     * @param matcher 密码匹配器
     * @return PasswordRealm
     */
    @Bean
    public PasswordRealm passwordRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        PasswordRealm userRealm = new PasswordRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

}
