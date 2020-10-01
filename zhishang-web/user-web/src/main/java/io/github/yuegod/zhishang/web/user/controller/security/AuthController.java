package io.github.yuegod.zhishang.web.user.controller.security;

import io.github.yuegod.api.security.domain.model.User;
import io.github.yuegod.api.security.service.IUserService;
import io.github.yuegod.zhishang.component.security.configuration.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 屈子威
 * @Date 2020/8/31 16:55
 * @description 权限验证
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "权限验证")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation("登录授权")
    public void login(String username,String password){
        return;
    }

    @GetMapping("/logout")
    @ApiOperation("退出登录")
    private void logout(){
        return;
    }

    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    private User findUserInfo(){
        User user = (User) userService.findById(SecurityUtils.getUserId());
        user.setPassword(null);
        return user;
    }

}
