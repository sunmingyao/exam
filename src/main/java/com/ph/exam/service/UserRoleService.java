package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.Account;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.entity.UserRole;
import com.ph.exam.mapper.AccountMapper;
import com.ph.exam.mapper.UserRoleMapper;
import com.ph.exam.support.config.RedisCache;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.constant.RedisKey;
import com.ph.exam.support.exception.ExamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : sunmingyao
 * @since : 2022/10/14 09:22
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IService<UserRole> {


    @Resource
    private RedisCache redisCache;


    @Resource
    private AccountMapper accountMapper;

    /**
     * 用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色id(逗号分割)
     * @author SunMingyao
     * @since 2023/10/24 15:24
     */

    @Transactional(rollbackFor = Exception.class)
    public void allocateRole(String userId, String roleIds) {

        Account account = Optional.ofNullable(accountMapper.selectById(userId)).orElseThrow(() -> new ExamException("用户不存在"));

        List<String> ids = Arrays.asList(roleIds.split(","));

        if(ids.size() > 0 && StringUtils.isNotEmpty(roleIds)){
           List<UserRole> userRoleList = this.list(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId,userId));

            //删除已移除的
            List<String> deleteList = userRoleList.stream().filter(f -> !ids.contains(f.getRoleId())).map(UserRole::getId).collect(Collectors.toList());
            if(deleteList.size() > 0) this.removeByIds(deleteList);


           //不存需要添加
            Stream<String> existList = ids.stream().filter(f -> !userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList()).contains(f));
            List<UserRole> list = new ArrayList<>();
            existList.forEach(e ->{
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(e.trim());
                list.add(userRole);
            });
           if(list.size() > 0) this.saveBatch(list);

        }



        // 刷新用户角色
//        Object cacheObject = redisCache.getCacheObject(RedisKey.getLoginUserKey(account.getUserName()));
//        if (cacheObject != null) {
//
//            LoginBody loginBody = (LoginBody) cacheObject;
//            loginBody.setRoleSet(this.baseMapper.getUserRoleSet(account.getId()));
//            redisCache.setCacheObject(RedisKey.getLoginUserKey(account.getUserName()), loginBody, Constant.LOGIN_EXPIRE_TIME, TimeUnit.MINUTES);
//        }

    }


}
