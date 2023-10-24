package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.LoginBody;
import com.ph.exam.entity.Role;
import com.ph.exam.entity.RoleResource;
import com.ph.exam.entity.UserRole;
import com.ph.exam.mapper.RoleMapper;
import com.ph.exam.mapper.RoleResourceMapper;
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
 * @author sunmingyao
 * @since 2023/10/24 17:24
 */

@Service
public class RoleResourceService extends ServiceImpl<RoleResourceMapper, RoleResource> implements IService<RoleResource> {

    @Resource
    private RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    public void allocateResource(String roleId, String resourceIds) {

        Role role = Optional.ofNullable(roleMapper.selectById(roleId)).orElseThrow(() -> new ExamException("角色不存在"));

        List<String> ids = Arrays.asList(resourceIds.split(","));

        if(ids.size() > 0 && StringUtils.isNotEmpty(resourceIds)){
            List<RoleResource> roleResourceList = this.list(Wrappers.lambdaQuery(RoleResource.class).eq(RoleResource::getRoleId,roleId));

            //删除已移除的
            List<String> deleteList = roleResourceList.stream().filter(f -> !ids.contains(f.getResourceId())).map(RoleResource::getId).collect(Collectors.toList());
            if(deleteList.size() > 0) this.removeByIds(deleteList);


            //不存需要添加
            Stream<String> existList = ids.stream().filter(f -> !roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList()).contains(f));
            List<RoleResource> list = new ArrayList<>();
            existList.forEach(e ->{
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(e.trim());
                list.add(roleResource);
            });
            if(list.size() > 0) this.saveBatch(list);

        }

    }
}
