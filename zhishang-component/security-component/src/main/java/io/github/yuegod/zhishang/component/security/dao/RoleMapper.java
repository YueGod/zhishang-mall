package io.github.yuegod.zhishang.component.security.dao;

import io.github.yuegod.zhishang.basecode.service.BaseDao;
import io.github.yuegod.zhishang.component.security.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/9/1 11:28
 * @description
 */
@Mapper
public interface RoleMapper extends BaseDao<SysRole> {

    List<SysRole> findRolesByUserId(Integer userId);

    void saveRolesByUserIdAndRoleIds(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    void deleteRolesByUserIdAndRoleIds(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);
}
