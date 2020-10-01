package io.github.yuegod.zhishang.web.user.controller;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.api.user.service.ITopUserService;
import io.github.yuegod.zhishang.component.security.configuration.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description 商城普通用户
 **/
@RestController
@RequestMapping("/top/user")
@Api(tags = "商城普通用户管理")
public class TopUserController {
    @Autowired
    private ITopUserService topUserService;

    @PostMapping
    @ApiOperation("用户注册")
    public void register(User user) {
        topUserService.register(user);
    }

    @PutMapping
    @ApiOperation("用户信息修改")
    public void change(User user) {
        topUserService.change(user);
    }

    @GetMapping
    @ApiOperation("用户信息获取")
    public User userInfo() {
        User user = new User();
        user.setUserId(SecurityUtils.getUserId());
        return (User) topUserService.findOne(user);
    }
}
