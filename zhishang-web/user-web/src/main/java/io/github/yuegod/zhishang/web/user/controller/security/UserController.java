package io.github.yuegod.zhishang.web.user.controller.security;

import io.github.yuegod.api.security.domain.model.User;
import io.github.yuegod.api.security.service.IUserService;
import io.github.yuegod.zhishang.basecode.api.base.PageReq;
import io.github.yuegod.zhishang.basecode.api.base.PageResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 屈子威
 * @Date 2020/8/31 17:01
 * @description 系统用户管理
 */
@RestController
@RequestMapping("/sys/user")
@PreAuthorize("hasAuthority('系统管理员')")
@Api(tags = "系统用户管理(只有系统管理员可以用)")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping
    public PageResp<User> findAll(PageReq pageReq) {
        return userService.findAll(pageReq);
    }

    @ApiOperation("根据ID删除用户")
    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @ApiOperation("保存OR修改")
    @PostMapping("/add")
    public void add(User user) {
        userService.save(user);
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return (User) userService.findById(id);
    }
}
