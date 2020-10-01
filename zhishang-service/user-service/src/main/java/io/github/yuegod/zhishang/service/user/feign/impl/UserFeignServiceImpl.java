package io.github.yuegod.zhishang.service.user.feign.impl;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.api.user.service.ITopUserService;
import io.github.yuegod.zhishang.feign.user.client.IUserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@RestController
public class UserFeignServiceImpl implements IUserFeignService {
    @Autowired
    private ITopUserService topUserService;

    @Override
    public User findById(Integer id) {
        return (User) topUserService.findById(id);
    }
}
