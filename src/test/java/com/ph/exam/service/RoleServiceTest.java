package com.ph.exam.service;

import com.ph.exam.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : sunmingyao
 * @since : 2022/10/14 09:47
 */
@SpringBootTest
class RoleServiceTest {

    @Resource
    private RoleService roleService;

    @Test
    void testCreateRole() {

        Role role = new Role();
        role.setRoleCode("ADMIN");
        role.setRoleName("管理员");

        roleService.createRole(role);
    }
}
