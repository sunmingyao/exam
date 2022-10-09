package com.ph.exam.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/9 13:46
 */

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
