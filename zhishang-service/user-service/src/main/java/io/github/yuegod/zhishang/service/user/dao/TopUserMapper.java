package io.github.yuegod.zhishang.service.user.dao;

import io.github.yuegod.zhishang.api.user.domain.model.User;
import io.github.yuegod.zhishang.basecode.service.BaseDao;
import io.github.yuegod.zhishang.service.user.domain.entity.TopUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description
 **/
public interface TopUserMapper extends BaseDao<TopUser> {

    /**
     * 通过用户ID查询
     */
    TopUser findByUserId(@Param("userId") Integer userId);

}
