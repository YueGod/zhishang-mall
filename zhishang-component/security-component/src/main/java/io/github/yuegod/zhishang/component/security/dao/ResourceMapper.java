package io.github.yuegod.zhishang.component.security.dao;

import io.github.yuegod.api.security.domain.vo.ResourcePermissionVo;
import io.github.yuegod.zhishang.basecode.service.BaseDao;
import io.github.yuegod.zhishang.component.security.entity.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/31 6:24
 * @description 资源Mapper
 */
@Mapper
public interface ResourceMapper extends BaseDao<SysResource> {

    /**
     * 获取资源和权限
     * @return 返回资源权限
     */
    List<ResourcePermissionVo> findResourcePermissions();

}
