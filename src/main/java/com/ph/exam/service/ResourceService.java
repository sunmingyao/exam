package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.*;
import com.ph.exam.mapper.ResourceMapper;
import com.ph.exam.mapper.RoleMapper;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.constant.RedisKey;
import com.ph.exam.support.exception.ExamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:53
 */

@Service
public class ResourceService extends ServiceImpl<ResourceMapper, Resource> implements IService<Resource> {



}
