package io.github.yuegod.zhishang.component.security.configuration;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author 屈子威
 * @Date 2020/8/31 16:59
 * @description 权限工具类
 */
public class SecurityUtils {

    public static UserInfo getUserInfo() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Integer getUserId() {
        return getUserInfo().getUserId();
    }

}
