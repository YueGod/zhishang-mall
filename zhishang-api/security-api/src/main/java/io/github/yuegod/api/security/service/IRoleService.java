package io.github.yuegod.api.security.service;

import io.github.yuegod.zhishang.basecode.api.base.IBaseService;
import io.github.yuegod.api.security.domain.model.Role;

import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:26
 * @description
 */
public interface IRoleService<E> extends IBaseService<Role,Integer,E> {
    /**
     * 通过UserID查询用户对应的角色
     * @return 角色集合
     */
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 通过用户ID和角色ID集合给用户添加角色
     * @param userId 用户ID
     * @param roleIds 角色ID集合
     */
    void saveRolesByUserIdAndRoleIds(Integer userId,List<Integer> roleIds);

    /**
     * 通过用户ID和角色ID集合 移除用户所对应的角色
     */
    void deleteRolesByUserIdAndRoleIds(Integer userId,List<Integer> roleIds);

}
