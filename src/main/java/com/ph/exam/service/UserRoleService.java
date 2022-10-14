package com.ph.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.UserRole;
import com.ph.exam.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author : sunmingyao
 * @since : 2022/10/14 09:22
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IService<UserRole> {
}
