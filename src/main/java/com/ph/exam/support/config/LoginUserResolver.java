package com.ph.exam.support.config;

import com.ph.exam.entity.LoginBody;
import com.ph.exam.service.LoginService;
import com.ph.exam.support.annotation.LoginUser;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.constant.RedisKey;
import com.ph.exam.support.exception.LoginFailedException;
import com.ph.exam.support.shiro.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 *
 * 登录用户注解参数解析
 *
 * @author : sunmingyao
 * @since : 2022/10/12 13:57
 */

@Slf4j
@Component
public class LoginUserResolver  implements HandlerMethodArgumentResolver {


    @Resource
    private TokenService tokenService;

    @Resource
    private LoginService loginService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(LoginBody.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public LoginBody resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {

        String token = nativeWebRequest.getHeader(Constant.TOKEN_HEADER_NAME);
        String userName = tokenService.getJobNumber(token);

        LoginBody loginBody = loginService.getLoginBody(userName);


        if (null == loginBody) {
            throw new LoginFailedException("没有登录或者登录超时，请重新登录！");
        }

        return  loginBody;
    }
}
