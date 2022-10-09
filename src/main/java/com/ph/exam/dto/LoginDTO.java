package com.ph.exam.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : sunmingyao
 * @since : 2022/10/09 16:04
 */

@Data
public class LoginDTO implements Serializable {


    @NotNull(message = "工号不能为空！")
    private String userName;

    @NotNull(message = "密码不能为空！")
    private String passWord;

    private String ip;


}
