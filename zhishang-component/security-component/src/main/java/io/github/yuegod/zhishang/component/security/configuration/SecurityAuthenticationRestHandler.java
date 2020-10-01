package io.github.yuegod.zhishang.component.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yuegod.api.security.domain.model.Login;
import io.github.yuegod.api.security.service.IUserService;
import io.github.yuegod.zhishang.basecode.web.model.Result;
import io.github.yuegod.zhishang.basecode.web.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * security登录处理handle
 */
@Component
public class SecurityAuthenticationRestHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationEntryPoint, AuthenticationFailureHandler, LogoutSuccessHandler {

    @Autowired
    private IUserService userService;

    private void header(HttpServletResponse response, Result data) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(data);
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
    }


    /**
     * 未登录
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        header(response, ResultUtils.result(Result.Code.NO_LOGIN.getValue(), "请登录"));
    }

    /**
     * 登录授权成功
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        // 调用service发布登录事件，模块通过监听事件处理业务
        Login login = new Login();
        login.setUsername(((UserInfo) authentication.getPrincipal()).getUsername());
        login.setLoginTime(new Date());
        userService.login(login);
        String token = JwtUtils.createJwtToken(login.getUsername());
        header(response, ResultUtils.result(Result.Code.SUCCESS.getValue(),"",token));
    }

    /**
     * 登录授权失败
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        header(response, ResultUtils.result(Result.Code.FAIL.getValue(), "用户名或密码错误"));
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        header(response, ResultUtils.result());
    }
}
