package com.ph.exam.support.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ph.exam.support.shiro.token.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author : sunmingyao
 */

@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    @Resource
    private TokenService tokenService;


    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            try {
                String userName = tokenService.getJobNumber(SecurityUtils.getSubject().getPrincipal().toString());
                setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                if (StringUtils.isNotEmpty(userName)) setFieldValByName("createBy", userName, metaObject);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        try {
            String userName = tokenService.getJobNumber(SecurityUtils.getSubject().getPrincipal().toString());
            if (StringUtils.isNotEmpty(userName)) setFieldValByName("updateBy", userName, metaObject);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
