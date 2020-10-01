package io.github.yuegod.zhishang.component.security.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/8/29 18:28
 * @description
 */
@EnableWebSecurity
@Configurable
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入配置不经过权限拦截的路径
     */
    @Value("${yuegod.security.antMatchers.premissAll:}")
    private String permissAll;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private UserDetailsService userInfoService;
    @Autowired
    private SecurityAuthenticationRestHandler restHandler;
    @Autowired
    private SystemSecurityFilter systemSecurityFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("不需要拦截的请求：" + permissAll);
        http.csrf().disable();
        http.addFilterAfter(systemSecurityFilter, FilterSecurityInterceptor.class);
        // 初始化不拦截的请求
        String[] permissAllArray = new String[]{"/*", "/static/**", "/webjars/**", "/swagger**", "/swagger-resources/**", "/v2/io.github.yuegod.zhishang.basecode.api-docs"};
        if (!StringUtils.isEmpty(permissAll)) {
            List<String> permissList = new ArrayList<>(Arrays.asList(permissAllArray));
            permissList.addAll(Arrays.asList(permissAll.split(",")));

            permissAllArray = permissList.toArray(permissAllArray);
        }

        http.authorizeRequests()
                .antMatchers(permissAllArray).permitAll()
                .anyRequest().authenticated()

                // 登陆 成功/失败 后的处理，因为是API的形式所以不用跳转页面
                .and().formLogin().loginPage("/auth/login").successHandler(restHandler).failureHandler(restHandler).permitAll()
                // 登出后的处理
                .and().logout().logoutUrl("/auth/logout").logoutSuccessHandler(restHandler)
                // 认证不通过后的处理
                .and().exceptionHandling().authenticationEntryPoint(restHandler)
                .and().rememberMe()
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
    }

}
