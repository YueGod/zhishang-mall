package io.github.yuegod.api.security.service;


import io.github.yuegod.zhishang.basecode.api.base.IBaseService;
import io.github.yuegod.api.security.domain.model.Login;
import io.github.yuegod.api.security.domain.model.User;

import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/8/29 16:17
 * @description
 */
public interface IUserService<E> extends IBaseService<User, Integer, E> {

    /**
     * 登录
     */
    void login(Login login);

    /**
     * 获取用户相关权限
     */
    List<String> findPermissionsByUser(User user);

    /**
     * 获取用户对应的角色
     */
    List<String> findRolesByUserId(Integer userId);

    int save(User user);
}
