package io.github.yuegod.zhishang.component.security.dao;

import io.github.yuegod.zhishang.basecode.service.BaseDao;
import io.github.yuegod.zhishang.component.security.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 19:26
 * @description 权限
 */
@Mapper
public interface PermissionMapper extends BaseDao<SysPermission> {

    /**
     * 通过RoleId集合查询权限
     * @param ids 角色表ID集合
     * @return 权限相关
     */
    List<String> findByRoleIds(@Param("ids") List<Integer> ids);

}
