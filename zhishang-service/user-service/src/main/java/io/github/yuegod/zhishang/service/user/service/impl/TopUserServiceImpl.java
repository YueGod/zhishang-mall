package io.github.yuegod.zhishang.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yuegod.api.security.enums.StatusEnum;
import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.api.user.service.ITopUserService;
import io.github.yuegod.zhishang.basecode.api.base.BaseException;
import io.github.yuegod.zhishang.basecode.service.BaseServiceImpl;
import io.github.yuegod.zhishang.component.security.dao.RoleMapper;
import io.github.yuegod.zhishang.component.security.dao.UserMapper;
import io.github.yuegod.zhishang.component.security.dao.UserRoleMapper;
import io.github.yuegod.zhishang.component.security.entity.SysRole;
import io.github.yuegod.zhishang.component.security.entity.SysUser;
import io.github.yuegod.zhishang.service.user.dao.TopUserMapper;
import io.github.yuegod.zhishang.service.user.domain.entity.TopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
@Service
@Primary
public class TopUserServiceImpl extends BaseServiceImpl<TopUser, User, Integer, TopUserMapper> implements ITopUserService<TopUser> {
    @Resource
    private TopUserMapper mapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUser.setStatus(StatusEnum.SUCCESS.getStatus());
        userMapper.insert(sysUser);
        SysRole sysRole = new SysRole();
        sysRole.setName("普通用户");
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysRole);
        sysRole = roleMapper.selectOne(wrapper);
        userRoleMapper.add(sysUser.getId(), sysRole.getId());
        mapper.insert(model2Entity(user));
    }

    @Override
    public void change(User user) {
        if (isNotEmpty(user.getPassword())){
            throw new BaseException("不允许修改密码");
        }
        mapper.updateById(model2Entity(user));
    }

    @Override
    public void verificationCode(Integer type) {

    }

    @Override
    protected BaseMapper getDao() {
        return mapper;
    }

    @Override
    protected TopUser model2Entity(User user) {
        if (isNotEmpty(user)) {
            TopUser topUser = new TopUser();
            topUser.setNickName(user.getNickName());
            topUser.setSex(user.getSex());
            topUser.setBirthDay(user.getBirthDay());
            topUser.setEmail(user.getEmail());
            topUser.setPhone(user.getPhone());
            topUser.setProfilePhoto(user.getProfilePhoto());
            topUser.setTotalAmount(user.getTotalAmount());
            topUser.setScore(user.getScore());
            topUser.setLastLoginTime(user.getLastLoginTime());
            topUser.setUserId(user.getUserId());
            topUser.setId(user.getId());
            return topUser;
        }
        return null;
    }

    @Override
    protected User entity2Model(TopUser topUser) {
        if (isNotEmpty(topUser)) {
            User user = new User();
            user.setNickName(topUser.getNickName());
            user.setSex(topUser.getSex());
            user.setBirthDay(topUser.getBirthDay());
            user.setEmail(topUser.getEmail());
            user.setPhone(topUser.getPhone());
            user.setProfilePhoto(topUser.getProfilePhoto());
            user.setTotalAmount(topUser.getTotalAmount());
            user.setScore(topUser.getScore());
            user.setLastLoginTime(topUser.getLastLoginTime());
            user.setUserId(topUser.getUserId());
            user.setId(topUser.getId());
            return user;
        }
        return null;
    }

    @Override
    public int save(User user) {
        if (user.isCreate()) {
            return add(user);
        } else {
            return update(user);
        }
    }
}
