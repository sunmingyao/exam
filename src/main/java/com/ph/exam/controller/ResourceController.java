package com.ph.exam.controller;

import com.ph.exam.service.ResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private ResourceService resourceService;
}
