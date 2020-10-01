package io.github.yuegod.zhishang.component.security.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yuegod.api.security.domain.model.Login;
import io.github.yuegod.api.security.domain.model.User;
import io.github.yuegod.api.security.event.LoginEvent;
import io.github.yuegod.api.security.service.IUserService;
import io.github.yuegod.zhishang.basecode.service.BaseServiceImpl;
import io.github.yuegod.zhishang.component.security.dao.PermissionMapper;
import io.github.yuegod.zhishang.component.security.dao.UserMapper;
import io.github.yuegod.zhishang.component.security.dao.UserRoleMapper;
import io.github.yuegod.zhishang.component.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/8/29 16:17
 * @description 需要通过@Primary来指定哪个是主要的加载对象
 */
@Service
@Primary
public class UserServiceImpl extends BaseServiceImpl<SysUser, User, Integer, UserMapper> implements IUserService<SysUser> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected BaseMapper getDao() {
        return userMapper;
    }

    @Override
    protected SysUser model2Entity(User user) {
        SysUser shUser = null;
        if (!ObjectUtils.isEmpty(user)) {
            shUser = new SysUser();
            shUser.setCreateTime(user.getCreateTime());
            shUser.setPassword(user.getPassword());
            shUser.setStatus(user.getStatus());
            shUser.setUsername(user.getUsername());
            shUser.setId(user.getId());
        }
        return shUser;
    }

    @Override
    protected User entity2Model(SysUser shUser) {
        User user = null;
        if (!ObjectUtils.isEmpty(shUser)) {
            user = new User();
            user.setCreateTime(shUser.getCreateTime());
            user.setPassword(shUser.getPassword());
            user.setStatus(shUser.getStatus());
            user.setUsername(shUser.getUsername());
            user.setId(shUser.getId());
        }
        return user;
    }


    @Override
    public void login(Login login) {
        applicationContext.publishEvent(new LoginEvent(login));
    }

    @Override
    public List<String> findPermissionsByUser(User user) {
        List<Integer> roleIds = userRoleMapper.findByUserId(user.getId());
        return permissionMapper.findByRoleIds(roleIds);
    }

    @Override
    public List<String> findRolesByUserId(Integer userId) {
        return userMapper.findRolesByUserId(userId);
    }

    @Override
    public int save(User user) {
        if (user.isCreate()) {
            return userMapper.insert(model2Entity(user));
        } else {
            return userMapper.updateById(model2Entity(user));
        }
    }
}
