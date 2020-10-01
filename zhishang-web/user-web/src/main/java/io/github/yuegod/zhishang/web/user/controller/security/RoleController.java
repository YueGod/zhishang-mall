package io.github.yuegod.zhishang.web.user.controller.security;

import io.github.yuegod.api.security.domain.model.Role;
import io.github.yuegod.api.security.service.IRoleService;
import io.github.yuegod.zhishang.basecode.api.base.PageReq;
import io.github.yuegod.zhishang.basecode.api.base.PageResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:22
 * @description 角色管理
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理")
@PreAuthorize("hasAuthority('系统管理员')")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation("查看所有角色")
    @GetMapping("/all")
    public PageResp<Role> findAll(PageReq pageReq) {
        return roleService.findAll(pageReq);
    }

    @ApiOperation("保存或修改")
    @PostMapping("/save")
    public void save(Role role) {
        roleService.save(role);
    }

    @ApiOperation("删除角色")
    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
    }

    @ApiOperation("查询用户对应的角色")
    @GetMapping("/user/{userId}")
    public List<Role> findByUserId(@PathVariable Integer userId) {
        return roleService.findRolesByUserId(userId);
    }

    @ApiOperation("给用户添加角色")
    @PostMapping("/user/{userId}")
    public void saveRolesByUserIdAndRoleIds(@PathVariable Integer userId, @RequestParam(required = false) List<Integer> roleIds) {
        roleService.saveRolesByUserIdAndRoleIds(userId, roleIds);
    }

    @ApiOperation("给用户删除角色")
    @DeleteMapping("/user/{userId}")
    public void deleteRolesByUserIdAndRoleIds(@PathVariable Integer userId, @RequestParam(required = false) List<Integer> roleIds) {
        roleService.deleteRolesByUserIdAndRoleIds(userId, roleIds);
    }
}
