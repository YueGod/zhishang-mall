package io.github.yuegod.zhishang.component.security.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yuegod.api.security.domain.model.Role;
import io.github.yuegod.api.security.service.IRoleService;
import io.github.yuegod.zhishang.basecode.service.BaseServiceImpl;
import io.github.yuegod.zhishang.component.security.dao.RoleMapper;
import io.github.yuegod.zhishang.component.security.entity.SysRole;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:27
 * @description 角色
 */
@Service
@Primary
public class RoleServiceImpl extends BaseServiceImpl<SysRole, Role, Integer, RoleMapper> implements IRoleService<SysRole> {
    @Resource
    private RoleMapper dao;

    @Override
    protected BaseMapper getDao() {
        return dao;
    }

    @Override
    protected SysRole model2Entity(Role role) {
        SysRole sysRole = null;
        if (!ObjectUtils.isEmpty(role)) {
            sysRole = new SysRole();
            sysRole.setName(role.getName());
            sysRole.setId(role.getId());
        }
        return sysRole;
    }

    @Override
    protected Role entity2Model(SysRole sysRole) {
        Role role = null;
        if (!ObjectUtils.isEmpty(sysRole)) {
            role = new Role();
            role.setName(sysRole.getName());
            role.setId(sysRole.getId());
        }
        return role;
    }

    @Override
    public int save(Role role) {
        int i = 0;
        if (role.isCreate()) {
            i = dao.insert(model2Entity(role));
        } else {
            SysRole sysRole = dao.selectById(role.getId());
            if (isNotEmpty(role.getName())) {
                sysRole.setName(role.getName());
            }
            i = dao.updateById(sysRole);
        }
        return i;
    }

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return entity2Model(dao.findRolesByUserId(userId));
    }

    @Override
    public void saveRolesByUserIdAndRoleIds(Integer userId, List<Integer> roleIds) {
        dao.saveRolesByUserIdAndRoleIds(userId, roleIds);
    }

    @Override
    public void deleteRolesByUserIdAndRoleIds(Integer userId, List<Integer> roleIds) {
        dao.deleteRolesByUserIdAndRoleIds(userId, roleIds);
    }
}
