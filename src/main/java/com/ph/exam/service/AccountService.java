package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.Account;
import com.ph.exam.mapper.AccountMapper;
import com.ph.exam.support.utils.CommonsUtils;
import org.springframework.stereotype.Service;

/**
 * 账号服务类
 *
 * @author : sunmingyao
 * @since : 2022/10/9 14:01
 */

@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> implements IService<Account> {

    /**
     * 根据用户明查询账号信息
     *
     * @param userName 用户名
     * @return com.ph.exam.entity.Account
     * @author SunMingyao
     * @since 2022/10/9 14:28
     */
    public Account findByAccount(String userName) {
        return this.getOne(Wrappers.lambdaQuery(Account.class).eq(Account::getUserName, userName));
    }

    /**
     * 注册一个账号 模拟信息
     *
     * @param account 账号
     * @author SunMingyao
     * @since 2022/10/9 15:46
     */
    public void registerAccount(Account account) {
        String salt = CommonsUtils.uuid();
        String encryptPassword = CommonsUtils.encryptPassword(account.getPassWord(), salt);
        account.setPassWord(encryptPassword);
        account.setSalt(salt);
        this.save(account);
    }
}
