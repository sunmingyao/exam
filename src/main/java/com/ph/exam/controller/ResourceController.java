package com.ph.exam.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ph.exam.service.RoleResourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:57
 */

@RestController
@RequestMapping("resource")
public class ResourceController {

    @Resource
    private RoleResourceService roleResourceService;

    @PostMapping("allocateResource")
    public R<Void> allocateRole(@RequestParam("roleId") String roleId, @RequestParam("resourceIds") String resourceIds) {
        roleResourceService.allocateResource(roleId, resourceIds);
        return R.ok(null);
    }
}
