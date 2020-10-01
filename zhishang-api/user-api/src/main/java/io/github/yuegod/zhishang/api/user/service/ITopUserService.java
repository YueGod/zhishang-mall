package io.github.yuegod.zhishang.api.user.service;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.basecode.api.base.IBaseService;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
public interface ITopUserService<E> extends IBaseService<User,Integer,E> {

    /**
     * 注册
     */
    void register(User user);

    /**
     * 修改用户信息
     */
    void change(User user);

    /**
     * 验证码
     */
    void verificationCode(Integer type);

}
