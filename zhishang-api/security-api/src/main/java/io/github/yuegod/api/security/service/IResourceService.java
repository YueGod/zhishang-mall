package io.github.yuegod.api.security.service;

import io.github.yuegod.zhishang.basecode.api.base.IBaseService;
import io.github.yuegod.api.security.domain.model.Resource;
import io.github.yuegod.api.security.domain.vo.ResourcePermissionVo;

import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/31 6:17
 * @description 资源
 */
public interface IResourceService<E> extends IBaseService<Resource,Integer,E> {
    /**
     * 获取资源和权限
     * @return 返回资源权限
     */
    List<ResourcePermissionVo> findResourcePermissions();
}
