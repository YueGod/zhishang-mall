package io.github.yuegod.zhishang.component.security.dao;

import io.github.yuegod.zhishang.basecode.service.BaseDao;
import io.github.yuegod.zhishang.component.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @Author 屈子威
 * @Date 2020/8/29 16:16
 * @description
 */
@Mapper
public interface UserMapper extends BaseDao<SysUser> {

    /**
     * 通过用户ID查询对应角色
     */
    List<String> findRolesByUserId(Integer userId);
}
