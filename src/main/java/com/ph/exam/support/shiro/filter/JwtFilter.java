package com.ph.exam.support.shiro.filter;

import com.alibaba.fastjson.JSONObject;

import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.shiro.token.JwtToken;
import com.ph.exam.support.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author : sunmingyao
 * @since : 2022/9/15 17:55
 * <p>JWT过滤器<br>
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     *
     * @param request     ServletRequest
     * @param response    ServletResponse
     * @param mappedValue mappedValue
     * @return 是否成功
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return executeLogin(request, response);
    }

    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constant.TOKEN_HEADER_NAME);

        if (StringUtils.isEmpty(token)) {
            throw new IncorrectCredentialsException("无登录信息！");
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
        } catch (IncorrectCredentialsException e) {
            JSONObject o = new JSONObject();
            o.put("msg", e.getMessage());
            ServletUtils.renderString((HttpServletResponse) response, o.toString());
            return false;
        }

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        /*HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", httpServletRequest.getMethod());
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }*/
        return super.preHandle(request, response);
    }
}
