package io.github.yuegod.zhishang.component.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 18:40
 * @description 用户角色中间表
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 通过用户ID查询角色表
     */
    List<Integer> findByUserId(Integer userId);

    /**
     * 保存用户ID和RoleId
     */
    void add(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}
