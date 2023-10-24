package com.ph.exam.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ph.exam.entity.Role;
import com.ph.exam.service.RoleService;
import com.ph.exam.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:58
 */

@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @GetMapping("createRole")
    public R<Void> createRole(@RequestParam("roleCode") String roleCode, @RequestParam("roleName") String roleName) {

        Role role = new Role();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        roleService.createRole(role);

        return R.ok(null);
    }


    @PostMapping("allocateRole")
    public R<Void> allocateRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {

        userRoleService.allocateRole(userId, roleIds);
        return R.ok(null);
    }
}
