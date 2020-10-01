package io.github.yuegod.zhishang.component.security.configuration;

import cn.hutool.core.util.StrUtil;
import io.github.yuegod.api.security.domain.model.User;
import io.github.yuegod.api.security.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 12:33
 * @description 权限Token过滤器
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private IUserService userService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //当前上下文中不存在认证信息
        //尝试获取token （token不一定存放在header中，比如也可以当做请求参数进行传递）
        //尝试从token中解析对象 （token中可以存放任何信息）
        //尝试从根据存放在token的信息去找对应的用户信息
        //用户找到用户信息信息 就在当前的认证上下文中进行设置,确保后续的filter能够检测到认证通过
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String tokenStr = request.getHeader("token");
            if (StrUtil.isNotBlank(tokenStr)) {
                String tokenObj = JwtUtils.getJwtTokenClaimValue(tokenStr);
                if (StrUtil.isNotBlank(tokenObj)) {
                    User user = new User();
                    user.setUsername(tokenObj);
                    user = (User) userService.findOne(user);
                    if (user != null) {
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        List<String> roles = userService.findRolesByUserId(user.getId());
                        for (String role : roles) {
                            authorities.add(new SimpleGrantedAuthority(role));
                        }
                        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user,user.getPassword(),authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        //调用下一个过滤器
        chain.doFilter(request, response);
    }

}
