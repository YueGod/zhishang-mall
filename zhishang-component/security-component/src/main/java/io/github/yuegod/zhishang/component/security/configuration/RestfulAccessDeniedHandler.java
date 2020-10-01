package io.github.yuegod.zhishang.component.security.configuration;


import io.github.yuegod.zhishang.basecode.web.model.Result;
import io.github.yuegod.zhishang.basecode.web.util.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 屈子威
 * @date 2020/8/30 12:25
 * @description
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 访问没有授权时如何处理
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(ResultUtils.result(Result.Code.FAIL.getValue(),"当前访问无权限！",null ));
        response.getWriter().flush();
    }
}

/**
 * @author 屈子威
 */
@Component
class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Token过期处理
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        //这里的返回信息是一个字符串，也就是说可以是吧一个对象序列化再放回
        response.getWriter().println(ResultUtils.result(Result.Code.FAIL.getValue(), "您当前的认证信息无效！",null));
        response.getWriter().flush();
    }
}